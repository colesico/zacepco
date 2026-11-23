<script>
  let { inspectionResult = null, inspectionMode = null, inspectionTarget=null } = $props();

  const MODE_LABELS = {
    search: 'Обыск',
    interrogation: 'Допрос',
    examination: 'Экспертиза'
  };

  let modalEl = $state(null);

  const result = $derived(inspectionResult?.result ?? null);
  const code = $derived(result?.code ?? '');
  const modeLabel = $derived(MODE_LABELS[inspectionMode] ?? inspectionMode ?? '');

  const formattedFocus = $derived.by(() => {
    const f = inspectionResult?.focus;
    const ids = Array.isArray(f.ids) ? f.ids : [];
    const types = Array.isArray(f.types) ? f.types : [];
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
          <br />
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
        {:else if code === 'CLUE_DISCOVERED'}
          <p class="mb-0 text-muted">Случайно обнаружена улика: </p>
        {:else if code === 'CLUE_FOCUS_DISCOVERED'}
          <p class="mb-0 text-muted">Обнаружена улика подходящая под фокус детектива.</p>
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
