<script>
  import { link } from 'svelte-spa-router';
   
  import ScriptCard from './ScriptCard';

  let { 
    scripts = [], 
    target = '_self',
    addUrl = '/scripts/add'   
  } = $props();

  $effect(() => {
    console.log('Scripts: %o', scripts);
  });
</script>


{#if scripts.length === 0}
<div >
  Сценариев не найдено.
  <a class="link-primary"  href={addUrl} {target}  use:link >Добавить сценарий</a>
</div>
{:else}
<div class="dir-card-panel mt-3">
  {#each scripts as script}
	  <ScriptCard {script} {target} />
  {/each}
</div>
{/if}


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