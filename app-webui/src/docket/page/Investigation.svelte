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
  let context = $state(null);

  $effect(() => {
    let cancelled = false;
    async function loadData() {
      loading = true;
      error = null;
      try {
        const c  = await investigationApi.findInvestigationContext(id);
		context = c;
        console.log(context);
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

  {#if context}
    <h1>Дело "{context.script.metainfo.title}" #{context.script.metainfo.uid}</h1>
    <SceneMap {context} />
  {/if}
</div>