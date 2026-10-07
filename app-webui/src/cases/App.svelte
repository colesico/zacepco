<script>
  import Router from 'svelte-spa-router';
  import { link } from 'svelte-spa-router'; 
  import CaseList from './page/CaseList.svelte';
  import CreateCase from './page/CreateCase.svelte';
  import CaseDetails from './page/CaseDetails';

  
  const routes = {
    '/': CaseList,
	'/cases/create': CreateCase,
	'/cases/:id': CaseDetails
  };
  
</script>

<Router {routes}/>

<style>
</style>