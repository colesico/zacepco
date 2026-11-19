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

  const focusLimit = $derived(
    context.script.investigation.search.focusLimit
  );

  const searchCost = $derived(
    context.script.investigation.search.cost
  );

  const searchPoints = $derived(
    context.script.investigation.search.points
  );

  const budget = $derived(context.investigation.state.budget);

  // Улики локации по сценарию, отфильтрованные по факту открытия
  const exposedClues = $derived(
    (Array.isArray(location.clues) ? location.clues : []).filter(
      (cid) => context.investigation.state.clues[cid]?.discovered === true
    )
  );

  const FOCUS_TOKEN = /^(?:[CcLlPp](?:\?|[a-zA-Z0-9]{1,3})?|[Tt](?:\?|\d{1,3})?)$/;

  let focus = $state('');
  let d12 = $state(null);

  let focusError = $state(false);
  let d12Error = $state(false);

  let showFocusHelp = $state(false);

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

  function toggleFocusHelp(e) {
    e.preventDefault();
    showFocusHelp = !showFocusHelp;
  }

  async function handleSearch(e) {
    e.preventDefault();

    focusError = !focusValid;
    d12Error = !d12Valid;

    if (focusError || d12Error) return;

    loading = true;
    error = null;
    try {
      const r = await investigationApi.locationSearch(invId, locId, focus, Number(d12));
      console.log('[Search] result: ', r);
    } catch (ex) {
      error = ex.data;
    } finally {
      loading = false;
    }
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
    </h1>

    <div class="d-flex align-items-start mb-3">
      <div class="position-relative" style="width: 120px; min-width: 120px;">
        <img
          src="/docket/script/images/thumb/{context.investigation.scriptId}/{locId}"
          alt={location.name}
          class="img-fluid rounded border text-muted"
          style="width: 120px; object-fit: cover; display: block;"
        />
        <span
          class="badge text-bg-secondary position-absolute top-0 start-0 m-1"
          style="font-size: 0.7em; z-index: 2;"
        >
          {locId}
        </span>
      </div>
      <p class="ps-3 mb-0">
        {location.description}
      </p>
    </div>

    {#if locationState.searchAvailable}
      <h2>Обыск локации</h2>

      <div class="small text-muted mb-2">
        Стоимость: <strong>{searchCost}</strong>
        · Очки: <strong>{searchPoints}</strong>
        · Бюджет: <strong>{budget}</strong>
      </div>

      <div
        class="search-form-panel mb-3 p-3"
        style="background-color: #eaeaea; border: 1px solid #bdbdbd; border-radius: 0.25rem;"
      >
        <div class="row g-3 search-form align-items-start">
          <div class="col-12 col-sm-auto text-center">
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

          <div class="col-12 col-sm">
            <form
              style="max-width: 360px;"
              novalidate
              onsubmit={handleSearch}
            >
              <div class="mb-3 row align-items-start">
                <label for="search-focus" class="col-4 col-form-label">
                  <span class="d-inline-flex align-items-center gap-1">
                    Фокус ({focusLimit}):
                    <button
                      type="button"
                      class="btn btn-sm btn-outline-secondary rounded-circle p-0"
                      style="width: 1.1rem; height: 1.1rem; font-size: 0.7rem; line-height: 1;"
                      aria-label="Подсказка по формату фокуса"
                      title="Подсказка по формату"
                      onclick={toggleFocusHelp}
                    >?</button>
                  </span>
                </label>
                <div class="col-8 position-relative">
                  <input
                    id="search-focus"
                    type="text"
                    class="form-control {focusError ? 'is-invalid' : ''}"
                    placeholder="max {focusLimit} id или тип"
                    autocapitalize="characters"
                    autocomplete="off"
                    spellcheck="false"
                    bind:value={focus}
                  />
                  {#if focusError}
                    <div class="invalid-feedback">
                      Неверный формат
                    </div>
                  {/if}

                  {#if showFocusHelp}
                    <div
                      class="position-absolute bg-white border rounded shadow-sm p-2 small"
                      style="top: calc(100% + 0.35rem); left: 0; z-index: 10; width: 320px; max-width: 90vw;"
                    >
                      <div class="d-flex justify-content-between align-items-start mb-2">
                        <strong>Формат поля «Фокус»</strong>
                        <button
                          type="button"
                          class="btn-close"
                          aria-label="Закрыть подсказку"
                          onclick={() => (showFocusHelp = false)}
                        ></button>
                      </div>
                      <p class="mb-2">
                        Укажите через запятую или пробел id/типы сущностей в любом регистре:
                      </p>
                      <ul>
                        <li>
                          <span class="badge text-bg-light border">[C|L|P]</span>
                          + (пусто | знак «<span class="badge text-bg-light border">?</span>» | 1–3 буквы/цифры)
                        </li>
                        <li>
                          <span class="badge text-bg-light border">T</span>
                          + (пусто | знак «<span class="badge text-bg-light border">?</span>» | 1–3 цифры)
                        </li>
                      </ul>

                      <p class="mb-1 fw-semibold">Примеры:</p>
                      <ul>
                        <li>t1 ckey p</li>
                        <li>pgrd, c</li>
                        <li>L2  t?</li>
                      </ul>
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
        </div>
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
    {:else if !locationState.searchAvailable}
      <p class="text-muted fst-italic mb-3">Улик не обнаружено</p>
    {/if}

    <button class="btn btn-outline-secondary mb-3" onclick={goBack}>
      ← Вернуться к карте
    </button>
  {/if}
</div>