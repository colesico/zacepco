<script>
  import * as scriptEntryApi from "../api/ScriptEntryApi.js";
  
  import UiErrorMessage from "@common/widget/UiErrorMessage.svelte"
  import UiLoadingIndicator from "@common/widget/UiLoadingIndicator.svelte"
  import UiLocalMenu from "@common/widget/UiLocalMenu.svelte";
  import UiLocalMenuItem from "@common/widget/UiLocalMenuItem.svelte";
  
  
  import ScriptScroll from "@casereg/widget/ScriptScroll"

  let error = $state(null);
  let loading = $state(true);
  
  let scripts = $state([]);
  
  $inspect(error); 
  $inspect(scripts); 


  $effect(() => {
    async function loadData() {
      try {
        scripts = await scriptEntryApi.listCasebooks(100,0);
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
      href="/casereg/registration"
    />
  </UiLocalMenu>
  <h1>Реестр дел</h1>
  <CaseScroll {cases} />
</div>

<style>
</style>