<script>
  import * as scriptEntryApi from "../api/ScriptEntryApi.js";
  import UiErrorMessage from "@common/widget/UiErrorMessage.svelte"

  let error = $state(null);
  let loading = $state(true);
  
  let entries = $state([]);
  
   $inspect(error); 

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
  <h1>Criminal cases</h1>
  <UiErrorMessage {error} />

  <table class="table">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">First</th>
        <th scope="col">Last</th>
        <th scope="col">Handle</th>
      </tr>
    </thead>
    <tbody>
	  {#each entries as entry}
        <tr>
          <th scope="row">{entry}</th>
          <td>{entry}</td>
          <td>{entry}</td>
          <td>{entry}</td>
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