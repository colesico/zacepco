<script>
  import { replace } from 'svelte-spa-router';
  import UiErrorMessage from '@core/widget/UiErrorMessage.svelte';
  import UiLoadingIndicator from '@core/widget/UiLoadingIndicator.svelte';
  import UiLabel from '@core/widget/UiLabel.svelte';
  import UiButton from '@core/widget/UiButton';
  import * as scriptApi from '../api/ScriptApi.js';
  import * as investigationApi from '../api/InvestigationApi.js';

  let { params = {} } = $props();
  const id = $derived(params.id);

  let error = $state(null);
  let loading = $state(true);
  
  let invEntry = $state(null);
  let state = $state(null);

  $effect(() => {
    let cancelled = false;
    async function loadData() {
    
      loading = true;
      error = null;
	  
      try {
        invEntry = await investigationApi.findInvestigationEntry(id);
        state = invEntry.state;
        console.log(state);
      } catch (ex) {
        error = ex.data;
        overview = null;
      } finally {
        loading = false;
      }
    }
	
    loadData();

  });
</script>

<div class="container">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />
  {#if invEntry}
    <h1>Дело "{state.scriptTitle}" #{state.scriptId} </h1>
    
    <!-- Отрисовка прямоугольной сетки карты -->
    {#if state && state.scene && state.locations}
      <div 
        class="map-grid" 
        style="--rows: {state.scene.rows}; --cols: {state.scene.cols};"
      >
        {#each Object.values(state.locations) as loc (loc.id)}
          {#if !loc.hidden}
            <div 
              class="location-cell" 
              style="grid-row: {loc.pos.row}; grid-column: {loc.pos.col};"
            >
              <span class="location-id">{loc.id}</span>
            </div>
          {/if}
        {/each}
      </div>
    {/if}
  {/if}
</div>

<!-- Стили для адаптивной сетки по центру -->
<style>
  .map-grid {
    display: grid;
    /* Центрирование сетки по горизонтали */
    justify-content: center; 
    
    /* Сжатие и расширение колонок строго от 100px до 240px */
    grid-template-columns: repeat(var(--cols, 1), minmax(100px, 240px));
    grid-template-rows: repeat(var(--rows, 1), auto);
    
    gap: 10px;
    margin: 20px auto 0 auto;
    
    /* Умещается в ширину экрана */
    max-width: 100%;
    box-sizing: border-box;
  }

  .location-cell {
    background: #ffffff;
    border: 1px solid #ccc;
    border-radius: 4px;
    padding: 8px;
    display: flex;
    align-items: center;
    justify-content: center;
    
    /* Сохраняет пропорции ячейки при изменении ширины */
    aspect-ratio: 1;
    box-sizing: border-box;
  }

  .location-id {
    font-weight: bold;
  }
</style>
