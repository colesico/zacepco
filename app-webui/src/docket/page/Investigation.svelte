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

  function handleLocationClick(locId) {
    push(`/investigation/${id}/location/${locId}`);
  }

  $effect(() => {
    let cancelled = false;
    async function loadData() {
      loading = true;
      error = null;
      try {
        const c = await investigationApi.findInvestigationContext(id);
        if (cancelled) return;
        context = c;
      } catch (ex) {
        if (cancelled) return;
        error = ex.data;
      } finally {
        if (!cancelled) loading = false;
      }
    }
    loadData();
    return () => { cancelled = true; };
  });
</script>

<div class="container investigation-page">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />

  {#if context}
    <h1 class="investigation-title">
      {context.script.metainfo.title}
      <span class="badge text-bg-secondary" style="font-size: 0.5em;">
        {context.script.metainfo.uid}
      </span>
    </h1>

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