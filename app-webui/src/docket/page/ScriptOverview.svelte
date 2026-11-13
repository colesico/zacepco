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


  $inspect(error);
  $inspect(script);

  $effect(() => {
    async function loadData() {
      try {
        script = await scriptApi.scriptDocument(id);
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


	<h1>Дело "{script.metainfo.title}" #{script.metainfo.uid}</h1>

    <!-- Контейнер для картинки и текста аннотации -->
    <div class="d-flex align-items-start">
      <!-- Изображение (размер регулируется через width) -->
      <img
        src="/docket/script/images/thumb/{id}/S"
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
          {#if personage.hidden}
            <!-- Скрытый персонаж: серая клетка с жирным знаком вопроса по центру -->
            <div class="card h-100 border-0 bg-light text-center shadow-sm">
              <div class="ratio ratio-1x1 card-img-top overflow-hidden rounded-top">
                <div class="hidden-cell">?</div>
              </div>
              <!-- Пустой card-body, чтобы высота карточки совпадала с открытыми -->
              <div class="card-body p-2 d-flex align-items-center justify-content-center">
                <h6 class="card-title mb-0 fw-semibold text-break">&nbsp;</h6>
              </div>
            </div>
          {:else}
            <!-- Карточка персонажа -->
            <div
              class="card h-100 border-0 bg-light text-center shadow-sm" >
              <!-- Контейнер для квадратного или пропорционального фото -->
              <div class="ratio ratio-1x1 card-img-top overflow-hidden rounded-top">
                <img
                  src="/docket/script/images/thumb/{id}/{personage.id}"
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
          {/if}
        </div>
      {/each}
    </div>

    <h2 class="mt-5 mb-4">Сцена преступления</h2>

    <div
      class="scene-container"
      style="--cols: {script.crime.scene.size.cols}; --rows: {script.crime.scene.size.rows};"
    >
      {#each Array(script.crime.scene.size.rows) as _, currentRow}
        {#each Array(script.crime.scene.size.cols) as _, currentCol}
          {@const location = getSceneLocation(currentRow + 1, currentCol + 1)}

          <div class="scene-cell">
            {#if location}
              {#if location.hidden}
                <!-- Скрытая локация: серая клетка с жирным знаком вопроса -->
                <div class="hidden-cell">?</div>
              {:else}
                <img
                  src="/docket/script/images/thumb/{id}/{location.id}"
                  alt={location.name || "Локация"}
                  title={location.name || "Локация"}
                  loading="lazy"
                />
              {/if}
            {:else}
              <div class="scene-empty-cell"></div>
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
            class="card h-100 border-0 bg-light text-center shadow-sm" >
            <!-- Контейнер для квадратного или пропорционального фото -->
            <div class="ratio ratio-1x1 card-img-top overflow-hidden rounded-top">
              <img
                src="/docket/script/images/thumb/{id}/{detective.id}"
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
</div>

<style>
  /* Ограничение карточек: не шире 240px, по центру своей колонки */
  .card-col {
    max-width: 240px;
  }

  /* Серая клетка с жирным "?" по центру — используется и для скрытых
     персонажей, и для скрытых локаций */
  .hidden-cell {
    width: 100%;
    height: 100%;
    background-color: #eaeaea;
    border: 1px dashed #ccc;
    box-sizing: border-box;

    display: flex;
    align-items: center;
    justify-content: center;

    font-weight: 700;
    font-size: 4rem;
    line-height: 1;
    color: #6c757d;
    user-select: none;
  }

  /* Сетка сцены — адаптивная, ячейки квадратные,
     не больше 240×240 и не выше 70% высоты экрана */
  .scene-container {
    --max-cell: 240px;
    --max-height: 70svh;

    display: grid;
    grid-template-columns: repeat(
      var(--cols),
      minmax(0, min(var(--max-cell), calc(var(--max-height) / var(--rows))))
    );
    justify-content: start;
    gap: 0;

    /* Контейнер по размеру клеток, но не шире родителя */
    width: fit-content;
    max-width: 100%;

    margin-left: 0;
    margin-right: auto;
    margin-bottom: 3rem;

    background-color: #f8f9fa;
    border: 1px solid #ddd;
  }

  .scene-cell {
    /* Ячейка резиновая: ширина = колонка, высота — по aspect-ratio */
    width: 100%;
    aspect-ratio: 1 / 1;
    position: relative;
    overflow: hidden;
    box-sizing: border-box;
  }

  .scene-cell img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    display: block;
  }

  .scene-empty-cell {
    width: 100%;
    height: 100%;
    background-color: #eaeaea;
    box-sizing: border-box;
    border: 1px dashed #ccc;
  }
</style>