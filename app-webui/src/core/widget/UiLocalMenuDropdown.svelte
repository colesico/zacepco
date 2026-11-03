<script>
  import { createEventDispatcher } from "svelte";

  export let caption = "item";
  export let title = "";
  export let icon = "";
  export let iconPosition = "left";

  export let isActive = false;
</script>

<li class="nav-item dropdown">
  <a
    class="nav-link dropdown-toggle"
    data-bs-toggle="dropdown"
    href="#"
    role="button"
    aria-haspopup="true"
    aria-expanded="false"
    {title}
  >
    {#if icon != null && iconPosition == "left"}
      <i class={icon} aria-hidden="true" />
    {/if}
    {caption}
    {#if icon != null && iconPosition == "right"}
      <i class={icon} aria-hidden="true" />
    {/if}
  </a>
  <div class="dropdown-menu">
    <slot />
  </div>
</li>
