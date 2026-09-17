<script>
  import * as criminalCaseApi from "../api/CriminalCaseApi.js";
  
  let lastCases = criminalCaseApi.lastCases(1,1);  
</script>

<main>
  <h1>Cases</h1>
</main>

<style>
  main {
    padding: 2rem;
  }
</style>