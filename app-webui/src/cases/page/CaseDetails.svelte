<script>
  import * as caseFileApi from "../api/CaseFileApi.js";
  
  import UiErrorMessage from "@common/widget/UiErrorMessage.svelte"
  import UiLoadingIndicator from "@common/widget/UiLoadingIndicator.svelte"
  import UiLocalMenu from "@common/widget/UiLocalMenu.svelte";
  import UiLocalMenuItem from "@common/widget/UiLocalMenuItem.svelte";
  
  
  let { params = {} } = $props();
  const id = $derived(params.id);
  
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


<div class="container">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />
   <UiLocalMenu>
    <UiLocalMenuItem
      icon="fas fa-clipboard-check"
      title="Регистрация нового дела на базе сценария преступления"
      caption="Добавить дело"
      href="/cases/create"
    />
  </UiLocalMenu>
  <h1>Дело #{id}</h1>

</div>

<style>
</style>