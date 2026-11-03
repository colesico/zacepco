<script>
  let {
	type="button",
    caption = "",
    title = null,
    icon = null,
    iconPosition = "left",
    cssClass = null,
    onclick = () => {}
  } = $props();
</script>

<button
  {type}
  class="btn btn-primary {cssClass}"
  {title}
  {onclick}
>
  {#if icon != null && iconPosition === "left"}
    <i class={icon} aria-hidden="true"></i>
  {/if}
  {caption}
  {#if icon != null && iconPosition === "right"}
    <i class={icon} aria-hidden="true"></i>
  {/if}
</button>