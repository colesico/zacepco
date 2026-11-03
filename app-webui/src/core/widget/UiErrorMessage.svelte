<script>
  let { error = null } = $props();
  let message = $derived(
  error != null
    ? (error.message || error.type || 'Неизвестная ошибка')
    : null
);
</script>

{#if message != null}
  <div class="alert alert-danger">{message}</div>
{/if}