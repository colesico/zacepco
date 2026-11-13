<script>
  let { context } = $props();

  const scene = $derived(context.script.crime.scene);
  const rows = $derived(scene.size.rows);
  const cols = $derived(scene.size.cols);

  // Runtime state of locations from the investigation (hidden/exposedClues may differ from the script)
  const investigationLocations = $derived(
    context.investigation?.state?.scene?.locations ?? {}
  );

  // Clue names from the script — used as tooltips on the clue badges
  const cluesIndex = $derived(context.script?.crime?.clues ?? {});

  // Locations (non-hidden) keyed by "row:col", with runtime state applied
  const locIndex = $derived(
    new Map(
      Object.values(scene.locations)
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
        .filter((l) => !l.hidden)
        .map((l) => [`${l.pos.row}:${l.pos.col}`, l])
    )
  );

  // Locations keyed by id (including hidden ones) — needed to resolve way endpoints
  const locById = $derived(
    new Map(Object.values(scene.locations).map((l) => [l.id, l]))
  );

  const gridPath = $derived.by(() => {
    const parts = [];
    for (let r = 0; r <= rows; r++) parts.push(`M0 ${r}H${cols}`);
    for (let c = 0; c <= cols; c++) parts.push(`M${c} 0V${rows}`);
    return parts.join('');
  });

  // Passages (ways) to draw as rounded squares with double-headed arrows
  const waysToDraw = $derived.by(() => {
    const ways = scene.ways ?? {};
    const result = [];
    for (const way of Object.values(ways)) {
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

      result.push({
        key: way.id?.key ?? '',
        description: way.description ?? '',
        mx, my, vertical
      });
    }
    return result;
  });

  // ——— pill geometry ———
  // Location ID (top-left) and location name (bottom) — identical style & size.
  // Size is the medium between previous name (0.055) and previous ID (0.07).
  const FONT_SIZE = 0.062;
  const PAD_X = 0.022;
  const PAD_Y = 0.010;
  const PILL_H = FONT_SIZE + PAD_Y * 2;
  const CHAR_W = 0.62;

  // Placement
  const LABEL_OFFSET = 0.02;        // inset from top-left of the cell for the ID pill
  const NAME_BOTTOM_MARGIN = 0.10;  // raised above way markers (marker half = 0.08)
  const LABEL_GAP = 0.01;
  const NAME_MAX_WIDTH = 0.96;

  // Clue badges — same pill language, matching height
  const CLUE_FONT_SIZE = FONT_SIZE;
  const CLUE_PAD_X = PAD_X;
  const CLUE_PAD_Y = PAD_Y;
  const CLUE_PILL_H = CLUE_FONT_SIZE + CLUE_PAD_Y * 2;
  const CLUE_STEP_Y = CLUE_PILL_H + LABEL_GAP;

  function pillWidth(text, fontSize, padX) {
    return text.length * fontSize * CHAR_W + padX * 2;
  }

  function labelWidth(text) {
    return pillWidth(text, FONT_SIZE, PAD_X);
  }

  function clueWidth(text) {
    return pillWidth(text, CLUE_FONT_SIZE, CLUE_PAD_X);
  }

  function fitName(text) {
    const charW = FONT_SIZE * CHAR_W;
    const maxChars = Math.floor((NAME_MAX_WIDTH - PAD_X * 2) / charW);
    if (text.length <= maxChars) return text;
    return text.slice(0, Math.max(1, maxChars - 1)) + '…';
  }

  // Way marker geometry — rounded square, thin border, thin sharp double arrow
  const WAY_HALF = 0.08;
  const WAY_SIDE = WAY_HALF * 2;
  const WAY_RX = 0.026;
  const WAY_ARROW_PATH =
    'M-0.06 0L0.06 0' +
    'M-0.06 0L-0.034-0.011M-0.06 0L-0.034 0.011' +
    'M0.06 0L0.034-0.011M0.06 0L0.034 0.011';
</script>

