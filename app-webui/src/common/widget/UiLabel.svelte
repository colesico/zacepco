<script>
  let {
    caption = "caption",
    required = false,
    forId = ""
  } = $props();
</script>

<label for={forId} class:label-mandatory={required}>{caption}</label>

<style>
  .label-mandatory {
  }

  .label-mandatory:after {
    content: "*";
    color: red;
  }
</style>