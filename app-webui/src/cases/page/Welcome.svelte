<script>
  import * as caseFileApi from "../api/CaseFileApi.js";
  
  import UiErrorMessage from "@common/widget/UiErrorMessage.svelte"
  import UiLoadingIndicator from "@common/widget/UiLoadingIndicator.svelte"
  
  import ScriptScroll from "@cases/widget/ScriptScroll.svelte"

  let error = $state(null);
  let loading = $state(true);
  
  let entries = $state([]);
  
  $inspect(error); 
  $inspect(entries); 


  $effect(() => {
    async function loadData() {
      try {
        entries = await caseFileApi.listCaseFiles(100,0);
      } catch (ex) {
        error = ex.data;
      } finally {
        loading = false;
      }
    }

    loadData();
  });
  
</script>


<main>
  <UiLoadingIndicator {loading} />	
  <h1>Criminal cases</h1>
  <UiErrorMessage {error} />
  <ScriptScroll {entries} />
</main>

<style>
  main {
    padding: 2rem;
  }
</style>