{#if rows > 0 && cols > 0}
  <svg
    viewBox="0 0 {cols} {rows}"
    width="100%"
    preserveAspectRatio="xMidYMid meet"
    xmlns="http://www.w3.org/2000/svg"
  >
    <!-- background -->
    <rect x="0" y="0" width={cols} height={rows} fill="#f2f2f2" />

    <!-- location images -->
    {#each Array(rows) as _, r}
      {#each Array(cols) as _, c}
        {@const loc = locIndex.get(`${r + 1}:${c + 1}`)}
        {#if loc}
          <image
            href="/docket/script/images/thumb/{context.investigation.scriptId}/{loc.id}"
            x={c}
            y={r}
            width="1"
            height="1"
            preserveAspectRatio="xMidYMid slice"
          >
            <title>{loc.name || 'Локация'}</title>
          </image>
        {/if}
      {/each}
    {/each}

    <!-- grid -->
    <path
      d={gridPath}
      fill="none"
      stroke="#8c8c8c"
      stroke-width="1"
      stroke-dasharray="4 4"
      vector-effect="non-scaling-stroke"
    />

    <!-- passages (ways) as rounded squares with thin, sharp double arrows -->
    {#each waysToDraw as w}
      <g transform={`translate(${w.mx} ${w.my}) rotate(${w.vertical ? 90 : 0})`}>
        <title>{w.key}{w.description ? ' — ' + w.description : ''}</title>
        <rect
          x={-WAY_HALF}
          y={-WAY_HALF}
          width={WAY_SIDE}
          height={WAY_SIDE}
          rx={WAY_RX}
          ry={WAY_RX}
          fill="#ffffff"
          fill-opacity="0.95"
          stroke="#4a4a4a"
          stroke-width="0.005"
        />
        <path
          d={WAY_ARROW_PATH}
          fill="none"
          stroke="#2a2a2a"
          stroke-width="0.005"
          stroke-linecap="round"
          stroke-linejoin="round"
          pointer-events="none"
        />
      </g>
    {/each}

    <!-- labels: location id top-left, clue ids top-right, name at bottom -->
    {#each Array(rows) as _, r}
      {#each Array(cols) as _, c}
        {@const loc = locIndex.get(`${r + 1}:${c + 1}`)}
        {#if loc}
          <!-- location id (top-left, dark pill — same style as the name) -->
          {@const idW = labelWidth(loc.id)}
          <g pointer-events="none">
            <rect
              x={c + LABEL_OFFSET}
              y={r + LABEL_OFFSET}
              width={idW}
              height={PILL_H}
              rx="0.015"
              fill="#1a1a1a"
              fill-opacity="0.7"
            />
            <text
              x={c + LABEL_OFFSET + PAD_X}
              y={r + LABEL_OFFSET + PAD_Y}
              text-anchor="start"
              dominant-baseline="hanging"
              font-family="system-ui, sans-serif"
              font-weight="500"
              font-size={FONT_SIZE}
              fill="#ffffff"
            >{loc.id}</text>
          </g>

          <!-- clue ids (top-right, stacked vertically, hover shows clue name) -->
          {#each loc.exposedClues as clueId, i}
            {@const w = clueWidth(clueId)}
            {@const x = c + 1 - LABEL_OFFSET - w}
            {@const y = r + LABEL_OFFSET + i * CLUE_STEP_Y}
            <g>
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
              >
                <title>{cluesIndex[clueId]?.name || clueId}</title>
              </rect>
              <text
                x={x + w - CLUE_PAD_X}
                y={y + CLUE_PAD_Y}
                text-anchor="end"
                dominant-baseline="hanging"
                font-family="system-ui, sans-serif"
                font-weight="500"
                font-size={CLUE_FONT_SIZE}
                fill="#7a5f00"
                pointer-events="none"
              >{clueId}</text>
            </g>
          {/each}

          <!-- location name (bottom center, raised to clear the way markers) -->
          {#if loc.name}
            {@const fittedName = fitName(loc.name)}
            {@const nw = pillWidth(fittedName, FONT_SIZE, PAD_X)}
            {@const nx = c + 0.5 - nw / 2}
            {@const ny = r + 1 - NAME_BOTTOM_MARGIN - PILL_H}
            <g pointer-events="none">
              <rect
                x={nx}
                y={ny}
                width={nw}
                height={PILL_H}
                rx="0.015"
                fill="#1a1a1a"
                fill-opacity="0.7"
              />
              <text
                x={c + 0.5}
                y={ny + PAD_Y}
                text-anchor="middle"
                dominant-baseline="hanging"
                font-family="system-ui, sans-serif"
                font-weight="500"
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