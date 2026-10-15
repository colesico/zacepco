<script>
  import Router from 'svelte-spa-router';
  import { link } from 'svelte-spa-router'; 
  import CaseList from './page/CaseList.svelte';
  import CreateCase from './page/CreateCase.svelte';
  import CaseOverview from './page/CaseOverview';

  
  const routes = {
    '/': CaseList,
	'/casereg/create': CreateCase,
	'/casereg/:id': CaseOverview
  };
  
</script>

<Router {routes}/>

<style>
</style>