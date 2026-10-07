<script>
  import { isEmpty } from '@common/assist/CommonUtils';

  let { entry = {}, 
        target = '_self' 
	  } = $props();
	  
</script>

<div class="dir-card">
  <a href="/cases/{entry.id}" target={target}>
    <div class="dir-card-img">
      <img src="/cases/assets/{entry.id}/S" alt={entry.script.title || ''} />
    </div>
    <div class="dir-card-title">
      {entry.script.title} 
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