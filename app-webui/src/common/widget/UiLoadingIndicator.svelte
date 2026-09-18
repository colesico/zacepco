<script>
  export let loading = false;
  export let imgUrl = "/resources/generic/app/img/icon/cat.gif";
</script>

{#if loading}
  <div class="loading-ind-popup">
    <div class="loading-ind-wrapper">
      <div class="loading-ind-content"><img src={imgUrl} alt="Загрузка" /></div>
    </div>
  </div>
{/if}

<style>
  .loading-ind-popup {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    text-align: center;
    z-index: 100000;
  }
  .loading-ind-popup > div.loading-ind-wrapper {
    position: absolute;
    top: 50%;
    left: 0;
    right: 0;
    transform: translateY(-50%);
    display: block;
    text-align: center;
  }
  .loading-ind-popup .loading-ind-content {
    display: inline-block;
    border: 1px solid #346b8a;
    background-color: white;
    padding: 20px;
  }
</style>
