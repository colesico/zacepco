<script>
  let {
    inspectionResult = null,
    context = null,
  } = $props();

  const INSPECTION_LABELS = {
    SEARCH: 'Обыск',
    INTERROGATION: 'Допрос',
    EXAMINATION: 'Экспертиза'
  };

  let modalEl = $state(null);

  const code = $derived(inspectionResult?.code ?? '');
  const result = $derived(inspectionResult?.result ?? null);
  const scriptId = $derived(context?.investigation?.scriptId);

  const detectiveId = $derived(result?.activeDetective?.id ?? '');
  const detective = $derived(
    detectiveId ? context?.script?.investigation?.detectives?.[detectiveId] : null
  );

  const clue = $derived(result?.clue ?? null);
  const assetId = $derived(clue?.asset ?? clue?.id ?? '');

  const inspectionType = $derived(result?.inspectionType ?? '');
  const inspectionTarget = $derived(result?.inspectionTarget ?? '');
  const inspectionLabel = $derived(INSPECTION_LABELS[inspectionType] ?? inspectionType ?? '?');

  const targetName = $derived.by(() => {
    if (!inspectionTarget) return '';
    const crime = context?.script?.crime;
    if (inspectionTarget.startsWith('L')) {
      return crime?.scene?.locations?.[inspectionTarget]?.name + ' (' + inspectionTarget + ')';
    }
    if (inspectionTarget.startsWith('P')) {
      return crime?.personages?.[inspectionTarget]?.name + ' (' + inspectionTarget + ')';
    }
    return inspectionTarget;
  });

  const formattedFocus = $derived.by(() => {
    const f = result?.detectiveFocus;
    const ids = Array.isArray(f?.ids) ? f.ids : [];
    const types = Array.isArray(f?.types) ? f.types : [];
    return [...ids, ...types].join(', ');
  });

  const matchedFocusText = $derived.by(() => {
    const ids = Array.isArray(result?.matchedIds) ? result.matchedIds : [];
    const types = Array.isArray(result?.matchedTypes) ? result.matchedTypes : [];
    return [...ids, ...types].join(', ');
  });

  const unmatchedFocusText = $derived.by(() => {
    const types = Array.isArray(result?.unmatchedTypes) ? result.unmatchedTypes : [];
    return types.join(', ');
  });

  function resolveEntityName(id) {
    const crime = context?.script?.crime;
    if (!id || !crime) return id;
    if (id.startsWith('L')) return crime.scene?.locations?.[id]?.name ?? id;
    if (id.startsWith('P')) return crime.personages?.[id]?.name ?? id;
    if (id.startsWith('C')) return crime.clues?.[id]?.name ?? id;
    return id;
  }

  function resolveUncoveredItem(rawItem) {
    const crime = context?.script?.crime;

    const id = String(rawItem ?? '');

    if (id.startsWith('L')) {
      const loc = crime?.scene?.locations?.[id] ?? {};
      const pos = loc.pos ?? {};
      return {
        type: 'L',
        id,
        assetId: id,
        name: loc.name ?? id,
        description: loc.description ?? '',
        position: `[${pos.col ?? '?'}, ${pos.row ?? '?'}]`
      };
    }

    if (id.startsWith('P')) {
      const pers = crime?.personages?.[id] ?? {};
      return {
        type: 'P',
        id,
        assetId: id,
        name: pers.name ?? id,
        description: pers.description ?? ''
      };
    }

    if (id.startsWith('C')) {
      const c = crime?.clues?.[id] ?? {};
      return {
        type: 'C',
        id,
        assetId: c.asset ?? id,
        name: c.name ?? id,
        description: c.description ?? ''
      };
    }

    if (id.startsWith('W')) {
      const way = crime?.scene?.ways?.[id] ?? {};
      const locs =  way.locations;
      const fromId = locs[0] ?? '?';
      const toId = locs[1] ?? '?';
      return {
        type: 'W',
        id,
        fromId,
        toId,
        fromName: resolveEntityName(fromId),
        toName: resolveEntityName(toId),
        description: way.description ?? ''
      };
    }

    return { type: 'OTHER', id };
  }

  const uncoveredItems = $derived(
    Array.isArray(clue?.uncovers) ? clue.uncovers.map(resolveUncoveredItem) : []
  );

  export function show() {
    if (modalEl && window.bootstrap) {
      window.bootstrap.Modal.getOrCreateInstance(modalEl).show();
    }
  }
