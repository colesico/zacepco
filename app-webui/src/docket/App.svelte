<script>
  import Router from 'svelte-spa-router';
  import { link } from 'svelte-spa-router'; 
  import ScriptList from './page/ScriptList.svelte';
  import ScriptRegistration from './page/ScriptRegistration.svelte';
  import ScriptOverview from './page/ScriptOverview';

  
  const routes = {
    '/scripts': ScriptList,
	'/scripts/add': ScriptRegistration,
	'/scripts/:id': ScriptOverview,
	'/': ScriptList
  };
  
</script>

<Router {routes}/>

<style>
</style>