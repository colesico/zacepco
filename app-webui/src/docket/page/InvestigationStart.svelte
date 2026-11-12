<script>
  import { replace } from 'svelte-spa-router';

  import UiErrorMessage from '@core/widget/UiErrorMessage.svelte';
  import UiLoadingIndicator from '@core/widget/UiLoadingIndicator.svelte';
  import UiLabel from '@core/widget/UiLabel.svelte';
  import UiButton from '@core/widget/UiButton';

  import * as scriptApi from '../api/ScriptApi.js';
  import * as investigationApi from '../api/InvestigationApi.js';

  let { params = {} } = $props();
  const id = $derived(params.id);

  let error = $state(null);
  let loading = $state(true);

  let script = $state(null);

  // Состояние полей формы
  let detectivesNum = $state(1);
  let randFactor = $state(1.0);
  let budgetScale = $state(1.0);

  const detectiveOptions = $derived.by(() => {
    const min = script.metainfo.playersNum.min;
    const max = script.metainfo.playersNum.max;

    return Array.from({ length: Math.max(0, max - min + 1) }, (_, i) => min + i);
  });

  $effect(() => {

    async function loadData() {

      loading = true;
      error = null;

      try {
        script = await scriptApi.scriptDocument(id);
        detectivesNum = script.metainfo.playersNum.min;
      } catch (ex) {
         error = ex.data;
         script = null;
      } finally {
          loading = false;
      }
    }

    loadData();

  });

  async function onStart(e) {
    e?.preventDefault();

    const formData = {
	  scriptId: Number(id),
      detectivesNum: Number(detectivesNum),
      randFactor: Number(randFactor),
      budgetScale: Number(budgetScale),
    };


    loading = true;
    error = null;

    try {
      const invEntry = await investigationApi.createInvestigation(formData);
      replace(`/investigation/${invEntry.id}`);
    } catch (ex) {
      error = ex.data;
    } finally {
      loading = false;
    }
  }

  function onCancel() {
    replace(`/script/${id}`);
  }
  
</script>

<div class="container">
  <UiLoadingIndicator {loading} />
  <UiErrorMessage {error} />

  {#if script}
    <h1>Начать расследование "{script.metainfo.title}" #{script.metainfo.uid}</h1>

    <form onsubmit={onStart}>
      <!-- Кол-во детективов: варианты min..max, по умолчанию playersNum.min -->
      <div class="row mt-3">
        <div class="col-xl-3 col-lg-4 col-md-5">
          <UiLabel
            caption={`Кол-во детективов (${script.metainfo.playersNum.min}-${script.metainfo.playersNum.max}):`}
            required={true}
            forId="detectivesNum"
          />
        </div>
        <div class="col-xl-9 col-lg-8 col-md-7">
          <select class="form-control" id="detectivesNum" bind:value={detectivesNum}>
            {#each detectiveOptions as detectiveNum}
              <option value={detectiveNum}>
                {detectiveNum}
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
          <select class="form-control me-sm-2" id="randFactor" bind:value={randFactor}>
            <option value={1.0}>100%</option>
            <option value={0.5}>50%</option>
            <option value={0.0}>0%</option>
          </select>
        </div>
      </div>

      <div class="row mt-3">
        <div class="col-xl-3 col-lg-4 col-md-5">
          <UiLabel caption="Бюджет:" required={false} forId="budgetScale" />
        </div>
        <div class="col-xl-9 col-lg-8 col-md-7">
          <select class="form-control me-sm-2" id="budgetScale" bind:value={budgetScale}>
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
          <UiButton caption="Отмена" type="button" onclick={onCancel} class="ms-2" />
        </div>
      </div>
    </form>
  {/if}
</div>