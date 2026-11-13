<script>
  let { context, onLocationClick, onWayClick, selectedId = null } = $props();

  const scene = $derived(context.script.crime.scene);
  const rows = $derived(scene.size.rows);
  const cols = $derived(scene.size.cols);

  let internalSelection = $state({ type: null, id: null });
  const activeSelection = $derived(
    selectedId ? { type: 'location', id: selectedId } : internalSelection
  );

  const investigationLocations = $derived(
    context.investigation?.state?.scene?.locations ?? {}
  );

  const cluesIndex = $derived(context.script?.crime?.clues ?? {});

  const allLocIndex = $derived(
    new Map(
      Object.values(scene.locations)
        .filter((l) => l.pos)
        .map((l) => {
          const inv = investigationLocations[l.id] || {};
          return {
            ...l,
            hidden: inv.hidden ?? l.hidden,
            exposedClues: Array.isArray(inv.exposedClues)
              ? inv.exposedClues
              : (l.exposedClues ?? [])
          };
        })
        .map((l) => [`${l.pos.row}:${l.pos.col}`, l])
    )
  );

  const locIndex = $derived(
    new Map(
      Array.from(allLocIndex.entries()).filter(([, l]) => !l.hidden)
    )
  );

  const locById = $derived(
    new Map(Object.values(scene.locations).map((l) => [l.id, l]))
  );

  const gridPath = $derived.by(() => {
    const parts = [];
    for (let r = 0; r <= rows; r++) parts.push(`M0 ${r}H${cols}`);
    for (let c = 0; c <= cols; c++) parts.push(`M${c} 0V${rows}`);
    return parts.join('');
  });

  const waysToDraw = $derived.by(() => {
    const ways = scene.ways ?? {};
    const result = [];
    for (const [wayKey, way] of Object.entries(ways)) {
      if (way.hidden) continue;
      const [aId, bId] = way.locations ?? [];
      if (!aId || !bId) continue;

      const a = locById.get(aId);
      const b = locById.get(bId);
      if (!a?.pos || !b?.pos) continue;

      const aHidden = (investigationLocations[aId]?.hidden ?? a.hidden) === true;
      const bHidden = (investigationLocations[bId]?.hidden ?? b.hidden) === true;
      if (aHidden || bHidden) continue;

      const ar = a.pos.row, ac = a.pos.col;
      const br = b.pos.row, bc = b.pos.col;

      let mx, my, vertical;
      if (ar === br && Math.abs(ac - bc) === 1) {
        mx = Math.min(ac, bc);
        my = ar - 0.5;
        vertical = false;
      } else if (ac === bc && Math.abs(ar - br) === 1) {
        mx = ac - 0.5;
        my = Math.min(ar, br);
        vertical = true;
      } else {
        continue;
      }

      const description = way.description ?? '';
      result.push({
        way,
        wayKey,
        key: way.id?.key ?? '',
        description,
        clickable: description.trim().length > 0,
        mx, my, vertical
      });
    }
    return result;
  });

  const cellsToFrame = $derived(
    Array.from(locIndex.values()).map((l) => ({
      id: l.id,
      x: l.pos.col - 1,
      y: l.pos.row - 1
    }))
  );

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
  const CHAR_W = 0.62;

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

  function pillWidth(text, fontSize, padX) {
    return text.length * fontSize * CHAR_W + padX * 2;
  }
  function labelWidth(text) { return pillWidth(text, FONT_SIZE, PAD_X); }

  function fitName(text) {
    const charW = FONT_SIZE * CHAR_W;
    const maxChars = Math.floor((NAME_WIDTH - PAD_X * 2) / charW);
    if (text.length <= maxChars) return text;
    return text.slice(0, Math.max(1, maxChars - 1)) + '…';
  }

  function fitClueName(clueId, name, maxWidth) {
    if (!name) return '';
    const charW = CLUE_FONT_SIZE * CHAR_W;
    const maxChars = Math.floor((maxWidth - CLUE_PAD_X * 2) / charW);
    const prefixLen = clueId.length + CLUE_SEP.length;
    const room = maxChars - prefixLen;
    if (room <= 0) return '';
    if (name.length <= room) return name;
    return name.slice(0, Math.max(1, room - 1)) + '…';
  }

  function clueLabelWidth(clueId, namePart) {
    const text = namePart ? clueId + CLUE_SEP + namePart : clueId;
    return pillWidth(text, CLUE_FONT_SIZE, CLUE_PAD_X);
  }

  function visibleClues(clues) {
    if (clues.length <= CLUES_MAX) {
      return { shown: clues, more: false };
    }
    return { shown: clues.slice(0, Math.max(0, CLUES_MAX - 1)), more: true };
  }

  function isLocationSelected(locId) {
    return activeSelection.type === 'location' && activeSelection.id === locId;
  }

  function isWaySelected(wayKey) {
    return activeSelection.type === 'way' && activeSelection.id === wayKey;
  }

  function handleLocationClick(locId) {
    internalSelection = { type: 'location', id: locId };
    onLocationClick?.(locId);
  }

  function handleLocationKeydown(e, locId) {
    if (e.key === 'Enter' || e.key === ' ') {
      e.preventDefault();
      internalSelection = { type: 'location', id: locId };
      onLocationClick?.(locId);
    }
  }

  function handleWayClick(wayKey, way) {
    internalSelection = { type: 'way', id: wayKey };
    onWayClick?.(way);
  }

  function handleWayKeydown(e, wayKey, way) {
    if (e.key === 'Enter' || e.key === ' ') {
      e.preventDefault();
      internalSelection = { type: 'way', id: wayKey };
      onWayClick?.(way);
    }
  }

  // ——— theme constants ———
  const MAP_BG = '#f2f2f2';
  const FRAME_COLOR = '#2a2a2a';
  const WAY_BORDER_COLOR = '#2a2a2a';
  const WAY_ARROW_COLOR = '#555555';
  const WAY_CLICKABLE_ARROW_COLOR = '#2fa84f';
  const WAY_FILL = MAP_BG;
  const WAY_FILL_OPACITY = 0.70;
  const SELECTED_COLOR = '#f5b400';
  const HIDDEN_QUESTION_COLOR = '#7a7a7a';

  // Way marker geometry.
  // The long side runs along the cell boundary and takes 30% of its length:
  // WAY_HALF_Y = 0.15 → full marker length = 0.30 = 30% of the cell edge.
  // The narrow side runs along the passage: WAY_HALF_X = 0.055.
  const WAY_HALF_X = 0.055;
  const WAY_HALF_Y = 0.150;
  const WAY_RX     = 0.020;
  const WAY_ARROW_PATH =
    'M-0.045 0L-0.005 -0.026L-0.005 0.026Z' +
    'M0.045 0L0.005 -0.026L0.005 0.026Z';
