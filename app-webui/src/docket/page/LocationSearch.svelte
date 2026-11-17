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

  const activeDetectiveId = $derived(
    context.investigation.state.activeDetectiveId
  );

  const activeDetective = $derived(
    context.script.investigation.detectives[activeDetectiveId]
  );

  let focus = $state(0);
  let d12 = $state(0);

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
		console.log(c);
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

      <div class="d-flex align-items-start mb-3 search-form">
        <img
          src="/docket/script/images/thumb/{context.investigation.scriptId}/{activeDetectiveId}"
          alt={activeDetective.name}
          class="img-fluid rounded border text-muted"
          style="width: 120px; min-width: 120px; object-fit: cover;"
        />

        <div class="ps-3 flex-grow-1" style="max-width: 360px;">
          <div class="mb-3 row align-items-center">
            <label for="search-focus" class="col-4 col-form-label">Фокус:</label>
            <div class="col-8">
              <input
                id="search-focus"
                type="number"
                class="form-control"
                min="0"
                bind:value={focus}
              />
            </div>
          </div>

          <div class="mb-3 row align-items-center">
            <label for="search-d12" class="col-4 col-form-label">D12:</label>
            <div class="col-8">
              <input
                id="search-d12"
                type="number"
                class="form-control"
                min="1"
                max="12"
                bind:value={d12}
              />
            </div>
          </div>
        </div>
      </div>
    {/if}

    <button class="btn btn-outline-secondary mb-3" onclick={goBack}>
      ← Назад на карту
    </button>
  {/if}
</div>