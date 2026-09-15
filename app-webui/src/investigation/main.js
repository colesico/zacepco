import { mount } from 'svelte';
import App from './App.svelte';

// Mount the Svelte 5 application into the DOM container with id="app"
const app = mount(App, {
  target: document.getElementById('app'),
  props: {}
});

export default app;
