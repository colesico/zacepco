<script>
  import Router from 'svelte-spa-router';
  import Welcome from './page/Welcome.svelte';

  
  const routes = {
    '/': Welcome
  };
  
</script>

<main>
  <Router {routes} />
</main>

<style>
</style>