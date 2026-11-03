<script>

    import UiErrorMessage from "@core/widget/UiErrorMessage.svelte"
	import UiLoadingIndicator from "@core/widget/UiLoadingIndicator.svelte"
	import UiLabel from "@core/widget/UiLabel.svelte";
	import UiButton from "@core/widget/UiButton";
	
	import { push, replace } from 'svelte-spa-router';
  
	import * as scriptApi from "../api/ScriptApi.js";
	
	let { params = {} } = $props();
    const id = $derived(params.id);

    let error = $state(null);
    let loading = $state(false);

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
  
  <h1>Начать расследование</h1>
  
  <form onsubmit={onStart}>
	
	<div class="row mt-3">
	  <div class="col-xl-3 col-lg-4 col-md-5">
		<UiLabel caption="Кол-во детективов:" required={true} forId="scriptFile" />
	  </div>
	  <div class="col-xl-9 col-lg-8 col-md-7">
		ююю
	  </div>
    </div>
	<div class="row mt-3">
	  <div class="col-xl-3 col-lg-4 col-md-5">
		<UiLabel caption="Степень случайности:" required={false} forId="scriptFile" />
	  </div>
	  <div class="col-xl-9 col-lg-8 col-md-7">
		ююю
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

</div>

