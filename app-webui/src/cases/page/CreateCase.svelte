<script>

    import UiErrorMessage from "@common/widget/UiErrorMessage.svelte"
	import UiLoadingIndicator from "@common/widget/UiLoadingIndicator.svelte"
  
	import * as caseFileApi from "../api/CaseFileApi.js";

    let error = $state(null);
    let loading = $state(false);

	let fileInput = $state(null);
	let status = $state('');

	async function uploadFile(event) {
		event.preventDefault(); 

		const file = fileInput?.files?.[0];
		if (!file) {
			status = 'Выберите файл!';
			return;
		}


		status = 'Загрузка...';

		try {
			const response = await caseFileApi.createCaseFile(file);

			if (response.ok) {
				status = 'Успешно загружено!';
			} else {
				status = 'Ошибка сервера';
			}
		} catch (err) {
			status = 'Ошибка';
		}
	}
</script>

<div class="container">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />
  <h1>Добавить дело в реестр</h1>
  
  <form onsubmit={uploadFile}>
	<input type="file" bind:this={fileInput} required />
	<button type="submit">Отправить</button>
  </form>

{#if status}
	<p>{status}</p>
{/if}

</div>

