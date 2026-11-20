<script>
  let {
    context,
    cols = 4,
    rows = 1,
    onPersonageClick,
    selectedId = null
  } = $props();

  const personagesMap = $derived(context.script?.crime?.personages ?? {});
  const investigationPersonages = $derived(
    context.investigation?.state?.personages ?? {}
  );
  const investigationClues = $derived(
    context.investigation?.state?.clues ?? {}
  );
  const cluesIndex = $derived(context.script?.crime?.clues ?? {});

  let internalSelection = $state(null);
  const activeSelectedId = $derived(selectedId ?? internalSelection);

  const personagesList = $derived(
    Object.values(personagesMap).map((p) => {
      const inv = investigationPersonages[p.id] || {};
      const staticClues = Array.isArray(p.clues) ? p.clues : [];
      const exposedClues = staticClues.filter(
        (cid) => investigationClues[cid]?.discovered === true
      );
      return {
        ...p,
        hidden: inv.hidden ?? p.hidden,
        searchAvailable: inv.searchAvailable,
        interrogationAvailable: inv.interrogationAvailable,
        exposedClues
      };
    })
  );

  const totalSlots = $derived(Math.max(0, rows * cols));
  const visiblePersonages = $derived(personagesList.slice(0, totalSlots));

  const gridPath = $derived.by(() => {
    const parts = [];
    for (let r = 0; r <= rows; r++) parts.push(`M0 ${r}H${cols}`);
    for (let c = 0; c <= cols; c++) parts.push(`M${c} 0V${rows}`);
    return parts.join('');
  });

  // ——— layout constants ———
  const CARD_INSET = 0;
  const LABEL_OFFSET = CARD_INSET;
  const CLUE_SIDE_OFFSET = 0.03;
  const NAME_SIDE_MARGIN = 0.03;

  const FONT_FAMILY = 'system-ui, sans-serif';
  const FONT_SIZE = 0.062;
  const FONT_WEIGHT_ID = 600;
  const FONT_WEIGHT_NAME = 400;

  const PAD_X = 0.022;
  const PAD_Y = 0.010;
  const PILL_H = FONT_SIZE + PAD_Y * 2;

  const LABEL_GAP = 0.01;
  const CLUE_TOP = LABEL_OFFSET + PILL_H + LABEL_GAP;

  const NAME_BOTTOM_MARGIN = 0.10;
  const NAME_WIDTH = 1 - NAME_SIDE_MARGIN * 2;
  const NAME_PAD_Y = 0.018;
  const NAME_PILL_H = FONT_SIZE + NAME_PAD_Y * 2;

  const CLUE_FONT_SIZE = FONT_SIZE;
  const CLUE_PAD_X = PAD_X;
  const CLUE_PAD_Y = PAD_Y;
  const CLUE_PILL_H = CLUE_FONT_SIZE + CLUE_PAD_Y * 2;
  const CLUE_STEP_Y = CLUE_PILL_H + LABEL_GAP;

  const CLUE_SEP = ' · ';
  const CLUE_MORE_TEXT = '…';
  const CLUE_MORE_MIN_WIDTH = 0.08;

  const CLUES_AVAILABLE_HEIGHT = 1 - NAME_BOTTOM_MARGIN - NAME_PILL_H - CLUE_TOP;
  const CLUES_MAX = Math.max(
    1,
    Math.floor((CLUES_AVAILABLE_HEIGHT - CLUE_PILL_H) / CLUE_STEP_Y) + 1
  );

  // ——— availability icons (magnifying glass & speech bubble) ———
  const BADGE_SIZE = 0.088;
  const BADGE_PAD = 0.014;
  const BADGE_GAP = 0.014;
  const BADGE_STEP_Y = BADGE_SIZE + BADGE_PAD * 2 + BADGE_GAP;
  const MAGNIFYING_GLASS_PATH =
    'M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z';
  const SPEECH_BUBBLE_PATH =
    'M512 240c0 114.9-114.6 208-256 208c-37.1 0-72.3-6.4-104.1-17.9c-11.9 8.7-31.3 20.6-54.3 30.6C73.6 471.1 44.7 480 16 480c-6.5 0-12.3-3.9-14.8-9.9c-2.5-6-1.1-12.8 3.4-17.4c0 0 0 0 0 0s0 0 0 0s0 0 0 0c0 0 0 0 0 0l.3-.3c.3-.3 .7-.7 1.3-1.4c1.1-1.2 2.8-3.1 4.9-5.7c4.1-5 9.6-12.4 15.2-21.6c10-16.6 19.5-38.4 21.4-62.9C17.7 326.8 0 285.1 0 240C0 125.1 114.6 32 256 32s256 93.1 256 208z';

  // ——— точный расчёт ширины текста через Canvas ———
  let _measureCtx = null;
  const _measureCache = new Map();

  function measureTextWidth(text, fontSize, weight) {
    if (!text) return 0;

    if (typeof document === 'undefined') {
      let w = 0;
      for (const ch of text) {
        if (ch >= 'A' && ch <= 'Z') w += 0.68;
        else if (ch >= 'a' && ch <= 'z') w += 0.50;
        else if (ch >= '0' && ch <= '9') w += 0.60;
        else if (ch === ' ') w += 0.28;
        else w += 0.55;
      }
      return w * fontSize;
    }

    const key = `${weight}|${fontSize}|${text}`;
    const cached = _measureCache.get(key);
    if (cached !== undefined) return cached;

    if (!_measureCtx) {
      _measureCtx = document.createElement('canvas').getContext('2d');
    }

    const BASE = 100;
    _measureCtx.font = `${weight} ${BASE}px ${FONT_FAMILY}`;
    const w = _measureCtx.measureText(text).width * (fontSize / BASE);

    _measureCache.set(key, w);
    return w;
  }

  function labelWidth(text) {
    return measureTextWidth(text, FONT_SIZE, FONT_WEIGHT_NAME) + PAD_X * 2;
  }

  function fitName(text) {
    const available = NAME_WIDTH - PAD_X * 2;
    if (measureTextWidth(text, FONT_SIZE, FONT_WEIGHT_NAME) <= available) {
      return text;
    }
    let lo = 0, hi = text.length;
    while (lo < hi) {
      const mid = Math.ceil((lo + hi) / 2);
      const w = measureTextWidth(
        text.slice(0, mid) + '…',
        FONT_SIZE,
        FONT_WEIGHT_NAME
      );
      if (w <= available) lo = mid;
      else hi = mid - 1;
    }
    return text.slice(0, lo) + '…';
  }

  function fitClueName(clueId, name, maxWidth) {
    if (!name) return '';
    const prefixW =
      measureTextWidth(clueId, CLUE_FONT_SIZE, FONT_WEIGHT_ID) +
      measureTextWidth(CLUE_SEP, CLUE_FONT_SIZE, FONT_WEIGHT_NAME);
    const available = maxWidth - CLUE_PAD_X * 2 - prefixW;
    if (available <= 0) return '';

    if (measureTextWidth(name, CLUE_FONT_SIZE, FONT_WEIGHT_NAME) <= available) {
      return name;
    }
    let lo = 0, hi = name.length;
    while (lo < hi) {
      const mid = Math.ceil((lo + hi) / 2);
      const w = measureTextWidth(
        name.slice(0, mid) + '…',
        CLUE_FONT_SIZE,
        FONT_WEIGHT_NAME
      );
      if (w <= available) lo = mid;
      else hi = mid - 1;
    }
    return name.slice(0, lo) + '…';
  }

  function clueLabelWidth(clueId, namePart) {
    const idW = measureTextWidth(clueId, CLUE_FONT_SIZE, FONT_WEIGHT_ID);
    const nameW = namePart
      ? measureTextWidth(CLUE_SEP + namePart, CLUE_FONT_SIZE, FONT_WEIGHT_NAME)
      : 0;
    return idW + nameW + CLUE_PAD_X * 2;
  }

  function visibleClues(clues) {
    if (clues.length <= CLUES_MAX) {
      return { shown: clues, more: false };
    }
    return { shown: clues.slice(0, Math.max(0, CLUES_MAX - 1)), more: true };
  }

  function handlePersonageClick(personageId) {
    internalSelection = personageId;
    onPersonageClick?.(personageId);
  }

  function handlePersonageKeydown(e, personageId) {
    if (e.key === 'Enter' || e.key === ' ') {
      e.preventDefault();
      internalSelection = personageId;
      onPersonageClick?.(personageId);
    }
  }

  function handleBackgroundClick() {
    internalSelection = null;
  }

  // ——— theme constants ———
  const BAR_BG = '#f2f2f2';
  const FRAME_COLOR = '#2a2a2a';
  const SELECTED_COLOR = '#f5b400';
  const HIDDEN_QUESTION_COLOR = '#7a7a7a';
  const ACTION_AVAILABLE_COLOR = '#28a745';
  const ACTION_UNAVAILABLE_COLOR = '#dc3545';

  const MAX_CELL_PX = 240;
