<script>
  import * as scriptApi from "../api/ScriptApi.js";

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
        overview = await scriptApi.scriptOverview(id);
      } catch (ex) {
        error = ex.data;
      } finally {
        loading = false;
      }
    }

    loadData();
  });


   function getSceneLocation(row, col) {
    if (!overview || !overview.scene || !overview.scene.locations) return null;

     return overview.scene.locations.find(
      (loc) => loc.row === row && loc.col === col
    );
  }

</script>


<div class="container">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />
   <UiLocalMenu>
    <UiLocalMenuItem
      icon="fas fa-clipboard-check"
      title="Начать расследование"
      caption="Начать расследование"
      href="/"
    />
  </UiLocalMenu>

 {#if overview}
	<h1>Дело #{id} {overview.metainfo.title}</h1>
	<!-- Контейнер для картинки и текста аннотации -->
	<div class="d-flex align-items-start gap-3 mb-5">

	  <!-- Изображение (размер регулируется через width) -->
	  <img
		src="/casereg/assets/image/thumb/{id}/S"
		alt="Обложка дела"
		class="img-fluid rounded border text-muted"
		style="width: 120px; min-width: 120px; object-fit: cover;"
	  />

	  <!-- Текст аннотации с серой чертой слева -->
	  <p class="ps-3 fs-5 mb-0">
		{overview.metainfo.annotation}
	  </p>

	</div>

	<!-- Внешняя строка-контейнер для двух колонок -->
	<div class="row">

	  <!-- Левая колонка -->
	  <div class="col-md-6 mb-3">
		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Кол-во игроков:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">{overview.metainfo.playersNum.min}-{overview.metainfo.playersNum.max} чел.</dd>
		</dl>

		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Сложность:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">{overview.metainfo.difficulty}</dd>
		</dl>

		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Время игры:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">{overview.metainfo.estimatedTime.min}-{overview.metainfo.estimatedTime.max} минут</dd>
		</dl>
	  </div>

	  <!-- Правая колонка -->
	  <div class="col-md-6 mb-3">
		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Авторы:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">{overview.metainfo.authors}</dd>
		</dl>

		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Лицензия:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">{overview.metainfo.licence}</dd>
		</dl>

		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Дата создания:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">{overview.metainfo.creationDate}</dd>
		</dl>

		<dl class="row mb-0">
		  <dt class="col-sm-4 text-muted fw-normal"><b>Версия:</b></dt>
		  <dd class="col-sm-8 text-break mb-1">v{overview.metainfo.version}</dd>
		</dl>
	  </div>

	</div>



  <h2 class="mt-5 mb-4">Персонажи</h2>

  <!-- Сетка для карточек: 2 в ряд на мобильных, 3 на планшетах, 4 или 6 на десктопах -->
  <div class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-6 g-3 mb-5">

    {#each overview.personages as personage}
      <div class="col card-col">
        <!-- Карточка персонажа -->
        <div class="card h-100 border-0 bg-light text-center shadow-sm"
		 style="cursor: pointer;"
		 onclick={() => entityDescription = personage.name+": "+personage.description}
		>

          <!-- Контейнер для квадратного или пропорционального фото -->
          <div class="ratio ratio-1x1 card-img-top overflow-hidden rounded-top">
            <img
              src="/casereg/assets/image/thumb/{id}/{personage.asset}"
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


	<h2 class="mt-5 mb-4">Сцена преступления</h2>

	<div class="grid-container"
		 style="--cols: {overview.scene.cols}; --rows: {overview.scene.rows};">

		 {#each Array(overview.scene.rows) as _, currentRow}

		   {#each Array(overview.scene.cols) as _, currentCol}

			 {@const location = getSceneLocation(currentRow+1, currentCol+1)}

			 <div class="grid-item">
			   {#if location}
				 <img
				   src="/casereg/assets/image/thumb/{id}/{location.asset}"
				   alt={location.name || "Локация"}
				   title={location.name || "Локация"}
				   loading="lazy"
				 />
			   {:else}
				 <div class="empty-cell"></div>
			   {/if}
			 </div>

		   {/each}
		 {/each}
	</div>
	

	
	 <h2 class="mt-5 mb-4">Детективы</h2>

  <!-- Сетка для карточек: 2 в ряд на мобильных, 3 на планшетах, 4 или 6 на десктопах -->
  <div class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-6 g-3 mb-5">

    {#each overview.detectives as detective}
      <div class="col card-col">

        <div class="card h-100 border-0 bg-light text-center shadow-sm"
		 style="cursor: pointer;"
		 onclick={() => entityDescription = detective.name+": "+detective.description}
		>

          <!-- Контейнер для квадратного или пропорционального фото -->
          <div class="ratio ratio-1x1 card-img-top overflow-hidden rounded-top">
            <img
              src="/casereg/assets/{id}/{detective.asset}"
              alt={detective.name}
              class="object-fit-cover"
            />
          </div>


          <div class="card-body p-2 d-flex align-items-center justify-content-center">
            <h6 class="card-title mb-0 fw-semibold text-break">
              {detective.name}
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

/* Ограничение карточек : не шире 240px, по центру своей колонки */
.card-col {
    max-width: 240px;
}

/* Сетка сцены — адаптивная, ячейки квадратные и ≤ 240×240 */

.grid-container {
    display: grid;

    /* Колонки: каждая ≤ 240px, при нехватке места сжимаются */
    grid-template-columns: repeat(var(--cols), minmax(0, 240px));
    justify-content: start;
    gap: 0;

    /* Ключевое: контейнер по размеру клеток, но не шире родителя */
    width: fit-content;
    max-width: 100%;

    margin-left: 0;
    margin-right: auto;
    margin-bottom: 3rem;

    background-color: #f8f9fa;
    border: 1px solid #ddd;
}	

.grid-item {
    /* Ячейка резиновая: ширина = колонка (≤ 240px), высота — по aspect-ratio */
    width: 100%;
    aspect-ratio: 1 / 1;
    position: relative;
    overflow: hidden;
    box-sizing: border-box;
}

.grid-item img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    display: block;
}

.empty-cell {
    width: 100%;
    height: 100%;
    background-color: #eaeaea;
    box-sizing: border-box;
    border: 1px dashed #ccc;
}
</style>