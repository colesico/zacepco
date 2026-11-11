<script>
  import * as scriptApi from "../api/ScriptApi.js";

  import UiErrorMessage from "@core/widget/UiErrorMessage.svelte";
  import UiLoadingIndicator from "@core/widget/UiLoadingIndicator.svelte";
  import UiLocalMenu from "@core/widget/UiLocalMenu.svelte";
  import UiLocalMenuItem from "@core/widget/UiLocalMenuItem.svelte";

  let { params = {} } = $props();
  const id = $derived(params.id);

  let error = $state(null);
  let loading = $state(true);
  let script = $state(null);
  
  
  let entityDescription = $state(null);

  $inspect(error);
  $inspect(script);  

  $effect(() => {
    async function loadData() {
      try {
        let o = await scriptApi.scriptDocument(id);
		script = o;
      } catch (ex) {
        error = ex.data;
      } finally {
        loading = false;
      }
    }

    loadData();
  });

  function getSceneLocation(row, col) {
    return Object.values(script.crime.scene.locations).find(
      (loc) => loc.pos.row === row && loc.pos.col === col
    );
  }

  function startInvestigation() {
    // TODO: реализовать переход к расследованию
    console.log("Начать расследование: дело #" + id);
    alert(`Расследование дела #${id} скоро начнётся!`);
  }
</script>

<div class="container">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />
  <UiLocalMenu>
    <UiLocalMenuItem
      icon="fas fa-circle-play"
      title="Начать новое расследование"
      caption="Начать расследование"
      href="/investigation/start/{id}"
    />
  </UiLocalMenu>

  {#if script}
  

	<h1>Дело "{script.metainfo.title}" #{script.metainfo.id}</h1>
	
    <!-- Контейнер для картинки и текста аннотации -->
    <div class="d-flex align-items-start">
      <!-- Изображение (размер регулируется через width) -->
      <img
        src="/docket/script-assets/images/thumb/{id}/S"
        alt="{script.metainfo.title}"
        class="img-fluid rounded border text-muted"
        style="width: 120px; min-width: 120px; object-fit: cover;"
      />

      <!-- Текст аннотации с серой чертой слева -->
      <p class="ps-3 fs-5 mb-0">
        {script.metainfo.annotation}
      </p>
    </div>
	
	<br/>

    <!-- Внешняя строка-контейнер для двух колонок -->
    <div class="row">
      <!-- Левая колонка -->
      <div class="col-md-6 mb-3">
        <dl class="row mb-0">
          <dt class="col-sm-4 text-muted fw-normal"><b>Сложность:</b></dt>
          <dd class="col-sm-8 text-break mb-1">{script.metainfo.difficulty}</dd>
        </dl>

        <dl class="row mb-0">
          <dt class="col-sm-4 text-muted fw-normal"><b>Время игры:</b></dt>
          <dd class="col-sm-8 text-break mb-1">
            {script.metainfo.estimatedTime.min}-{script.metainfo.estimatedTime.max} минут
          </dd>
        </dl>

        <dl class="row mb-0">
          <dt class="col-sm-4 text-muted fw-normal"><b>Кол-во игроков:</b></dt>
          <dd class="col-sm-8 text-break mb-1">
            {script.metainfo.playersNum.min}-{script.metainfo.playersNum.max} чел.
          </dd>
        </dl>

        <dl class="row mb-0">
          <dt class="col-sm-4 text-muted fw-normal"><b>Возраст:</b></dt>
          <dd class="col-sm-8 text-break mb-1">{script.metainfo.age}</dd>
        </dl>
      </div>

      <!-- Правая колонка -->
      <div class="col-md-6 mb-3">
        <dl class="row mb-0">
          <dt class="col-sm-4 text-muted fw-normal"><b>Авторы:</b></dt>
          <dd class="col-sm-8 text-break mb-1">{script.metainfo.authors}</dd>
        </dl>

        <dl class="row mb-0">
          <dt class="col-sm-4 text-muted fw-normal"><b>Лицензия:</b></dt>
          <dd class="col-sm-8 text-break mb-1">{script.metainfo.licence}</dd>
        </dl>

        <dl class="row mb-0">
          <dt class="col-sm-4 text-muted fw-normal"><b>Дата создания:</b></dt>
          <dd class="col-sm-8 text-break mb-1">{script.metainfo.creationDate}</dd>
        </dl>

        <dl class="row mb-0">
          <dt class="col-sm-4 text-muted fw-normal"><b>Версия:</b></dt>
          <dd class="col-sm-8 text-break mb-1">v{script.metainfo.version}</dd>
        </dl>
      </div>
    </div>
	

    <h2 class="mt-5 mb-4">Персонажи</h2>

    <!-- Сетка для карточек: 2 в ряд на мобильных, 3 на планшетах, 4 или 6 на десктопах -->
    <div class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-6 g-3 mb-5">
      {#each Object.values(script.crime.personages) as personage}
        <div class="col card-col">
          <!-- Карточка персонажа -->
          <div
            class="card h-100 border-0 bg-light text-center shadow-sm"
            style="cursor: pointer;"
            onclick={() => entityDescription = personage.name + ": " + personage.description}
          >
            <!-- Контейнер для квадратного или пропорционального фото -->
            <div class="ratio ratio-1x1 card-img-top overflow-hidden rounded-top">
              <img
                src="/docket/script-assets/images/thumb/{id}/{personage.id}"
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

    <div
      class="grid-container"
      style="--cols: {script.crime.scene.size.cols}; --rows: {script.crime.scene.size.rows};"
    >
      {#each Array(script.crime.scene.size.rows) as _, currentRow}
        {#each Array(script.crime.scene.size.cols) as _, currentCol}
          {@const location = getSceneLocation(currentRow + 1, currentCol + 1)}

          <div class="grid-item">
            {#if location}
              <img
                src="/docket/script-assets/images/thumb/{id}/{location.id}"
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
      {#each Object.values(script.investigation.detectives) as detective}
        <div class="col card-col">
          <div
            class="card h-100 border-0 bg-light text-center shadow-sm"
            style="cursor: pointer;"
            onclick={() => entityDescription = detective.name + ": " + detective.description}
          >
            <!-- Контейнер для квадратного или пропорционального фото -->
            <div class="ratio ratio-1x1 card-img-top overflow-hidden rounded-top">
              <img
                src="/docket/script-assets/images/thumb/{id}/{detective.id}"
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
        <div class="modal-content shadow border-0 p-3 position-relative">
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
  /* Ограничение карточек: не шире 240px, по центру своей колонки */
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