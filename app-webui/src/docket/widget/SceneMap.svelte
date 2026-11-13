<script>
  let { context } = $props();

  const scene = $derived(context.script.crime.scene);
  const rows = $derived(scene.size.rows);
  const cols = $derived(scene.size.cols);

  const investigationLocations = $derived(
    context.investigation?.state?.scene?.locations ?? {}
  );

  const cluesIndex = $derived(context.script?.crime?.clues ?? {});

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

  const cellsToFrame = $derived(
    Array.from(locIndex.values()).map((l) => ({
      x: l.pos.col - 1,
      y: l.pos.row - 1
    }))
  );

  // ——— layout constants ———
  const CARD_INSET = 0;
  const LABEL_OFFSET = CARD_INSET;
  const CLUE_OFFSET = 0.03;
  const NAME_SIDE_MARGIN = 0.03;

  const FONT_SIZE = 0.062;
  const PAD_X = 0.022;
  const PAD_Y = 0.010;
  const PILL_H = FONT_SIZE + PAD_Y * 2;
  const CHAR_W = 0.62;

  const NAME_BOTTOM_MARGIN = 0.10;
  const LABEL_GAP = 0.01;
  const NAME_WIDTH = 1 - NAME_SIDE_MARGIN * 2;
  const NAME_PAD_Y = 0.018;
  const NAME_PILL_H = FONT_SIZE + NAME_PAD_Y * 2;

  const CLUE_FONT_SIZE = FONT_SIZE;
  const CLUE_PAD_X = PAD_X;
  const CLUE_PAD_Y = PAD_Y;
  const CLUE_PILL_H = CLUE_FONT_SIZE + CLUE_PAD_Y * 2;
  const CLUE_STEP_Y = CLUE_PILL_H + LABEL_GAP;

  function pillWidth(text, fontSize, padX) {
    return text.length * fontSize * CHAR_W + padX * 2;
  }
  function labelWidth(text) { return pillWidth(text, FONT_SIZE, PAD_X); }
  function clueWidth(text)  { return pillWidth(text, CLUE_FONT_SIZE, CLUE_PAD_X); }

  function fitName(text) {
    const charW = FONT_SIZE * CHAR_W;
    const maxChars = Math.floor((NAME_WIDTH - PAD_X * 2) / charW);
    if (text.length <= maxChars) return text;
    return text.slice(0, Math.max(1, maxChars - 1)) + '…';
  }

  // ——— theme constants ———
  const MAP_BG = '#f2f2f2';
  const FRAME_COLOR = '#2a2a2a';
  const WAY_BORDER_COLOR = '#2a2a2a';
  const WAY_ARROW_COLOR = '#555555';
  const WAY_FILL = MAP_BG;
  const WAY_FILL_OPACITY = 0.70;

  // Way marker geometry
  const WAY_HALF_X = 0.055;
  const WAY_HALF_Y = 0.110;
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

    <!-- location images — fill the whole cell -->
    {#each Array(rows) as _, r}
      {#each Array(cols) as _, c}
        {@const loc = locIndex.get(`${r + 1}:${c + 1}`)}
        {#if loc}
          <image
            href="/docket/script/images/thumb/{context.investigation.scriptId}/{loc.id}"
            x={c + CARD_INSET}
            y={r + CARD_INSET}
            width={1 - CARD_INSET * 2}
            height={1 - CARD_INSET * 2}
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

    <!-- passages (ways) — slightly translucent fill -->
    {#each waysToDraw as w}
      <g transform={`translate(${w.mx} ${w.my}) rotate(${w.vertical ? 90 : 0})`}>
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
          fill={WAY_ARROW_COLOR}
          stroke="none"
          pointer-events="none"
        />
      </g>
    {/each}

    <!-- labels: clue ids top-left, location id top-right, name bar at bottom -->
    {#each Array(rows) as _, r}
      {#each Array(cols) as _, c}
        {@const loc = locIndex.get(`${r + 1}:${c + 1}`)}
        {#if loc}
          <!-- location id (top-right, dark pill, text centered inside the pill) -->
          {@const idW = labelWidth(loc.id)}
          {@const idX = c + 1 - LABEL_OFFSET - idW}
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
              font-family="system-ui, sans-serif"
              font-weight="500"
              font-size={FONT_SIZE}
              fill="#ffffff"
            >{loc.id}</text>
          </g>

          <!-- clue ids (top-left, stacked vertically, hover shows clue name) -->
          {#each loc.exposedClues as clueId, i}
            {@const w = clueWidth(clueId)}
            {@const x = c + CLUE_OFFSET}
            {@const y = r + CLUE_OFFSET + i * CLUE_STEP_Y}
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
                x={x + CLUE_PAD_X}
                y={y + CLUE_PAD_Y}
                text-anchor="start"
                dominant-baseline="hanging"
                font-family="system-ui, sans-serif"
                font-weight="500"
                font-size={CLUE_FONT_SIZE}
                fill="#7a5f00"
                pointer-events="none"
              >{clueId}</text>
            </g>
          {/each}

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