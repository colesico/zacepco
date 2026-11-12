<script>
  import Router from 'svelte-spa-router';
  import { link } from 'svelte-spa-router'; 
  import ScriptList from './page/ScriptList.svelte';
  import ScriptAdd from './page/ScriptAdd.svelte';
  import ScriptOverview from './page/ScriptOverview';
  import InvestigationStart from './page/InvestigationStart';
  import Investigation from './page/Investigation';

  
  const routes = {
    '/script': ScriptList,
	'/script/add': ScriptAdd,
	'/script/:id': ScriptOverview,
	'/investigation/start/:id': InvestigationStart,
	'/investigation/:id': Investigation,
	'/': ScriptList
  };
  
</script>

<Router {routes}/>

<style>
</style>