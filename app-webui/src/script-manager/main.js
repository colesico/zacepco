import { mount } from 'svelte';
import App from './App.svelte';

// Mount the Svelte 5 application into the DOM container with id="root"
const app = mount(App, {
  target: document.getElementById('root'),
  // You can pass initial props here if your root component requires them
  props: {}
});

export default app;