</script>

{#if rows > 0 && cols > 0}
  <svg
    viewBox="0 0 {cols} {rows}"
    width="100%"
    preserveAspectRatio="xMidYMid meet"
    xmlns="http://www.w3.org/2000/svg"
  >
    <!-- background -->
    <rect x="0" y="0" width={cols} height={rows} fill={MAP_BG} />

    <!-- location cells: image for visible, "?" for hidden -->
    {#each Array(rows) as _, r}
      {#each Array(cols) as _, c}
        {@const loc = allLocIndex.get(`${r + 1}:${c + 1}`)}
        {#if loc}
          {#if loc.hidden}
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
              href="/docket/script/images/thumb/{context.investigation.scriptId}/{loc.id}"
              x={c + CARD_INSET}
              y={r + CARD_INSET}
              width={1 - CARD_INSET * 2}
              height={1 - CARD_INSET * 2}
              preserveAspectRatio="xMidYMid slice"
              class="location-image"
              role="button"
              tabindex="0"
              onclick={() => handleLocationClick(loc.id)}
              onkeydown={(e) => handleLocationKeydown(e, loc.id)}
            >
              <title>{loc.name || 'Локация'}</title>
            </image>
          {/if}
        {/if}
      {/each}
    {/each}

    <!-- grid (does not intercept clicks) -->
    <path
      d={gridPath}
      fill="none"
      stroke="#8c8c8c"
      stroke-width="1"
      stroke-dasharray="4 4"
      vector-effect="non-scaling-stroke"
      pointer-events="none"
    />

    <!-- thin dark frame around each visible location cell (on the cell edge) -->
    {#each cellsToFrame as cell}
      <rect
        x={cell.x + CARD_INSET}
        y={cell.y + CARD_INSET}
        width={1 - CARD_INSET * 2}
        height={1 - CARD_INSET * 2}
        fill="none"
        stroke={FRAME_COLOR}
        stroke-width="1"
        vector-effect="non-scaling-stroke"
        pointer-events="none"
      />
    {/each}

    <!-- yellow highlight for the selected location -->
    {#each cellsToFrame as cell}
      {#if isLocationSelected(cell.id)}
        <rect
          x={cell.x + CARD_INSET}
          y={cell.y + CARD_INSET}
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

    <!-- passages (ways) — clickable ones differ by green arrow color -->
    {#each waysToDraw as w}
      <g
        class={w.clickable ? 'way-clickable' : 'way-static'}
        transform={`translate(${w.mx} ${w.my}) rotate(${w.vertical ? 90 : 0})`}
        role={w.clickable ? 'button' : undefined}
        tabindex={w.clickable ? '0' : undefined}
        onclick={w.clickable ? () => handleWayClick(w.wayKey, w.way) : undefined}
        onkeydown={w.clickable ? (e) => handleWayKeydown(e, w.wayKey, w.way) : undefined}
      >
        <title>{w.key}{w.description ? ' — ' + w.description : ''}</title>
        <rect
          x={-WAY_HALF_X}
          y={-WAY_HALF_Y}
          width={WAY_HALF_X * 2}
          height={WAY_HALF_Y * 2}
          rx={WAY_RX}
          ry={WAY_RX}
          fill={WAY_FILL}
          fill-opacity={WAY_FILL_OPACITY}
          stroke={WAY_BORDER_COLOR}
          stroke-width="1"
          vector-effect="non-scaling-stroke"
        />
        <path
          d={WAY_ARROW_PATH}
          fill={w.clickable ? WAY_CLICKABLE_ARROW_COLOR : WAY_ARROW_COLOR}
          stroke="none"
          pointer-events="none"
        />
        {#if isWaySelected(w.wayKey)}
          <rect
            x={-WAY_HALF_X}
            y={-WAY_HALF_Y}
            width={WAY_HALF_X * 2}
            height={WAY_HALF_Y * 2}
            rx={WAY_RX}
            ry={WAY_RX}
            fill="none"
            stroke={SELECTED_COLOR}
            stroke-width="3"
            vector-effect="non-scaling-stroke"
            pointer-events="none"
          />
        {/if}
      </g>
    {/each}

    <!-- labels: location id top-right, clue ids below it (left side), name bar at bottom -->
    {#each Array(rows) as _, r}
      {#each Array(cols) as _, c}
        {@const loc = locIndex.get(`${r + 1}:${c + 1}`)}
        {#if loc}
          {@const idW = labelWidth(loc.id)}
          {@const idX = c + 1 - LABEL_OFFSET - idW}
          {@const clueMaxW = 1 - CLUE_SIDE_OFFSET * 2}
          {@const { shown: shownClues, more: hasMoreClues } = visibleClues(loc.exposedClues)}

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
              font-weight={FONT_WEIGHT_ID}
              font-size={FONT_SIZE}
              fill="#ffffff"
            >{loc.id}</text>
          </g>

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
              />
              <text
                x={x + CLUE_PAD_X}
                y={y + CLUE_PAD_Y}
                text-anchor="start"
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

          {#if hasMoreClues}
            {@const dotsW = Math.max(CLUE_MORE_MIN_WIDTH, pillWidth(CLUE_MORE_TEXT, CLUE_FONT_SIZE, CLUE_PAD_X))}
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

          {#if loc.name}
            {@const fittedName = fitName(loc.name)}
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
    {/each}
  </svg>
{/if}

<style>
  .location-image {
    cursor: pointer;
    outline: none;
  }

  .location-image:focus-visible {
    outline: 2px solid #2563eb;
    outline-offset: 2px;
  }

  .location-image::-moz-focus-inner {
    border: 0;
  }

  .way-clickable {
    cursor: pointer;
    outline: none;
  }

  .way-clickable:focus-visible {
    outline: 2px solid #2563eb;
    outline-offset: 2px;
  }
</style>