<script>

	import { push, replace } from 'svelte-spa-router';
	
    import UiErrorMessage from "@core/widget/UiErrorMessage.svelte"
	import UiLoadingIndicator from "@core/widget/UiLoadingIndicator.svelte"
	import UiLabel from "@core/widget/UiLabel.svelte";
	import UiButton from "@core/widget/UiButton";
	
	import * as scriptApi from "../api/ScriptApi.js";
	
 	
	let { params = {} } = $props();
    const id = $derived(params.id);

    let error = $state(null);
    let loading = $state(false);
	
	let overview = $state(null);

    $effect(() => {
    async function loadData() {
      try {
        overview = await scriptApi.scriptOverview(id);
      } catch (ex) {
        error = ex.data;
      } finally {
        loading = false;
      }
    }

    loadData();
  });
  
  
	async function onStart(){
		replace('/');
	}
	
	async function onCancel(){
		replace('/scripts/'+id);
	}
	
</script>

<div class="container">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />
  
  
  {#if overview}
  
    <h1>Начать расследование дела  #{id}: {overview.metainfo.title}</h1>
	
	  <form onsubmit={onStart}>
		
		<div class="row mt-3">
		  <div class="col-xl-3 col-lg-4 col-md-5">
			<UiLabel caption="Кол-во детективов:" required={true} forId="detectivesNum" />
		  </div>
		  <div class="col-xl-9 col-lg-8 col-md-7">
			 <select class="form-control" id="detectivesNum">
			  <option>1</option>
			  <option>2</option>
			  <option>3</option>
			  <option>4</option>
			  <option>5</option>
			</select>
		  </div>
		</div>
		<div class="row mt-3">
		  <div class="col-xl-3 col-lg-4 col-md-5">
			<UiLabel caption="Влияние случайности:" required={false} forId="scriptFile" />
		  </div>
		  <div class="col-xl-9 col-lg-8 col-md-7">
			<select class="form-control mr-sm-2" id="inlineFormCustomSelect">
	    		<option value="1.0" selected>Обычное</option>
				<option value="0.5">Пониженное</option>
				<option value="0.0">Без случайности</option>
			 </select>
		  </div>
		</div>
		
		 <div class="d-flex justify-content-center mt-4">
			<div>
			  <UiButton caption="Начать" type="submit" />
			</div>
		  <div class="ms-3">
			<UiButton caption="Отмена" onclick={onCancel} class="ml-2" />
		  </div>
		</div>
		
	  </form>
  
   {/if}

</div>

