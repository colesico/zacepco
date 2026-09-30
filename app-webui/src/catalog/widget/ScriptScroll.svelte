<script>
  import ScriptCard from './ScriptCard.svelte';

  let { 
    entries = [], 
    target = '_self' 
  } = $props();

 
  $effect(() => {
    console.log('Scripts: %o', scripts);
  });
</script>

<div class="dir-card-panel mt-3">
  {#each entries as entry}
    <ScriptCard {entry} {target} />
  {/each}
</div>

<style>
  .dir-card-panel {
    display: flex;
    flex-wrap: wrap;
    align-items: stretch;
    justify-content: center;
  }
  
  :global(.dir-card-panel a) {
    text-decoration: none;
    color: #43414E;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: stretch;
  }
</style>
