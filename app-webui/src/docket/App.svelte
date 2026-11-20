<script>
  import Router from 'svelte-spa-router';
  import ScriptList from './page/ScriptList.svelte';
  import ScriptAdd from './page/ScriptAdd.svelte';
  import ScriptOverview from './page/ScriptOverview';
  import InvestigationStart from './page/InvestigationStart';
  import Investigation from './page/Investigation';
  import InvestigationSearch from './page/InvestigationSearch';

  
  const routes = {
    '/script': ScriptList,
	'/script/add': ScriptAdd,
	'/script/:id': ScriptOverview,
	'/investigation/start/:id': InvestigationStart,
	'/investigation/:id': Investigation,
	'/investigation/:investigationId/search/:entityId': InvestigationSearch,
	'/': ScriptList
  };
  
</script>

<Router {routes}  restoreScrollState={true} />

<style>
</style>