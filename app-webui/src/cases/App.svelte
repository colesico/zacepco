<script>
  import Router from 'svelte-spa-router';
  import { link } from 'svelte-spa-router'; 
  import CaseList from './page/CaseList.svelte';
  import CreateCase from './page/CreateCase.svelte';

  
  const routes = {
    '/': CaseList,
	'/cases/create': CreateCase
  };
  
</script>

<Router {routes} hashMode={false} />

<style>
</style>