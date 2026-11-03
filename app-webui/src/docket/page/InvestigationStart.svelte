<script>
  import { push, replace } from 'svelte-spa-router';

  import UiErrorMessage from "@core/widget/UiErrorMessage.svelte";
  import UiLoadingIndicator from "@core/widget/UiLoadingIndicator.svelte";
  import UiLabel from "@core/widget/UiLabel.svelte";
  import UiButton from "@core/widget/UiButton";

  import * as scriptApi from "../api/ScriptApi.js";

  let { params = {} } = $props();
  const id = $derived(params.id);

  let error = $state(null);
  let loading = $state(true);

  let overview = $state(null);

  // Состояние полей формы
  let detectivesNum = $state(1);
  let randFactor = $state(1.0);
  let budgetScale = $state(1.0);

  $effect(() => {
    async function loadData() {
      try {
        overview = await scriptApi.scriptOverview(id);
        detectivesNum = overview?.metainfo?.playersNum?.min ?? 1;
      } catch (ex) {
        error = ex.data;
      } finally {
        loading = false;
      }
    }

    loadData();
  });

  async function onStart(e) {
    e?.preventDefault();

    const formData = {
      detectivesNum: Number(detectivesNum),
      randFactor: Number(randFactor),
      budgetScale: Number(budgetScale),
    };

    console.log("Параметры расследования:", formData);

    // TODO: передать formData в API запуска расследования
    replace('/investigation/'+0);
  }

  async function onCancel() {
    replace('/scripts/' + id);
  }
</script>

<div class="container">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />

  {#if overview}
    <h1>Начать расследование дела #{id}: {overview.metainfo.title}</h1>

    <form onsubmit={onStart}>
      <!-- Кол-во детективов: варианты 1..N, по умолчанию playersNum.min -->
      <div class="row mt-3">
        <div class="col-xl-3 col-lg-4 col-md-5">
          <UiLabel
            caption={`Кол-во детективов (${overview.metainfo.playersNum.min}-${overview.metainfo.playersNum.max}):`}
            required={true}
            forId="detectivesNum"
          />
        </div>
        <div class="col-xl-9 col-lg-8 col-md-7">
          <select class="form-control" id="detectivesNum" bind:value={detectivesNum}>
            {#each Array(overview.detectives.length) as _, i}
              <option value={i + 1}>
                {i + 1}
              </option>
            {/each}
          </select>
        </div>
      </div>

      <div class="row mt-3">
        <div class="col-xl-3 col-lg-4 col-md-5">
          <UiLabel caption="Влияние случайности:" required={false} forId="randFactor" />
        </div>
        <div class="col-xl-9 col-lg-8 col-md-7">
          <select class="form-control mr-sm-2" id="randFactor" bind:value={randFactor}>
            <option value={1.0}>Обычное</option>
            <option value={0.5}>Пониженное</option>
            <option value={0.0}>Нет влияния</option>
          </select>
        </div>
      </div>

      <div class="row mt-3">
        <div class="col-xl-3 col-lg-4 col-md-5">
          <UiLabel caption="Бюджет:" required={false} forId="budgetScale" />
        </div>
        <div class="col-xl-9 col-lg-8 col-md-7">
          <select class="form-control mr-sm-2" id="budgetScale" bind:value={budgetScale}>
            <option value={0.7}>70%</option>
            <option value={1.0}>100%</option>
            <option value={1.3}>130%</option>
            <option value={1.6}>160%</option>
          </select>
        </div>
      </div>

      <div class="d-flex justify-content-center mt-4">
        <div>
          <UiButton caption="Начать" type="submit" />
        </div>
        <div class="ms-3">
          <UiButton caption="Отмена" onclick={onCancel} class="ml-2" />
        </div>
      </div>
    </form>
  {/if}
</div>