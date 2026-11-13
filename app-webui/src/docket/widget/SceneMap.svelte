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

    <!-- подписи -->
    {#each Array(rows) as _, r}
      {#each Array(cols) as _, c}
        {@const loc = locIndex.get(`${r + 1}:${c + 1}`)}
        {#if loc}
          <text
            x={c + 0.5}
            y={r + 0.5}
            text-anchor="middle"
            dominant-baseline="central"
            font-family="system-ui, sans-serif"
            font-weight="bold"
            font-size="0.15"
            fill="#000000"
            stroke="#ffffff"
            stroke-width="0.02"
            paint-order="stroke fill"
            pointer-events="none"
          >{loc.id}</text>
        {/if}
      {/each}
    {/each}
  </svg>
{/if}