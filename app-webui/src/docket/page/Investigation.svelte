<script>
  import { replace } from 'svelte-spa-router';
  import UiErrorMessage from '@core/widget/UiErrorMessage.svelte';
  import UiLoadingIndicator from '@core/widget/UiLoadingIndicator.svelte';
  import UiLabel from '@core/widget/UiLabel.svelte';
  import UiButton from '@core/widget/UiButton';
  import * as scriptApi from '../api/ScriptApi.js';
  import * as investigationApi from '../api/InvestigationApi.js';
  import SceneMap from '../widget/SceneMap.svelte';

  let { params = {} } = $props();
  const id = $derived(params.id);

  let error = $state(null);
  let loading = $state(true);
  let invEntry = $state(null);
  let state = $state(null);

  $effect(() => {
    let cancelled = false;
    async function loadData() {
      loading = true;
      error = null;
      try {
        invEntry = await investigationApi.findInvestigationEntry(id);
        state = invEntry.state;
        console.log(state);
      } catch (ex) {
        error = ex.data;
        overview = null;
      } finally {
        loading = false;
      }
    }
    loadData();
  });
</script>

<div class="container">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />

  {#if invEntry}
    <h1>Дело "{state.script.title}" #{state.script.id}</h1>

    {#if state?.scene}
      <SceneMap scene={state.scene} />
    {/if}
  {/if}
</div>