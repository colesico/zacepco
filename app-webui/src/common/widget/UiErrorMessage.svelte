<script>
  let { error = null } = $props();
  let message = $derived(error.message);
</script>

{#if message != null}
  <div class="alert alert-danger">{message}</div>
{/if}