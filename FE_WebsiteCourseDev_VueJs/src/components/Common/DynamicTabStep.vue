<template>
  <ul class="nav nav-tabs mb-4">
    <li class="nav-item" v-for="(stepItem, index) in steps" :key="index">
      <button
        class="nav-link"
        :class="{ active: currentStep === index + 1, disabled: stepItem.disabled }"
        :disabled="stepItem.disabled"
        @click="$emit('change', index + 1)"
      >
        {{ index + 1 }}. {{ stepItem.label }}
      </button>
    </li>
  </ul>
</template>

<script setup>
defineProps({
  steps: Array,
  currentStep: Number
})

defineEmits(['change'])
</script>