</script>

<div
  bind:this={modalEl}
  class="modal fade"
  tabindex="-1"
  aria-labelledby="inspectionResultModalLabel"
  aria-hidden="true"
>
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="inspectionResultModalLabel">
          {inspectionLabel}{targetName ? `: ${targetName}` : ''}
        </h5>
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="modal"
          aria-label="Закрыть"
        ></button>
      </div>

      <div class="modal-body">
        <div class="d-flex flex-wrap align-items-center gap-2 mb-3">
          {#if detectiveId}
            <span class="d-inline-flex align-items-center gap-2">
              {#if scriptId}
                <img
                  src="/docket/script/images/thumb/{scriptId}/{detectiveId}"
                  alt={detective?.name ?? detectiveId}
                  class="rounded border text-muted flex-shrink-0"
                  style="width: 32px; height: 32px; object-fit: cover;"
                />
              {/if}
              <span class="fw-semibold">{detective?.name ?? detectiveId}</span>
            </span>
            <span class="text-muted">·</span>
          {/if}
          <span>Фокус: <strong>{formattedFocus || '—'}</strong></span>
          {#if result?.d12 != null}
            <span class="text-muted">·</span>
            <span>D12: <strong>{result.d12}</strong></span>
          {/if}
        </div>
	
	 {#if result?.awardedPoints != null}
		<div class="mb-2 d-flex align-items-center gap-2">
		  <i class="fa-solid fa-medal text-warning"></i>
		  <span>Начислено очков: <strong>{result.awardedPoints}</strong></span>
		</div>
	  {/if}

	  {#if result?.budgetSpend != null || result?.currentBudget != null}
		<div class="mb-2 d-flex align-items-center gap-2">
		  <i class="fa-solid fa-coins text-success"></i>
		  <span>
			Потрачено бюджета: <strong>{result?.budgetSpend ?? 0}</strong>, остаток: <strong>{result?.currentBudget ?? 0}</strong>
		  </span>
		</div>
	  {/if}

	  {#if matchedFocusText || unmatchedFocusText}
		<div class="mb-3 d-flex align-items-center gap-2">
		  <i class="fa-solid fa-crosshairs text-info"></i>
		  <span>
			Соответствие фокуса:
			{#if matchedFocusText} <strong class="text-success">{matchedFocusText}</strong>{/if}
			{#if matchedFocusText && unmatchedFocusText}, {/if}
			{#if unmatchedFocusText} <strong class="text-danger">{unmatchedFocusText}</strong>{/if}
		  </span>
		</div>
	  {/if}

        {#if code === 'INVALID_D12'}
          <p class="mb-0 text-muted">Неверное значение D12</p>
        {:else if code === 'FOCUS_LIMIT_EXCEEDED'}
          <p class="mb-0 text-muted">
            В фокусе детектива слишком много элементов, максимум: {result?.focusLimit}
          </p>
        {:else if code === 'UNDISCOVERED_FOCUS'}
          <p class="mb-0 text-muted">
            В фокусе детектива ранее не открытые сущности{Array.isArray(result?.undiscoveredFocus) && result.undiscoveredFocus.length
              ? `: ${result.undiscoveredFocus.join(', ')}`
              : '.'}
          </p>
        {:else if code === 'INSUFFICIENT_BUDGET'}
          <p class="mb-0 text-muted">Недостаточно бюджета расследования</p>
        {:else if code === 'CLUES_EXHAUSTED'}
          <p class="mb-0 text-muted">Этот источник улик исчерпан.</p>
        {:else if code === 'CLUES_NOT_FOUND' || code === 'NOTHING_FOUND'}
          <p class="mb-0 text-muted">Новых улик не обнаружено.</p>
        {:else if code === 'CLUE_DISCOVERED' || code === 'CLUE_FOCUS_DISCOVERED'}
         

          <p class="mb-2 text-muted">
            {code === 'CLUE_DISCOVERED'
              ? 'Случайно обнаружена подозрительная улика:'
              : 'Целенаправленно обнаружена улика:'}
          </p>

          {#if clue}
            <div class="d-flex align-items-center gap-3 p-2 rounded border bg-light">
              <div
                class="position-relative flex-shrink-0"
                style="width: 64px; height: 64px;"
              >
                {#if scriptId && assetId}
                  <img
                    src="/docket/script/images/thumb/{scriptId}/{assetId}"
                    alt={clue.name ?? clue.id}
                    class="w-100 h-100 rounded border object-fit-cover d-block"
                  />
                {:else}
                  <div
                    class="w-100 h-100 rounded border bg-secondary-subtle d-flex align-items-center justify-content-center text-muted small"
                  >
                    ?
                  </div>
                {/if}
                <span
                  class="badge text-bg-secondary position-absolute top-0 start-0 m-1"
                  style="font-size: 0.65em; z-index: 2;"
                >
                  {clue.id}
                </span>
              </div>

              <div class="min-w-0">
                <div class="fw-semibold text-break">{clue.name ?? clue.id}</div>
                {#if clue.description}
                  <div class="small text-muted text-break">{clue.description}</div>
                {/if}
              </div>
            </div>

            {#if uncoveredItems.length > 0}
              <div class="mt-3">
                <div class="mb-2 text-muted">Также открывает:</div>
                <div class="d-flex flex-column gap-2">
                  {#each uncoveredItems as item}
                    {#if item.type === 'L' || item.type === 'C' || item.type === 'P'}
                      <div class="d-flex align-items-center gap-3 p-2 rounded border bg-light">
                        <div
                          class="position-relative flex-shrink-0"
                          style="width: 64px; height: 64px;"
                        >
                          {#if scriptId && item.assetId}
                            <img
                              src="/docket/script/images/thumb/{scriptId}/{item.assetId}"
                              alt={item.name}
                              class="w-100 h-100 rounded border object-fit-cover d-block"
                            />
                          {:else}
                            <div
                              class="w-100 h-100 rounded border bg-secondary-subtle d-flex align-items-center justify-content-center text-muted small"
                            >
                              ?
                            </div>
                          {/if}
                          <span
                            class="badge text-bg-secondary position-absolute top-0 start-0 m-1"
                            style="font-size: 0.65em; z-index: 2;"
                          >
                            {item.id}
                          </span>
                        </div>

                        <div class="min-w-0">
                          <div class="fw-semibold text-break">{item.name}</div>
                          {#if item.description}
                            <div class="small text-muted text-break">{item.description}</div>
                          {/if}
                          {#if item.type === 'L' && item.position}
                            <div class="small text-secondary mt-1">
                              Столбцец и строка: <strong>{item.position}</strong>
                            </div>
                          {/if}
                        </div>
                      </div>
                    {:else if item.type === 'W'}
                      <div class="p-2 rounded border bg-light">
                        <div class="fw-semibold">
                          Путь [{item.fromName} — {item.toName}]
                        </div>
                        {#if item.description}
                          <div class="small text-muted text-break">{item.description}</div>
                        {/if}
                      </div>
                    {:else}
                      <div><strong>{item.id}</strong></div>
                    {/if}
                  {/each}
                </div>
              </div>
            {/if}
          {/if}
        {:else if code}
          <p class="mb-0 text-muted">Неизвестный код результата: {code}</p>
        {/if}
      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">
          ОК
        </button>
      </div>
    </div>
  </div>
</div>
