<script>

  import * as scriptApi from "../api/ScriptApi.js";

  import { link } from 'svelte-spa-router';
  
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
        scripts = await scriptApi.listScripts(100,0);
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
      icon="fas fa-folder-plus"
      title="Регистрация сценария в реестре"
      caption="Добавить сценарий"
      href="/script/add"
    />
  </UiLocalMenu>
  <h1>Сценарии</h1>
  <ScriptScroll {scripts} />
</div>

<style>
</style>