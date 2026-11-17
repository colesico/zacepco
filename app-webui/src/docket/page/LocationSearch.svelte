<script>
  import { push } from 'svelte-spa-router';
  import UiErrorMessage from '@core/widget/UiErrorMessage.svelte';
  import UiLoadingIndicator from '@core/widget/UiLoadingIndicator.svelte';
  import * as investigationApi from '../api/InvestigationApi.js';

  let { params = {} } = $props();
  const invId = $derived(params.invId);
  const locId = $derived(params.locId);

  let error = $state(null);
  let loading = $state(true);
  let context = $state(null);

  // локация — derived из контекста
  const location = $derived(
    context?.script?.crime?.scene?.locations?.[locId] ?? null
  );

  function goBack() {
    push(`/investigation/${invId}`);
  }

  $effect(() => {
    let cancelled = false;

    async function loadData() {
      loading = true;
      error = null;
      try {
        const c = await investigationApi.findInvestigationContext(invId);
        if (cancelled) return;
        context = c;
      } catch (ex) {
        if (cancelled) return;
        error = ex?.data ?? ex;
        context = null;
      } finally {
        if (!cancelled) loading = false;
      }
    }

    loadData();

    return () => {
      cancelled = true;
    };
  });
</script>

<div class="container">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />

  {#if context}
    <h1>
        Локация: {location.name}
        <span class="badge text-bg-secondary" style="font-size: 0.5em;">
          {locId}
        </span>
    </h1>

    {#if location}

      <button class="btn btn-outline-secondary mb-3" onclick={goBack}>
        ← Назад на карту
      </button>

      <!-- Тут дальше — ваш контент по локации:
           соседние проходы, улики, действия и т.д. -->
    {:else}
      <div class="alert alert-warning">
        Локация <code>{locId}</code> не найдена в этом расследовании.
        <button class="btn btn-link p-0 align-baseline" onclick={goBack}>
          Вернуться на карту
        </button>
      </div>
    {/if}
  {/if}
</div>