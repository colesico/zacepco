<script>
	// Используем руну $state из Svelte 5 для отслеживания состояния
	let fileInput = $state(null);
	let status = $state('');

	async function uploadFile(event) {
		event.preventDefault(); // Отменяем перезагрузку страницы

		const file = fileInput?.files?.[0];
		if (!file) {
			status = 'Выберите файл!';
			return;
		}

		// Создаем стандартный multipart/form-data объект
		const formData = new FormData();
		formData.append('file', file);

		status = 'Загрузка...';

		try {
			const response = await fetch('https://httpbin.org', { // Тестовый API, возвращает то, что загрузили
				method: 'POST',
				body: formData // Браузер сам выставит нужные заголовки boundary
			});

			if (response.ok) {
				status = 'Успешно загружено!';
			} else {
				status = 'Ошибка сервера';
			}
		} catch (err) {
			status = 'Ошибка сети';
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