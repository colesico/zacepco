<script>
  let { context, inspectionMode = 'search', onInspect } = $props();

  const MODE_META = {
    search: {
      title: 'Обыск',
      buttonLabel: 'Обыскать',
      icon: 'fa-magnifying-glass'
    },
    interrogation: {
      title: 'Допрос',
      buttonLabel: 'Допросить',
      icon: 'fa-comment'
    },
    examination: {
      title: 'Экспертиза',
      buttonLabel: 'Провести экспертизу',
      icon: 'fa-microscope'
    }
  };

  const currentMeta = $derived(MODE_META[inspectionMode] ?? MODE_META.search);

  const modeConfig = $derived(
    context.script?.investigation?.[inspectionMode] ?? {}
  );

  const activeDetectiveId = $derived(
    context.investigation.state.activeDetectiveId
  );

  const activeDetective = $derived(
    context.script.investigation.detectives[activeDetectiveId]
  );

  const focusLimit = $derived(modeConfig.focusLimit);
  const actionCost = $derived(modeConfig.cost);
  const actionPoints = $derived(modeConfig.points);

  const budget = $derived(context.investigation.state.budget);

  const FOCUS_TOKEN = /^(?:[CcLlPp](?:\?|[a-zA-Z0-9]{1,3})?|[Tt](?:\?|\d{1,3})?)$/;

  let focus = $state('');
  let d12 = $state(null);

  let focusError = $state(false);
  let d12Error = $state(false);

  const focusTokens = $derived(
    focus.split(/[ ,]+/).filter(Boolean)
  );

  const focusValid = $derived(
    focusTokens.length > 0 &&
      (focusLimit === undefined || focusTokens.length <= focusLimit) &&
      focusTokens.every((t) => FOCUS_TOKEN.test(t))
  );

  const d12Valid = $derived(
    Number.isInteger(d12) && d12 >= 1 && d12 <= 12
  );

  function rollD12() {
    d12 = Math.floor(Math.random() * 12) + 1;
  }
  
  function handleFocusInput(e) {
    const target = e.currentTarget;
    const raw = target.value;
    const cursor = target.selectionStart ?? raw.length;
    const isAtEnd = cursor === raw.length;

    let formatted = raw.toUpperCase();

    if (isAtEnd) {
      formatted = formatted.replace(/([^\s,]{4})([^\s,])$/, '$1 $2');
    }

    const newCursor = isAtEnd ? formatted.length : cursor;

    focus = formatted;
    target.value = formatted;
    target.setSelectionRange(newCursor, newCursor);
  }

  function handleSubmit(e) {
    console.log("Handle submit");
	
    e.preventDefault();

    focusError = !focusValid;
    d12Error = !d12Valid;

    if (focusError || d12Error) return;

    onInspect?.(focus, Number(d12));
  }
</script>

<!-- <h2>{currentMeta.title}</h2> -->

<div class="d-flex flex-wrap align-items-center gap-2 small text-muted mb-2">
  <span class="d-inline-flex align-items-center gap-2 text-body">
    <img
      src="/docket/script/images/thumb/{context.investigation.scriptId}/{activeDetectiveId}"
      alt={activeDetective.name}
      class="rounded border text-muted flex-shrink-0"
      style="width: 32px; height: 32px; object-fit: cover;"
    />
    <span>{activeDetective.name}</span>
  </span>
  <span>·</span>
  <span>Стоимость: <strong>{actionCost}</strong></span>
  <span>·</span>
  <span>Очки: <strong>{actionPoints}</strong></span>
  <span>·</span>
  <span>Бюджет: <strong>{budget}</strong></span>
</div>

<div
  class="search-form-panel mb-3 p-3"
  style="background-color: #eaeaea; border: 1px solid #bdbdbd; border-radius: 0.25rem;"
>
  <form
    style="max-width: 360px;"
    novalidate
    onsubmit={handleSubmit}
  >
    <div class="mb-3 row align-items-start">
      <label for="inspect-focus-{inspectionMode}" class="col-4 col-form-label">
        <span class="d-inline-flex align-items-center gap-1">
          Фокус:
          <button
            type="button"
            class="btn btn-sm btn-outline-secondary rounded-circle d-inline-flex align-items-center justify-content-center p-0"
            style="width: 1.2rem; height: 1.2rem; font-size: 0.7rem;"
            data-bs-toggle="modal"
            data-bs-target="#focusHelpModal-{inspectionMode}"
            aria-label="Подсказка по формату фокуса"
            title="Подсказка по формату"
          >
            <i class="fas fa-info"></i>
          </button>
        </span>
      </label>
      <div class="col-8">
        <input
          id="inspect-focus-{inspectionMode}"
          type="text"
          class="form-control {focusError ? 'is-invalid' : ''}"
          placeholder="Лимит:{focusLimit}"
          autocapitalize="characters"
          autocomplete="off"
          spellcheck="false"
          value={focus}
          oninput={handleFocusInput}
        />
        {#if focusError}
          <div class="invalid-feedback">
            Неверный формат
          </div>
        {/if}
      </div>
    </div>

    <div class="mb-3 row align-items-start">
      <label for="inspect-d12-{inspectionMode}" class="col-4 col-form-label">D12:</label>
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
            id="inspect-d12-{inspectionMode}"
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
        <i class="fas {currentMeta.icon} me-2"></i>{currentMeta.buttonLabel}
      </button>
    </div>
  </form>
</div>

<!-- Bootstrap Modal с подсказкой по формату поля «Фокус» -->
<div
  class="modal fade"
  id="focusHelpModal-{inspectionMode}"
  tabindex="-1"
  aria-labelledby="focusHelpModalLabel-{inspectionMode}"
  aria-hidden="true"
>
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="focusHelpModalLabel-{inspectionMode}">
          Формат поля «Фокус»
        </h5>
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="modal"
          aria-label="Закрыть"
        ></button>
      </div>
      <div class="modal-body">
        <p class="mb-2">
          Укажите через запятую или пробел id сущностей или их типы (L,P,C,T):
        </p>
        <ul>
          <li>
            [L|P|C]
            + [пусто | знак '?' | 1–3 буквы/цифры]
          </li>
          <li>
            T + [пусто | знак "?" | 1–3 цифры)]
          </li>
        </ul>

        <p class="mb-1 fw-semibold">Примеры:</p>
        <ul class="mb-0">
          <li>T1 CKEY P</li>
          <li>PUSR, C</li>
          <li>L2  T? C?</li>
        </ul>
        <p class="mb-1 fw-semibold mt-1">Лимит фокуса:</p>
        Максимальное число элементов в фокусе (id или типов)
      </div>
    </div>
  </div>
</div>
