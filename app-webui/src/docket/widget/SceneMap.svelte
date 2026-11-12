<script>
  let { script, state } = $props();

  const scene  = $derived(script.crime.scene);
  const cols = $derived(scene.size.cols);
  const rows = $derived(scene.size.rows);

  const locIndex = $derived(
    new Map(
      Object.values(scene.locations)
        .filter((l) => !l.hidden)
        .map((l) => [`${l.pos.row}:${l.pos.col}`, l])
    )
  );
</script>

{#if rows > 0 && cols > 0}
  <div
    class="map-grid"
    style="
      --rows: {rows};
      --cols: {cols};
    "
  >
    {#each Array(rows) as _, r}
      {#each Array(cols) as _, c}
        {@const loc = locIndex.get(`${r + 1}:${c + 1}`)}
        <div class="location-cell">
          {#if loc}
            <img
              src="/docket/script/images/thumb/{context.scriptId}/{loc.id}"
              alt={loc.name || 'Локация'}
              title={loc.name || 'Локация'}
              class="location-image"
            />
            <span class="location-id">{loc.id}</span>
          {/if}
        </div>
      {/each}
    {/each}
  </div>
{/if}

<style>
  .map-grid {
    /* Сколько пикселей по высоте «съедают» h1, лоадер, отступы.
       Переопределяется через --map-chrome на любом предке. */
    --_chrome: var(--map-chrome, 200px);

    /* Ширина = min(доступная ширина, ширина при которой высота
       сетки ровно влезает во вьюпорт). svh — надёжнее на мобилках. */
    width: min(
      100%,
      calc((100vh - var(--_chrome)) * var(--cols) / var(--rows))
    );
    width: min(
      100%,
      calc((100svh - var(--_chrome)) * var(--cols) / var(--rows))
    );

    /* cols / rows → при 1fr×1fr ячейки идеальные квадраты */
    aspect-ratio: var(--cols) / var(--rows);

    display: grid;
    grid-template-columns: repeat(var(--cols, 1), 1fr);
    grid-template-rows: repeat(var(--rows, 1), 1fr);
    gap: 0;

    margin: 20px auto 0 auto;
    box-sizing: border-box;

    /* Внешний контур сетки */
    border-top: 1px dashed #c0c0c0;
    border-left: 1px dashed #c0c0c0;
  }

  .location-cell {
    position: relative;
    background: #ffffff;

    /* Внутренние линии: только справа и снизу — не двоятся */
    border-right: 1px dashed #c0c0c0;
    border-bottom: 1px dashed #c0c0c0;

    box-sizing: border-box;
    overflow: hidden;

    display: flex;
    align-items: center;
    justify-content: center;
  }

  .location-image {
    position: absolute;
    inset: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
    z-index: 1;
  }

  .location-id {
    font-weight: bold;
    position: relative;
    z-index: 2;
    background: rgba(255, 255, 255, 0.7);
    padding: 2px 6px;
    border-radius: 4px;
  }
</style>