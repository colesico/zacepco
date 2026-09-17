<script>
  import Router from 'svelte-spa-router';
  import { link } from 'svelte-spa-router'; 
  import Welcome from './page/Welcome.svelte';
  import CriminalCases from './page/CriminalCases.svelte';

  
  const routes = {
    '/': Welcome,
	'/cases': CriminalCases
  };
  
</script>

<nav>
  <a href="/" use:link>Главная</a>
  <a href="/cases" use:link>Кейсы</a>
</nav>

<main>
  <Router {routes} />
</main>

<style>
</style>