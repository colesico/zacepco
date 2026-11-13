<script>
  let { context } = $props();

  const scene = $derived(context.script.crime.scene);
  const rows = $derived(scene.size.rows);
  const cols = $derived(scene.size.cols);

  const locIndex = $derived(
    new Map(
      Object.values(scene.locations)
        .filter((l) => !l.hidden)
        .map((l) => [`${l.pos.row}:${l.pos.col}`, l])
    )
  );

  const gridPath = $derived.by(() => {
    const parts = [];
    for (let r = 0; r <= rows; r++) parts.push(`M0 ${r}H${cols}`);
    for (let c = 0; c <= cols; c++) parts.push(`M${c} 0V${rows}`);
    return parts.join('');
  });

  // Параметры метки id локации (координаты в единицах viewBox, где ячейка = 1×1)
  const LABEL_FONT_SIZE = 0.07;    // обычный, не крупный шрифт
  const LABEL_PAD_X = 0.025;       // горизонтальный отступ текста от края плашки
  const LABEL_PAD_Y = 0.015;       // вертикальный отступ
  const LABEL_OFFSET = 0.02;       // отступ плашки от края ячейки
  const LABEL_CHAR_WIDTH = LABEL_FONT_SIZE * 0.65; // приблизительная ширина символа
</script>

{#if rows > 0 && cols > 0}
  <svg
    viewBox="0 0 {cols} {rows}"
    width="100%"
    preserveAspectRatio="xMidYMid meet"
    xmlns="http://www.w3.org/2000/svg"
  >
    <!-- фон: бледно-серый -->
    <rect x="0" y="0" width={cols} height={rows} fill="#f2f2f2" />

    <!-- картинки локаций -->
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

    <!-- сетка: темнее фона -->
    <path
      d={gridPath}
      fill="none"
      stroke="#8c8c8c"
      stroke-width="1"
      stroke-dasharray="4 4"
      vector-effect="non-scaling-stroke"
    />

    <!-- подписи: id в верхнем левом углу, внутри закрашенной плашки -->
    {#each Array(rows) as _, r}
      {#each Array(cols) as _, c}
        {@const loc = locIndex.get(`${r + 1}:${c + 1}`)}
        {#if loc}
          {@const label = loc.id}
          {@const rectX = c + LABEL_OFFSET}
          {@const rectY = r + LABEL_OFFSET}
          {@const rectW = label.length * LABEL_CHAR_WIDTH + LABEL_PAD_X * 2}
          {@const rectH = LABEL_FONT_SIZE + LABEL_PAD_Y * 2}
          <g pointer-events="none">
            <!-- плашка под текст -->
            <rect
              x={rectX}
              y={rectY}
              width={rectW}
              height={rectH}
              rx="0.015"
              fill="#ffffff"
              fill-opacity="0.85"
              stroke="#8c8c8c"
              stroke-width="0.005"
            />
            <!-- сам id -->
            <text
              x={rectX + LABEL_PAD_X}
              y={rectY + LABEL_PAD_Y}
              text-anchor="start"
              dominant-baseline="hanging"
              font-family="system-ui, sans-serif"
              font-weight="400"
              font-size={LABEL_FONT_SIZE}
              fill="#333333"
            >{label}</text>
          </g>
        {/if}
      {/each}
    {/each}
  </svg>
{/if}