<script>
  import { link } from 'svelte-spa-router';

  let {
    caption = "item",
    title = "",
    icon = null,
    iconPosition = "left",
    href = "#",
    disabled = false,
    onclick = () => {}
  } = $props();
</script>

<li class="nav-item">
  <a class="nav-link" class:disabled  use:link {title} {href} {onclick}>
    {#if icon && iconPosition === "left"}
      <i class={icon} aria-hidden="true"></i>
    {/if}
    {caption}
    {#if icon && iconPosition === "right"}
      <i class={icon} aria-hidden="true"></i>
    {/if}
  </a>
</li>
