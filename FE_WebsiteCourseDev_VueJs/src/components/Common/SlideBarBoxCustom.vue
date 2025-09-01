<template>
  <div class="accordion" :id="'accordion-' + uniqueId">
    <div class="accordion-item border-0">
      <h2 class="accordion-header">
        <button
          class="accordion-button custom-accordion-header"
          type="button"
          data-bs-toggle="collapse"
          :data-bs-target="'#' + collapseId"
          aria-expanded="true"
          :aria-controls="collapseId">
          {{ title }}
        </button>
      </h2>
      <div
        :id="collapseId"
        class="accordion-collapse collapse show"
        :data-bs-parent="'#accordion-' + uniqueId">
        <div class="accordion-body pt-2 pb-2">
          <div
            class="form-check mb-1"
            v-for="(option, index) in showAll ? options : options.slice(0, 5)"
            :key="`option-${uniqueId}-${option.id ?? option}`">
            <input
              class="form-check-input"
              type="checkbox"
              :id="`checkbox-${uniqueId}-${index}`"
              :value="option.id ?? option"
              :name="title"
              v-model="model"/>
            <label
              class="form-check-label ms-2"
              :for="`checkbox-${uniqueId}-${index}`">
              {{ option.name ?? option }}
            </label>
          </div>
          <button
            v-if="options.length > 5"
            class="displayedOptions fs-8 btn p-0 mt-1"
            @click="showAll = !showAll">
            {{ showAll ? 'Ẩn bớt' : 'Hiện thêm' }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

const props = defineProps({
  title: String,
  options: Array,
  modelValue: Array
})

const emit = defineEmits(['update:modelValue'])

const model = computed({
  get: () => props.modelValue,
  set: (val) => emit('update:modelValue', val)
})

const showAll = ref(false)
const uniqueId = `id-${Math.random().toString(36).slice(2, 9)}`
const collapseId = `collapse-${uniqueId}`
</script>

<style scoped>
.form-check-input:checked {
  background-color: var(--green-primary);
  border-color: var(--green-primary);
  box-shadow: none;
}

.form-check-input:focus {
  box-shadow: none;
  border-color: var(--green-primary);
  outline: 0;
}

.accordion-button {
  padding: 10px 16px;
  font-weight: 500;
  font-size: 16px;
  color: var(--bs-dark);
}

.accordion-button:not(.collapsed) {
  background-color: #ffffff;
  box-shadow: none;
  color: var(--green-primary);
}

.accordion-button:focus {
  box-shadow: none;
}

.accordion-item {
  border-radius: 10px;
  overflow: hidden;
  border: 1px solid #e0e0e0;
}

.accordion-body {
  padding: 12px 16px;
}

.form-check-label,
.form-check-input {
  cursor: pointer;
}

.displayedOptions {
  color: var(--green-primary);
}
</style>
