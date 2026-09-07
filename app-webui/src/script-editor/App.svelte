<script>
  // Using Svelte 5 Runes for state
  let counter = $state(0);
</script>

<main>
  <h1>Module Dashboard</h1>
  <p>Counter: {counter}</p>
  <button onclick={() => counter++}>Increment</button>
</main>

<style>
  main {
    padding: 2rem;
    font-family: sans-serif;
  }
</style>