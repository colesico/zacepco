<script>
  import * as casebookApi from "../api/CasebookApi.js";
  
  import UiErrorMessage from "@common/widget/UiErrorMessage.svelte"
  import UiLoadingIndicator from "@common/widget/UiLoadingIndicator.svelte"
  import UiLocalMenu from "@common/widget/UiLocalMenu.svelte";
  import UiLocalMenuItem from "@common/widget/UiLocalMenuItem.svelte";
  
  
  let { params = {} } = $props();
  const id = $derived(params.id);
  
  let error = $state(null);
  let loading = $state(true);
  
  let overview = $state(null);
  
  let entityDescription = $state(null);
  
  $inspect(error); 
  $inspect(overview); 


  $effect(() => {
    async function loadData() {
      try {
        overview = await casebookApi.casebookOverview(id);
      } catch (ex) {
        error = ex.data;
      } finally {
        loading = false;
      }
    }

    loadData();
  });
  
</script>


<div class="container">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />
   <UiLocalMenu>
    <UiLocalMenuItem
      icon="fas fa-clipboard-check"
      title="Регистрация нового дела на базе сценария преступления"
      caption="Добавить дело"
      href="/cases/create"
    />
  </UiLocalMenu>
  
 {#if overview}
	<h1>Дело #{id} {overview.meta.title}</h1>
	<!-- Контейнер для картинки и текста аннотации -->
	<div class="d-flex align-items-start gap-3 mb-5">
	  
	  <!-- Изображение (размер регулируется через width) -->
	  <img 
		src="/cases/assets/{id}/S" 
		alt="Обложка дела" 
		class="img-fluid rounded border text-muted" 
		style="width: 120px; min-width: 120px; object-fit: cover;" 
	  />

	  <!-- Текст аннотации с серой чертой слева -->
	  <p class="ps-3 fs-5 mb-0">
		{overview.meta.annotation}
	  </p>
	  
	</div>
	
	<!-- Внешняя строка-контейнер для двух колонок -->
	<div class="row">
	  
	  <!-- Левая колонка -->
	  <div class="col-md-6 mb-3">
		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Кол-во игроков:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">{overview.meta.playersNum.min}-{overview.meta.playersNum.max} чел.</dd>
		</dl>
		
		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Сложность:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">{overview.meta.difficulty}</dd>
		</dl>
		
		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Время игры:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">{overview.meta.estimatedTime.min}-{overview.meta.estimatedTime.max} минут</dd>
		</dl>
	  </div>
	  
	  <!-- Правая колонка -->
	  <div class="col-md-6 mb-3">
		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Авторы:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">{overview.meta.authors}</dd>
		</dl>

		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Лицензия:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">{overview.meta.licence}</dd>
		</dl>

		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Дата создания:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">{overview.meta.creationDate}</dd>
		</dl>

		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Версия:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">v{overview.meta.version}</dd>
		</dl>
	  </div>
      
	</div>
	

  
  <h2 class="mt-5 mb-4">Персонажи</h2>
  
  <!-- Сетка для карточек: 2 в ряд на мобильных, 3 на планшетах, 4 или 6 на десктопах -->
  <div class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-6 g-3 mb-5">
    
    {#each overview.personages as personage}
      <div class="col">
        <!-- Карточка персонажа -->
        <div class="card h-100 border-0 bg-light text-center shadow-sm"
		 style="cursor: pointer;"
		 onclick={() => entityDescription = personage.name+": "+personage.description}
		>
          
          <!-- Контейнер для квадратного или пропорционального фото -->
          <div class="ratio ratio-1x1 card-img-top overflow-hidden rounded-top">
            <img 
              src="/cases/assets/{id}/{personage.id}" 
              alt={personage.name} 
              class="object-fit-cover"
            />
          </div>
          
        
          <div class="card-body p-2 d-flex align-items-center justify-content-center">
            <h6 class="card-title mb-0 fw-semibold text-break">
              {personage.name}
            </h6>
          </div>
          
        </div>
      </div>
    {/each}
    
  </div>
  {/if}	


{#if entityDescription}
  <div class="modal-backdrop fade show"></div>

  <div class="modal fade show d-block" tabindex="-1">
    <div class="modal-dialog modal-lg modal-dialog-centered">
      <div class="modal-content shadow border-0 p-3 position-relative" >
        
        <button 
		  type="button" 
		  class="btn-close position-absolute top-0 end-0 m-1 p-1 shadow-none" 
		  style="cursor: pointer; z-index: 1060; min-width: auto;"
		  aria-label="Закрыть" 
		  onclick={() => entityDescription = null}
		></button>
        
        <div class="modal-body p-2 pt-3">
          <p class="text-muted mb-0 text-center" style="white-space: pre-line;">
            {entityDescription || "Описание отсутствует."}
          </p>
        </div>

      </div>
    </div>
  </div>
{/if}

</div>

<style>
</style>