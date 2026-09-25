<script>
  import * as scriptCatalogApi from "../api/ScriptEntryApi.js";
  
  let error;
  let loading = false;
  
		
</script>


<main>
  <h1>Criminal cases</h1>
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
      {#each lastCases as caseItem, index}
        <tr>
          <th scope="row">{index + 1}</th>
          <td>{caseItem.firstName}</td>
          <td>{caseItem.lastName}</td>
          <td>{caseItem.handle}</td>
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