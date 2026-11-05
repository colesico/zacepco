<script>
  import Router from 'svelte-spa-router';
  import { link } from 'svelte-spa-router'; 
  import ScriptList from './page/ScriptList.svelte';
  import ScriptRegistration from './page/ScriptRegistration.svelte';
  import ScriptOverview from './page/ScriptOverview';
  import InvestigationStart from './page/InvestigationStart';
  import Investigation from './page/Investigation';

  
  const routes = {
    '/scripts': ScriptList,
	'/scripts/add': ScriptRegistration,
	'/scripts/:id': ScriptOverview,
	'/investigation/start/:id': InvestigationStart,
	'/investigation/:id': Investigation,
	'/': ScriptList
  };
  
</script>

<Router {routes}/>

<style>
</style>