</script>

{#if rows > 0 && cols > 0}
  <div
    class="personages-wrap"
    style={`--cols: ${cols}; --rows: ${rows}; --max-cell: ${MAX_CELL_PX}px;`}
  >
    <svg
      viewBox="0 0 {cols} {rows}"
      preserveAspectRatio="xMidYMid meet"
      xmlns="http://www.w3.org/2000/svg"
    >
      <defs>
        <filter
          id="personage-clue-pill-shadow"
          x="-30%"
          y="-30%"
          width="160%"
          height="160%"
        >
          <feDropShadow
            dx="0"
            dy="0.008"
            stdDeviation="0.008"
            flood-color="#000000"
            flood-opacity="0.35"
          />
        </filter>
      </defs>

      <!-- Фон сетки -->
      <rect
        x="0"
        y="0"
        width={cols}
        height={rows}
        fill={BAR_BG}
        style="cursor: default;"
        onclick={handleBackgroundClick}
      />

      <!-- Слой портретов и скрытых карточек -->
      {#each visiblePersonages as personage, idx (personage.id)}
        {@const r = Math.floor(idx / cols)}
        {@const c = idx % cols}
        {#if personage.hidden}
          <text
            x={c + 0.5}
            y={r + 0.5}
            text-anchor="middle"
            dominant-baseline="middle"
            font-family={FONT_FAMILY}
            font-weight={700}
            font-size="0.45"
            fill={HIDDEN_QUESTION_COLOR}
            pointer-events="none"
          >?</text>
        {:else}
          <image
            href="/docket/script/images/thumb/{context.investigation.scriptId}/{personage.id}"
            x={c + CARD_INSET}
            y={r + CARD_INSET}
            width={1 - CARD_INSET * 2}
            height={1 - CARD_INSET * 2}
            preserveAspectRatio="xMidYMid slice"
            class="personage-image"
            role="button"
            tabindex="0"
            onclick={() => handlePersonageClick(personage.id)}
            onkeydown={(e) => handlePersonageKeydown(e, personage.id)}
          >
            <title>{personage.name || 'Персонаж'}</title>
          </image>
        {/if}
      {/each}

      <!-- Пунктирная сетка -->
      <path
        d={gridPath}
        fill="none"
        stroke="#8c8c8c"
        stroke-width="1"
        stroke-dasharray="4 4"
        vector-effect="non-scaling-stroke"
        pointer-events="none"
      />

      <!-- Рамки занятых ячеек -->
      {#each visiblePersonages as personage, idx (personage.id)}
        {@const r = Math.floor(idx / cols)}
        {@const c = idx % cols}
        {#if !personage.hidden}
          <rect
            x={c + CARD_INSET}
            y={r + CARD_INSET}
            width={1 - CARD_INSET * 2}
            height={1 - CARD_INSET * 2}
            fill="none"
            stroke={FRAME_COLOR}
            stroke-width="1"
            vector-effect="non-scaling-stroke"
            pointer-events="none"
          />
        {/if}
      {/each}

      <!-- Рамка выделенного персонажа -->
      {#each visiblePersonages as personage, idx (personage.id)}
        {@const r = Math.floor(idx / cols)}
        {@const c = idx % cols}
        {#if !personage.hidden && activeSelectedId === personage.id}
          <rect
            x={c + CARD_INSET}
            y={r + CARD_INSET}
            width={1 - CARD_INSET * 2}
            height={1 - CARD_INSET * 2}
            fill="none"
            stroke={SELECTED_COLOR}
            stroke-width="3"
            vector-effect="non-scaling-stroke"
            pointer-events="none"
          />
        {/if}
      {/each}

      <!-- Плашки ID, индикаторы обыска/допроса, открытые улики и имена -->
      {#each visiblePersonages as personage, idx (personage.id)}
        {@const r = Math.floor(idx / cols)}
        {@const c = idx % cols}
        {#if !personage.hidden}
          {@const idW = labelWidth(personage.id)}
          {@const idX = c + 1 - LABEL_OFFSET - idW}
          {@const clueMaxW = 1 - CLUE_SIDE_OFFSET * 2}
          {@const { shown: shownClues, more: hasMoreClues } = visibleClues(personage.exposedClues)}
          {@const badgeScale = BADGE_SIZE / 512}
          {@const badgeBoxSize = BADGE_SIZE + BADGE_PAD * 2}
          {@const badgeBoxX = idX + (idW - badgeBoxSize) / 2}
          {@const badgesBaseY = r + LABEL_OFFSET + PILL_H + BADGE_GAP}
          {@const hasSearch = personage.searchAvailable !== undefined}
          {@const hasInterrogation = personage.interrogationAvailable !== undefined}

          <!-- ID персонажа (правый верхний угол) -->
          <g pointer-events="none">
            <rect
              x={idX}
              y={r + LABEL_OFFSET}
              width={idW}
              height={PILL_H}
              rx="0.015"
              fill="#1a1a1a"
              fill-opacity="0.7"
            />
            <text
              x={idX + idW / 2}
              y={r + LABEL_OFFSET + PAD_Y}
              text-anchor="middle"
              dominant-baseline="hanging"
              font-family={FONT_FAMILY}
              font-weight={FONT_WEIGHT_NAME}
              font-size={FONT_SIZE}
              fill="#ffffff"
            >{personage.id}</text>
          </g>

          <!-- Индикатор доступности обыска (лупа) -->
          {#if hasSearch}
            {@const searchBoxY = badgesBaseY}
            {@const searchIconX = badgeBoxX + BADGE_PAD}
            {@const searchIconY = searchBoxY + BADGE_PAD}
            <g pointer-events="none">
              <rect
                x={badgeBoxX}
                y={searchBoxY}
                width={badgeBoxSize}
                height={badgeBoxSize}
                rx="0.015"
                fill="#1a1a1a"
                fill-opacity="0.7"
              />
              <g transform={`translate(${searchIconX} ${searchIconY}) scale(${badgeScale})`}>
                <path
                  d={MAGNIFYING_GLASS_PATH}
                  fill={personage.searchAvailable
                    ? ACTION_AVAILABLE_COLOR
                    : ACTION_UNAVAILABLE_COLOR}
                />
              </g>
            </g>
          {/if}

          <!-- Индикатор доступности допроса (облачко) -->
          {#if hasInterrogation}
            {@const interrBoxY = badgesBaseY + (hasSearch ? BADGE_STEP_Y : 0)}
            {@const interrIconX = badgeBoxX + BADGE_PAD}
            {@const interrIconY = interrBoxY + BADGE_PAD}
            <g pointer-events="none">
              <rect
                x={badgeBoxX}
                y={interrBoxY}
                width={badgeBoxSize}
                height={badgeBoxSize}
                rx="0.015"
                fill="#1a1a1a"
                fill-opacity="0.7"
              />
              <g transform={`translate(${interrIconX} ${interrIconY}) scale(${badgeScale})`}>
                <path
                  d={SPEECH_BUBBLE_PATH}
                  fill={personage.interrogationAvailable
                    ? ACTION_AVAILABLE_COLOR
                    : ACTION_UNAVAILABLE_COLOR}
                />
              </g>
            </g>
          {/if}

          <!-- Плашки открытых улик -->
          {#each shownClues as clueId, i}
            {@const clueName = cluesIndex[clueId]?.name ?? ''}
            {@const namePart = fitClueName(clueId, clueName, clueMaxW)}
            {@const w = clueLabelWidth(clueId, namePart)}
            {@const x = c + CLUE_SIDE_OFFSET}
            {@const y = r + CLUE_TOP + i * CLUE_STEP_Y}
            <g pointer-events="none">
              <rect
                x={x}
                y={y}
                width={w}
                height={CLUE_PILL_H}
                rx="0.015"
                fill="#ffe89a"
                fill-opacity="0.92"
                stroke="#b8941f"
                stroke-width="0.005"
                filter="url(#personage-clue-pill-shadow)"
              />
              <text
                x={x + w / 2}
                y={y + CLUE_PAD_Y}
                text-anchor="middle"
                dominant-baseline="hanging"
                font-family={FONT_FAMILY}
                font-size={CLUE_FONT_SIZE}
                fill="#7a5f00"
              >
                <tspan font-weight={FONT_WEIGHT_ID}>{clueId}</tspan>
                {#if namePart}
                  <tspan font-weight={FONT_WEIGHT_NAME} fill-opacity="0.85">{CLUE_SEP}{namePart}</tspan>
                {/if}
              </text>
            </g>
          {/each}

          <!-- Индикатор «…» если открытых улик больше, чем помещается -->
          {#if hasMoreClues}
            {@const dotsW = Math.max(
              CLUE_MORE_MIN_WIDTH,
              measureTextWidth(CLUE_MORE_TEXT, CLUE_FONT_SIZE, FONT_WEIGHT_ID) + CLUE_PAD_X * 2
            )}
            {@const dotsX = c + CLUE_SIDE_OFFSET}
            {@const dotsY = r + CLUE_TOP + shownClues.length * CLUE_STEP_Y}
            <g pointer-events="none">
              <rect
                x={dotsX}
                y={dotsY}
                width={dotsW}
                height={CLUE_PILL_H}
                rx="0.015"
                fill="#ffe89a"
                fill-opacity="0.92"
                stroke="#b8941f"
                stroke-width="0.005"
                filter="url(#personage-clue-pill-shadow)"
              />
              <text
                x={dotsX + dotsW / 2}
                y={dotsY + CLUE_PAD_Y}
                text-anchor="middle"
                dominant-baseline="hanging"
                font-family={FONT_FAMILY}
                font-size={CLUE_FONT_SIZE}
                font-weight={FONT_WEIGHT_ID}
                fill="#7a5f00"
              >{CLUE_MORE_TEXT}</text>
            </g>
          {/if}

          <!-- Имя персонажа (внизу по центру) -->
          {#if personage.name}
            {@const fittedName = fitName(personage.name)}
            {@const nx = c + NAME_SIDE_MARGIN}
            {@const ny = r + 1 - NAME_BOTTOM_MARGIN - NAME_PILL_H}
            <g pointer-events="none">
              <rect
                x={nx}
                y={ny}
                width={NAME_WIDTH}
                height={NAME_PILL_H}
                rx="0.015"
                fill="#1a1a1a"
                fill-opacity="0.7"
              />
              <text
                x={c + 0.5}
                y={ny + NAME_PAD_Y}
                text-anchor="middle"
                dominant-baseline="hanging"
                font-family={FONT_FAMILY}
                font-weight={FONT_WEIGHT_NAME}
                font-size={FONT_SIZE}
                fill="#ffffff"
              >{fittedName}</text>
            </g>
          {/if}
        {/if}
      {/each}
    </svg>
  </div>
{/if}

<style>
  .personages-wrap {
    width: 100%;
    max-width: calc(var(--cols) * var(--max-cell));
    aspect-ratio: var(--cols) / var(--rows);
    margin: 0 auto;
  }

  .personages-wrap > svg {
    display: block;
    width: 100%;
    height: 100%;
  }

  .personage-image {
    cursor: pointer;
    outline: none;
  }

  .personage-image:focus-visible {
    outline: 2px solid #2563eb;
    outline-offset: 2px;
  }

  .personage-image::-moz-focus-inner {
    border: 0;
  }
</style>
