<script>
  import Router from 'svelte-spa-router';
  import { link } from 'svelte-spa-router'; 
  import ScriptList from './page/ScriptList.svelte';
  import ScriptRegistration from './page/ScriptRegistration.svelte';
  import ScriptOverview from './page/ScriptOverview';

  
  const routes = {
    '/': ScriptList,
	'/registration': ScriptRegistration,
	'/:id': ScriptOverview
  };
  
</script>

<Router {routes}/>

<style>
</style>