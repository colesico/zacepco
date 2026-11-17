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

  // C/L/P: '?' или 1–3 символа A–Z, 0–9
  // T:     '?' или 1-3 цифры 0–9
  const FOCUS_TOKEN = /^(?:[CcLlPp](?:\?|[a-zA-Z0-9]{1,3})|[Tt](?:\?|\d{1,3}))$/;

  let focus = $state('');
  let d12 = $state(null);

  let focusError = $state(false);
  let d12Error = $state(false);

  const focusTokens = $derived(
    focus.split(/[ ,]+/).filter(Boolean)
  );

  const focusValid = $derived(
    focusTokens.length > 0 && focusTokens.every((t) => FOCUS_TOKEN.test(t))
  );

  const d12Valid = $derived(
    Number.isInteger(d12) && d12 >= 1 && d12 <= 12
  );

  function rollD12() {
    d12 = Math.floor(Math.random() * 12) + 1;
  }

  function handleSearch(e) {
    e.preventDefault();

    focusError = !focusValid;
    d12Error = !d12Valid;

    if (focusError || d12Error) return;

    // TODO: вызов API обыска, например:
    // await investigationApi.search(invId, { locId, focus: focusTokens, d12 });
    console.log('[Search]', { focus: focusTokens, d12 });
  }

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
        {location.description}
      </p>
    </div>

    {#if locationState.searchAvailable}
      <h2>Обыск локации</h2>

      <div class="d-flex align-items-start mb-3 search-form">
        <div class="text-center" style="width: 120px; min-width: 120px;">
          <img
            src="/docket/script/images/thumb/{context.investigation.scriptId}/{activeDetectiveId}"
            alt={activeDetective.name}
            class="img-fluid rounded border text-muted"
            style="width: 120px; height: 120px; object-fit: cover;"
          />
          <div class="small fw-semibold mt-1 text-break">
            {activeDetective.name}
          </div>
        </div>

        <form
          class="ps-3 flex-grow-1"
          style="max-width: 360px;"
          novalidate
          onsubmit={handleSearch}
        >
          <div class="mb-3 row align-items-start">
            <label for="search-focus" class="col-4 col-form-label">Фокус:</label>
            <div class="col-8">
              <input
                id="search-focus"
                type="text"
                class="form-control {focusError ? 'is-invalid' : ''}"
                placeholder="?"
                autocapitalize="characters"
                autocomplete="off"
                spellcheck="false"
                bind:value={focus}
              />
              {#if focusError}
                <div class="invalid-feedback">
                  Ошибка формата. Укажите через пробел или запятую ID/Типы сущностей. Например: T1, PGRD, L?
                </div>
              {/if}
            </div>
          </div>

          <div class="mb-3 row align-items-start">
            <label for="search-d12" class="col-4 col-form-label">D12:</label>
            <div class="col-8">
              <div class="d-flex gap-2">
                <button
                  type="button"
                  class="btn btn-outline-secondary"
                  title="Бросить D12"
                  aria-label="Бросить D12"
                  onclick={rollD12}
                >
                  <i class="fas fa-dice"></i>
                </button>
                <input
                  id="search-d12"
                  type="number"
                  class="form-control {d12Error ? 'is-invalid' : ''}"
                  placeholder="?"
                  min="1"
                  max="12"
                  bind:value={d12}
                />
              </div>
              {#if d12Error}
                <div class="invalid-feedback d-block">
                  Введите число от 1 до 12.
                </div>
              {/if}
            </div>
          </div>

          <div class="text-end">
            <button type="submit" class="btn btn-primary">
              <i class="fas fa-magnifying-glass me-2"></i>Обыскать
            </button>
          </div>
        </form>
      </div>
    {/if}

    <button class="btn btn-outline-secondary mb-3" onclick={goBack}>
      ← Назад на карту
    </button>
  {/if}
</div>