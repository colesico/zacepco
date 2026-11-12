<script>
  let { context } = $props();

  const scene = $derived(context.script.crime.scene);
  const rows = $derived(scene.size.rows);
  const cols = $derived(scene.size.cols);
</script>

{#if rows > 0 && cols > 0}
  <div class="map" style="--rows: {rows}; --cols: {cols}">
    {#each Array(rows) as _, r}
      {#each Array(cols) as _, c}
        {@const loc = Object.values(scene.locations).find(
          (l) => !l.hidden && l.pos.row === r + 1 && l.pos.col === c + 1
        )}
        <div class="cell">
          {#if loc}
            <img
              class="img"
              src="/docket/script/images/thumb/{context.investigation.scriptId}/{loc.id}"
              alt={loc.name || 'Локация'}
              title={loc.name || 'Локация'}
            />
            <span class="badge">{loc.id}</span>
          {/if}
        </div>
      {/each}
    {/each}
  </div>
{/if}

<style>
  .map {
    width: 100%;
    max-width: calc((100svh - 200px) * var(--cols) / var(--rows));
    aspect-ratio: var(--cols) / var(--rows);
    display: grid;
    grid-template-columns: repeat(var(--cols), 1fr);
    grid-template-rows: repeat(var(--rows), 1fr);
    gap: 1px;
    background: #c0c0c0;
    border: 1px solid #c0c0c0;
    margin: 20px auto 0;
  }

  .cell {
    position: relative;
    overflow: hidden;
    background: #fff;
  }

  .img {
    position: absolute;
    inset: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .badge {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    font-weight: bold;
    background: rgba(255, 255, 255, 0.7);
    padding: 2px 6px;
    border-radius: 4px;
  }
</style>