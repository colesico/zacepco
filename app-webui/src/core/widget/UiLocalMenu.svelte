<script>
  let { alignItems = "right", children } = $props();
</script>

<ul class="nav w-100 justify-content-end">
  {@render children()}
</ul>
