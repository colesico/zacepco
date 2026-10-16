<script>

    import UiErrorMessage from "@common/widget/UiErrorMessage.svelte"
	import UiLoadingIndicator from "@common/widget/UiLoadingIndicator.svelte"
	import UiLabel from "@common/widget/UiLabel.svelte";
	import UiButton from "@common/widget/UiButton";
	
	import { push, replace } from 'svelte-spa-router';
  
	import * as casebookApi from "../api/CasebookApi.js";

    let error = $state(null);
    let loading = $state(false);

	let fileInput = $state(null);

	async function uploadFile(event) {

		error = null;
		
	    console.log('Submit form...');
		event.preventDefault(); 

		const file = fileInput?.files?.[0];
		if (!file) {
			console.log('File not cpecified');
			return;
		}

		loading = true;

		try {
		    console.log('Call api');
			const response = await casebookApi.createCasebook(file);
		} catch (ex) {
			error = ex.data;
		} finally {
			loading = false;
		}
	}
	
	async function onCancel(){
		replace('/');
	}
	
</script>

<div class="container">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />
  
  <h1>Добавить дело в реестр</h1>
  
  <form onsubmit={uploadFile}>
	
	<div class="row mt-3">
	  <div class="col-xl-3 col-lg-4 col-md-5">
		<UiLabel caption="Файл сценария:" required={false} forId="scriptFile" />
	  </div>
	  <div class="col-xl-9 col-lg-8 col-md-7">
		<input id="scriptFile" type="file" class="form-control" bind:this={fileInput} required />
	  </div>
    </div>
	
	 <div class="d-flex justify-content-center mt-4">
        <div>
          <UiButton caption="Добавить" type="submit" />
        </div>
      <div class="ms-3">
        <UiButton caption="Отмена" onclick={onCancel} class="ml-2" />
      </div>
    </div>
	
  </form>

</div>

