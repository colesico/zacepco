<script>

    import * as caseFileApi from "../api/CaseFileApi.js";
  
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

<form onsubmit={uploadFile}>
	<input type="file" bind:this={fileInput} required />
	<button type="submit">Отправить</button>
</form>

{#if status}
	<p>{status}</p>
{/if}