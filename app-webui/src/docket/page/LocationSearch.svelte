<script>
  import { push } from 'svelte-spa-router';
  import UiErrorMessage from '@core/widget/UiErrorMessage.svelte';
  import UiLoadingIndicator from '@core/widget/UiLoadingIndicator.svelte';
  import * as investigationApi from '../api/InvestigationApi.js';

  let { params = {} } = $props();
  const invId = $derived(params.invId);
  const locId = $derived(params.locId);

  let error = $state(null);
  let loading = $state(true);
  let context = $state(null);

  const location = $derived(
    context.script.crime.scene.locations[locId]
  );

  const locationState = $derived(
    context.investigation.state.scene.locations[locId]
  );

  function goBack() {
    push(`/investigation/${invId}`);
  }

  $effect(() => {
    let cancelled = false;

    async function loadData() {
      loading = true;
      error = null;
      try {
        const c = await investigationApi.findInvestigationContext(invId);
        if (cancelled) return;
        context = c;
        console.log('[LocationSearch] context loaded:', c);
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

  {#if context}
    <h1>
      {location.name}
      <span class="badge text-bg-secondary" style="font-size: 0.5em;">
        {locId}
      </span>
    </h1>

    <div class="d-flex align-items-start mb-3">
      <img
        src="/docket/script/images/thumb/{context.investigation.scriptId}/{locId}"
        alt={location.name}
        class="img-fluid rounded border text-muted"
        style="width: 120px; min-width: 120px; object-fit: cover;"
      />
      <p class="ps-3 mb-0">
        {location.description ?? 'Описание отсутствует.'}
      </p>
    </div>

    {#if locationState.searchAvailable}
      <h2>Обыск локации</h2>
    {/if}

    <button class="btn btn-outline-secondary mb-3" onclick={goBack}>
      ← Назад на карту
    </button>
  {/if}
</div>