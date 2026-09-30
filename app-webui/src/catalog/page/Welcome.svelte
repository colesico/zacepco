<script>
  import * as scriptEntryApi from "../api/ScriptEntryApi.js";
  
  import UiErrorMessage from "@common/widget/UiErrorMessage.svelte"
  import UiLoadingIndicator from "@common/widget/UiLoadingIndicator.svelte"

  let error = $state(null);
  let loading = $state(true);
  
  let entries = $state([]);
  
  $inspect(error); 
  $inspect(entries); 


  $effect(() => {
    async function loadData() {
      try {
        entries = await scriptEntryApi.listScriptEntries(100,0);
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

  <table class="table">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Название</th>
        <th scope="col">Авторы</th>
        <th scope="col">Дата создания</th>
		<th scope="col">Версия</th>
      </tr>
    </thead>
    <tbody>
	  {#each entries as entry}
        <tr>
          <th scope="row">{entry.id}</th>
          <td>{entry.scriptInfo.title}</td>
          <td>{entry.scriptInfo.authors}</td>
		    <td>{entry.scriptInfo.creationDate}</td>
          <td>{entry.scriptInfo.version}</td>
        </tr>
      {:else}
        <tr>
          <td colspan="4" class="text-center">Нет доступных кейсов</td>
        </tr>
      {/each}
    </tbody>
  </table>
</main>

<style>
  main {
    padding: 2rem;
  }
</style>