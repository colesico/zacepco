import { mount } from 'svelte';
import App from './App.svelte';

// Mount the Svelte 5 application into the DOM container with id="app"
const app = mount(App, {
  target: document.getElementById('app'),
  // You can pass initial props here if your root component requires them
  props: {}
});

export default app;
