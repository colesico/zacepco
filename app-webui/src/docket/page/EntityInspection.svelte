<script>
  import { push } from 'svelte-spa-router';
  import { pop } from 'svelte-spa-router';
  import { tick } from 'svelte';

  import UiErrorMessage from '@core/widget/UiErrorMessage.svelte';
  import UiLoadingIndicator from '@core/widget/UiLoadingIndicator.svelte';
  import * as investigationApi from '../api/InvestigationApi.js';
  import InspectionForm from '../widget/InspectionForm.svelte';
  import InspectionResult from '../widget/InspectionResult.svelte';

  let { params = {} } = $props();
  const investigationId = $derived(params.investigationId);
  const entityId = $derived(params.entityId);

  let error = $state(null);
  let loading = $state(true);
  let context = $state(null);

  let inspectionResult = $state(null);
  let inspectionMode = $state(null);
  
  let inspectionResultModal = $state(null);

  const entity = $derived(
    entityId?.startsWith('P')
      ? context?.script?.crime?.personages?.[entityId]
      : context?.script?.crime?.scene?.locations?.[entityId]
  );

  const entityState = $derived(
    entityId?.startsWith('P')
      ? context?.investigation?.state?.personages?.[entityId]
      : context?.investigation?.state?.scene?.locations?.[entityId]
  );

  const budget = $derived(context?.investigation?.state?.budget ?? 0);

  // Улики сущности по сценарию, отфильтрованные по факту открытия
  const exposedClues = $derived(
    (Array.isArray(entity?.clues) ? entity.clues : []).filter(
      (cid) => context?.investigation?.state?.clues?.[cid]?.discovered === true
    )
  );

  async function handleSearch(focus, d12) {
    loading = true;
    error = null;
    try {
      const r = await investigationApi.search(investigationId, entityId, focus, d12);
      console.log('[Search] result: ', r);
	  inspectionType = "SEARCH";
      inspectionResult = r;
	  await tick();
      inspectionResultModal?.show();
    } catch (ex) {
      error = ex.data;
    } finally {
      loading = false;
    }
  }

  async function handleInterrogation(focus, d12) {
    console.log('[Interrogation]', focus, d12);
  }

  function goBack() {
    pop();
  }

  $effect(() => {
    let cancelled = false;

    async function loadData() {
      loading = true;
      error = null;
      try {
        const c = await investigationApi.findInvestigationContext(investigationId);
        if (cancelled) return;
        context = c;
      } catch (ex) {
        if (cancelled) return;
        error = ex.data;
        context = null;
      } finally {
        if (!cancelled) loading = false;
      }
    }

    loadData();

    return () => {
      cancelled = true;
    };
  });
</script>

<div class="container">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />

  {#if context && entity && entityState}
    <h1>
      {entity.name}
    </h1>

    <div class="d-flex align-items-start mb-3">
      <div
        class="position-relative flex-shrink-0"
        style="width: 35%; max-width: 120px; min-width: 0;"
      >
        <img
          src="/docket/script/images/thumb/{context.investigation.scriptId}/{entityId}"
          alt={entity.name}
          class="img-fluid rounded border text-muted w-100"
          style="object-fit: cover; display: block;"
        />
        <span
          class="badge text-bg-secondary position-absolute top-0 start-0 m-1"
          style="font-size: 0.7em; z-index: 2;"
        >
          {entityId}
        </span>
      </div>
      <p class="ps-3 mb-0" style="min-width: 0; overflow-wrap: break-word;">
        {entity.description}
      </p>
    </div>

    {#if budget > 0 && (entityState.searchAvailable || (entityId?.startsWith('P') && entityState?.interrogationAvailable))}
      <ul class="nav nav-tabs mb-3" role="tablist">
        {#if entityState.searchAvailable}
          <li class="nav-item" role="presentation">
            <button
              class="nav-link active"
              data-bs-toggle="tab"
              data-bs-target="#tab-search"
              type="button"
              role="tab"
            >
              Обыск
            </button>
          </li>
        {/if}

        {#if entityId?.startsWith('P') && entityState?.interrogationAvailable}
          <li class="nav-item" role="presentation">
            <button
              class="nav-link"
              class:active={!entityState.searchAvailable}
              data-bs-toggle="tab"
              data-bs-target="#tab-interrogation"
              type="button"
              role="tab"
            >
              Допрос
            </button>
          </li>
        {/if}
      </ul>

      <div class="tab-content mb-4">
        {#if entityState.searchAvailable}
          <div class="tab-pane fade show active" id="tab-search" role="tabpanel">
            <InspectionForm {context} inspectionType="SEARCH" onInspect={handleSearch} />
          </div>
        {/if}

        {#if entityId?.startsWith('P') && entityState?.interrogationAvailable}
          <div
            class="tab-pane fade"
            class:show={!entityState.searchAvailable}
            class:active={!entityState.searchAvailable}
            id="tab-interrogation"
            role="tabpanel"
          >
            <InspectionForm {context} inspectionType="INTERROGATION" onInspect={handleInterrogation} />
          </div>
        {/if}
      </div>
    {/if}

    {#if exposedClues.length}
      <h2 class="mt-4">Обнаруженные улики</h2>

      <div class="row row-cols-2 row-cols-sm-3 row-cols-md-4 g-3 mb-4">
        {#each exposedClues as clueId}
          {@const clue = context.script.crime.clues[clueId]}
          {@const assetId = clue?.asset ?? clueId}
          <div class="col">
            <div class="card h-100 border-0 bg-light shadow-sm position-relative">
              <div class="ratio ratio-1x1 card-img-top overflow-hidden rounded-top">
                <img
                  src="/docket/script/images/thumb/{context.investigation.scriptId}/{assetId}"
                  alt={clue?.name ?? clueId}
                  class="object-fit-cover"
                  loading="lazy"
                />
              </div>
              <span
                class="badge text-bg-secondary position-absolute top-0 start-0 m-1"
                style="font-size: 0.7em; z-index: 2;"
              >
                {clueId}
              </span>
              <div class="card-body p-2 text-center">
                <div class="fw-semibold text-break mb-1">
                  {clue?.name ?? clueId}
                </div>
                {#if clue?.description}
                  <p class="small text-muted mb-0 text-break">
                    {clue.description}
                  </p>
                {/if}
              </div>
            </div>
          </div>
        {/each}
      </div>
    {:else if !entityState.searchAvailable}
      <p class="text-muted fst-italic mb-3">Улик не обнаружено</p>
    {/if}

    <button class="btn btn-outline-secondary mb-3" onclick={goBack}>
      ← Вернуться к карте
    </button>
  {/if}

  {#if inspectionResult }
    <InspectionResult bind:this={inspectionResultModal} {inspectionResult} {context} />
  {/if}
</div>

<style>
  :global(.nav-tabs .nav-link) {
    color: var(--bs-secondary-color);
  }

  :global(.nav-tabs .nav-link:hover) {
    color: var(--bs-body-color);
  }

  :global(.nav-tabs .nav-link.active) {
    font-weight: 700;
    color: var(--bs-emphasis-color);
  }
</style>
