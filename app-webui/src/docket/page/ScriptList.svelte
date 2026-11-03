<script>
  import * as scriptApi from "../api/ScriptApi.js";
  
  import UiErrorMessage from "@core/widget/UiErrorMessage.svelte"
  import UiLoadingIndicator from "@core/widget/UiLoadingIndicator.svelte"
  import UiLocalMenu from "@core/widget/UiLocalMenu.svelte";
  import UiLocalMenuItem from "@core/widget/UiLocalMenuItem.svelte";
  
  
  import ScriptScroll from "@docket/widget/ScriptScroll"

  let error = $state(null);
  let loading = $state(true);
  
  let scripts = $state([]);
  
  $inspect(error); 
  $inspect(scripts); 


  $effect(() => {
    async function loadData() {
      try {
        scripts = await scriptApi.listScriptEntries(100,0);
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
      href="/scripts/add"
    />
  </UiLocalMenu>
  <h1>Реестр дел</h1>
  <ScriptScroll {scripts} />
</div>

<style>
</style>