<script>
  import * as casebookApi from "../api/CasebookApi.js";
  
  import UiErrorMessage from "@common/widget/UiErrorMessage.svelte"
  import UiLoadingIndicator from "@common/widget/UiLoadingIndicator.svelte"
  import UiLocalMenu from "@common/widget/UiLocalMenu.svelte";
  import UiLocalMenuItem from "@common/widget/UiLocalMenuItem.svelte";
  
  
  import CaseScroll from "@cases/widget/CaseScroll"

  let error = $state(null);
  let loading = $state(true);
  
  let cases = $state([]);
  
  $inspect(error); 
  $inspect(cases); 


  $effect(() => {
    async function loadData() {
      try {
        cases = await casebookApi.listCasebooks(100,0);
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
  <h1>Реестр дел</h1>
  <CaseScroll {cases} />
</div>

<style>
</style>