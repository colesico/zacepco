<script>
  import Router from 'svelte-spa-router';
  import { link } from 'svelte-spa-router'; 
  import Welcome from './page/Welcome.svelte';
  import UploadScript from './page/UploadScript.svelte';

  
  const routes = {
    '/': Welcome,
	'/script/upload': UploadScript
  };
  
</script>

<nav>
  <a href="/" use:link>Главная</a>
  <a href="/script/upload" use:link>Загрузить скрипт</a>
</nav>

<main>
  <Router {routes} />
</main>

<style>
</style>