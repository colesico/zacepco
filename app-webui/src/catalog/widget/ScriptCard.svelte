<script>
  import { isEmpty } from 'src/core/assist/CommonUtils';

  let { embroidery = {}, target = '_self' } = $props();
</script>

<div class="dir-card">
  <a href="/catalog/embroidery/details/{embroidery.id}" target={target}>
    <div class="dir-card-img">
      <img src="/file-store{embroidery.document?.poster?.thumbId}" alt={embroidery.document?.name?.text || ''} />
    </div>
    <div class="dir-card-title">
      {embroidery.document?.kind?.text} 
      {embroidery.document?.name?.text} 
      
      {#if !isEmpty(embroidery.document?.vendorCode?.text)}
        {embroidery.document.vendorCode.text}
      {/if}
      
      {embroidery.document?.trademark?.name}
    </div>
  </a>
</div>

<style>

.dir-card {
  margin:5px;
  padding:10px;
  cursor:pointer;
  border:1px solid lightgray;
  border-radius:8px;
  background-color: white;
}

.dir-card-img{
  display:flex;
  margin:4px;
  min-width:255px;
  min-height:255px;
  justify-content:center;
  align-items:center;
  /* background-color:gray; */
}

.dir-card-title{
    display:flex;
    max-width:240px;
    padding: 4px;
    display: flex;
    justify-content:center;
    text-align: center;
    overflow-wrap: anywhere;

}
</style>