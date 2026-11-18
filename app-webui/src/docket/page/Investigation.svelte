<script>
  import { push } from 'svelte-spa-router';
  import UiErrorMessage from '@core/widget/UiErrorMessage.svelte';
  import UiLoadingIndicator from '@core/widget/UiLoadingIndicator.svelte';
  import * as investigationApi from '../api/InvestigationApi.js';
  import SceneMap from '../widget/SceneMap.svelte';

  let { params = {} } = $props();
  const id = $derived(params.id);

  let error = $state(null);
  let loading = $state(true);
  let context = $state(null);

  const activeDetectiveId = $derived(
    context.investigation.state.activeDetectiveId
  );

  const activeDetective = $derived(
    context.script.investigation.detectives[activeDetectiveId]
  );

  const budget = $derived(
    context.investigation.state.budget
  );

  function handleLocationClick(locId) {
    push(`/investigation/${id}/location/${locId}`);
  }

  $effect(() => {
    async function loadData() {
      loading = true;
      error = null;
      try {
        const c = await investigationApi.findInvestigationContext(id);
        context = c;
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
      <span>{context.script.metainfo.title}</span>
      <span
        class="badge text-bg-secondary"
        style="font-size: 0.5em; margin-left: auto; flex-shrink: 0;"
      >
        {context.script.metainfo.uid}
      </span>
    </h1>

    <div
      class="d-flex flex-wrap align-items-center gap-3 mb-2 small"
      style="flex-shrink: 0;"
    >
      <div class="d-inline-flex align-items-center gap-2">
        <img
          src="/docket/script/images/thumb/{context.investigation.scriptId}/{activeDetectiveId}"
          alt={activeDetective.name}
          class="rounded border text-muted"
          style="width: 32px; height: 32px; object-fit: cover;"
        />
        <span class="text-muted">Текущий ход:</span>
        <span>{activeDetective.name}</span>
      </div>

      <div class="d-inline-flex align-items-center gap-2">
        <i class="fas fa-coins text-muted"></i>
        <span class="text-muted">Бюджет:</span>
        <span class="fw-semibold">{budget ?? '—'}</span>
      </div>
    </div>

    <div class="scene-map-wrapper">
      <SceneMap {context} onLocationClick={handleLocationClick} />
    </div>
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