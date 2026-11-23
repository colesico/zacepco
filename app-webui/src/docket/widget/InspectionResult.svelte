<script>
  let {
    inspectionResult = null,
    inspectionMode = null,
    inspectionTarget = null,
	context = null,
  } = $props();

  const MODE_LABELS = {
    search: 'Обыск',
    interrogation: 'Допрос',
    examination: 'Экспертиза'
  };

  let modalEl = $state(null);

  const result = $derived(inspectionResult?.result ?? null);
  const scriptId = $derived(context.investigation.scriptId);
  
  const code = $derived(result?.code ?? '');
  const clue = $derived(result?.clue ?? null);
  const assetId = $derived(clue?.id ?? '');
  
  const modeLabel = $derived(MODE_LABELS[inspectionMode] ?? inspectionMode ?? '');

  const formattedFocus = $derived.by(() => {
    const f = inspectionResult?.focus;
    const ids = Array.isArray(f?.ids) ? f.ids : [];
    const types = Array.isArray(f?.types) ? f.types : [];
    return [...ids, ...types].join(', ');
  });

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
          {modeLabel} {inspectionTarget}
        </h5>
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="modal"
          aria-label="Закрыть"
        ></button>
      </div>

      <div class="modal-body">
        <div class="mb-3">
          Для фокуса: <strong>{formattedFocus || '—'}</strong> 
          D12: <strong>{inspectionResult?.d12 ?? ''}</strong>
        </div>

        {#if code === 'INVALID_D12'}
          <p class="mb-0 text-muted">Неверное значение D12</p>
        {:else if code === 'FOCUS_LIMIT_EXCEEDED'}
          <p class="mb-0 text-muted">
            В фокусе детектива слишком много элементов, максимум : {result.focusLimit}
          </p>
        {:else if code === 'UNDISCOVERED_FOCUS'}
          <p class="mb-0 text-muted">В фокусе детектива ранее не открытые сущности:</p>
        {:else if code === 'INSUFFICIENT_BUDGET'}
          <p class="mb-0 text-muted">Недостаточно бюджета расследования</p>
        {:else if code === 'CLUES_EXHAUSTED'}
          <p class="mb-0 text-muted">Этот источник улик исчерпан.</p>
        {:else if code === 'CLUE_DISCOVERED' || code === 'CLUE_FOCUS_DISCOVERED'}
          <p class="mb-2 text-muted">
            {code === 'CLUE_DISCOVERED'
              ? 'Случайно обнаружена улика:'
              : 'Обнаружена фокусная улика:'}
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
