<script module>
  let investigationScrollY = 0;
</script>

<script>
  import { push } from 'svelte-spa-router';
  import { tick } from 'svelte';

  import UiErrorMessage from '@core/widget/UiErrorMessage.svelte';
  import UiLoadingIndicator from '@core/widget/UiLoadingIndicator.svelte';
  import * as investigationApi from '../api/InvestigationApi.js';
  import SceneMap from '../widget/SceneMap.svelte';
  import PersonagesBar from '../widget/PersonagesBar.svelte';
  import WayInfo from '../widget/WayInfo.svelte';

  let { params = {} } = $props();
  const id = $derived(params.id);
  const scroll = $derived(params.scroll);

  let error = $state(null);
  let loading = $state(true);
  let context = $state(null);
  
  let wayModal = $state(null);

  const activeDetectiveId = $derived(
    context.investigation.state.activeDetectiveId
  );

  const activeDetective = $derived(
    context.script.investigation.detectives[activeDetectiveId]
  );

  const participatingDetectives = $derived(
    Object.entries(context.investigation.state.detectives ?? {}).map(
      ([detId, stateInfo]) => ({
        id: detId,
        points: stateInfo?.points ?? 0,
        ...(context.script.investigation.detectives[detId] ?? { name: detId })
      })
    )
  );

  const budget = $derived(
    context.investigation.state.budget
  );

  function handleLocationClick(locationId) {
    investigationScrollY = window.scrollY;
    push(`/investigation/${id}/inspection/${locationId}`);
  }

  function handlePersonageClick(personageId) {
    investigationScrollY = window.scrollY;
    push(`/investigation/${id}/inspection/${personageId}`);
  }

  function handleWayClick(wayId) {
    wayModal?.open(wayId);
  }
  
  $effect(() => {
    async function loadData() {
      loading = true;
      error = null;
      try {
        const c = await investigationApi.findInvestigationContext(id);
        console.log(c);
        context = c;

        // Restore scroll
        await tick();
		if (scroll != null) {
          investigationScrollY = Number(scroll);
        }
        window.scrollTo({ top: investigationScrollY, behavior: 'instant' });
        investigationScrollY = 0;
      } catch (ex) {
        error = ex.data;
      } finally {
        loading = false;
      }
    }
    loadData();
  });
</script>

<div class="container investigation-page">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />

  {#if context}
    <h1
      class="investigation-title"
      style="display: flex; justify-content: space-between; align-items: center; gap: 1rem;"
    >
      <span class="d-inline-flex align-items-center gap-2">
        <span>{context.script.metainfo.title}</span>
        {#if context.script.metainfo.annotation}
          <button
            type="button"
            class="btn btn-sm btn-outline-secondary rounded-circle d-inline-flex align-items-center justify-content-center p-0"
            style="width: 1.2rem; height: 1.2rem; font-size: 0.75rem;"
            data-bs-toggle="modal"
            data-bs-target="#scriptAnnotationModal"
            aria-label="Аннотация сценария"
            title="Аннотация сценария"
          >
            <i class="fas fa-info"></i>
          </button>
        {/if}
      </span>
      <span
        class="badge text-bg-secondary"
        style="font-size: 0.5em; margin-left: auto; flex-shrink: 0;"
      >
        {context.script.metainfo.uid}
      </span>
    </h1>

    <!-- Bootstrap Modal с аннотацией -->
    {#if context.script.metainfo.annotation}
      <div
        class="modal fade"
        id="scriptAnnotationModal"
        tabindex="-1"
        aria-labelledby="scriptAnnotationModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="scriptAnnotationModalLabel">
                {context.script.metainfo.title}
              </h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Закрыть"
              ></button>
            </div>
            <div class="modal-body" style="white-space: pre-line;">
              {context.script.metainfo.annotation}
            </div>
          </div>
        </div>
      </div>
    {/if}

    <div class="mb-2" style="flex-shrink: 0;">
      <!-- Ряд всех участвующих детективов -->
      <div class="d-flex flex-wrap align-items-center gap-2 mb-2">
        {#each participatingDetectives as det (det.id)}
          {@const isActive = det.id === activeDetectiveId}
          <div
            class="d-inline-flex flex-column align-items-center"
            title={`${det.name} (Очки: ${det.points})`}
          >
            <img
              src="/docket/script/images/thumb/{context.investigation.scriptId}/{det.id}"
              alt={det.name}
              class="rounded object-fit-cover {isActive
                ? 'border border-3 border-warning shadow-sm'
                : 'border opacity-75'}"
              style="width: 48px; height: 48px;"
            />
          </div>
        {/each}
      </div>

      <!-- Строка под рядом детективов: Текущий ход и Бюджет -->
      <div class="d-flex flex-wrap align-items-center gap-3 small">
        <div class="d-inline-flex align-items-center gap-1">
          <span class="text-muted">Текущий ход:</span>
          <span class="fw-semibold">{activeDetective.name}</span>
        </div>

        <div class="d-inline-flex align-items-center gap-1">
          <i class="fas fa-coins text-muted"></i>
          <span class="text-muted">Бюджет:</span>
          <span class="fw-semibold">{budget ?? '—'}</span>
        </div>
      </div>
    </div>

    <h2 class="mb-1">{context.script.crime.scene.name}</h2>
    <div class="scene-map-wrapper">
      <SceneMap {context} onLocationClick={handleLocationClick} onWayClick={handleWayClick} />
    </div>

   <WayInfo bind:this={wayModal} {context} />

    <h2 class="mb-1">Персонажи</h2>
    <PersonagesBar
      cols={Object.keys(context.script.crime.personages ?? {}).length}
      rows={1}
      {context}
      onPersonageClick={handlePersonageClick}
    />

    <h2 class="mb-1">Экспертиза</h2>
  {/if}
</div>

<style>
  .investigation-page {
    display: flex;
    flex-direction: column;
    flex: 1 1 auto;
    min-height: 0;
    box-sizing: border-box;
  }

  .investigation-title {
    flex-shrink: 0;
  }

  .scene-map-wrapper {
    flex: 1 1 auto;
    min-height: 0;
    width: 100%;
    display: flex;
    align-items: flex-start;
    justify-content: center;
    overflow: hidden;
  }
</style>
