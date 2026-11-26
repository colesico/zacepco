<script>
  let { context } = $props();

  let isOpen = $state(false);
  let wayId = $state(null);

  const way = $derived(wayId ? context?.script?.crime?.scene?.ways?.[wayId] : null);
  const locations = $derived(
    (way?.locations ?? []).map((locId) => ({
      id: locId,
      name: context?.script?.crime?.scene?.locations?.[locId]?.name
    }))
  );

  export function open(id) {
    wayId = id;
    isOpen = true;
  }

  export function close() {
    isOpen = false;
  }

  function handleKeydown(event) {
    if (isOpen && event.key === 'Escape') {
      close();
    }
  }
</script>

<svelte:window onkeydown={handleKeydown} />

{#if isOpen}
  <div class="modal-backdrop fade show"></div>

  <div
    class="modal fade show d-block"
    tabindex="-1"
    role="dialog"
    aria-labelledby="wayInfoModalLabel"
    aria-modal="true"
    onclick={(e) => {
      if (e.target === e.currentTarget) close();
    }}
  >
    <div class="modal-dialog modal-dialog-centered modal-sm">
      <div class="modal-content shadow">
        <div class="modal-header">
          <h5 class="modal-title d-flex align-items-center gap-2" id="wayInfoModalLabel">
            <span>Проход</span>
            <span class="badge text-bg-secondary">{wayId}</span>
          </h5>
          <button
            type="button"
            class="btn-close"
            aria-label="Закрыть"
            onclick={close}
          ></button>
        </div>

        <div class="modal-body">
          {#if locations.length > 0}
            <div class="d-flex flex-wrap align-items-center gap-2 mb-3">
              {#each locations as loc, i (loc.id)}
                {#if i > 0}
                  <span class="text-muted">↔</span>
                {/if}
                <div class="d-inline-flex align-items-center gap-1">
                  <span class="badge text-bg-dark">{loc.id}</span>
                  {#if loc.name}
                    <span class="fw-medium">{loc.name}</span>
                  {/if}
                </div>
              {/each}
            </div>
          {/if}

          {#if way?.description}
            <p class="mb-0" style="white-space: pre-line;">{way.description}</p>
          {:else}
            <p class="text-muted mb-0 fst-italic">Описание отсутствует</p>
          {/if}
        </div>
      </div>
    </div>
  </div>
{/if}
