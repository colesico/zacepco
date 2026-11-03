<script>
  import { createEventDispatcher } from "svelte";

  export let caption = "item";
  export let title = "";
  export let icon = null;
  export let iconPosition = "left";
  export let href = "#";
  export let disabled = false;

  const dispatch = createEventDispatcher();

  function onClick() {
    dispatch("click");
  }
</script>

<a class="dropdown-item" class:disabled {title} {href} on:click={onClick}>
  {#if icon && iconPosition == "left"}
    <i class={icon} aria-hidden="true" />
  {/if}
  {caption}
  {#if icon && iconPosition == "right"}
    <i class={icon} aria-hidden="true" />
  {/if}
</a>
