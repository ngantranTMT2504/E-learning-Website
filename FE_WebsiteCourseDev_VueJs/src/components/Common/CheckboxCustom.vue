<template>
  <label class="check-custom">
    <input type="checkbox" :checked="modelValue" @change="$emit('update:modelValue', $event.target.checked)" />
    <span class="checkmark me-2"></span>
    <slot />
  </label>
</template>

<script setup>
defineProps({
  modelValue: Boolean
})

defineEmits(['update:modelValue'])
</script>

<style scoped>
.check-custom {
  display: flex;
  align-items: center;
  cursor: pointer;
  user-select: none;
  font-size: 1rem;
}

.check-custom input[type="checkbox"] {
  display: none;
}

.check-custom .checkmark {
  width: 18px;
  height: 18px;
  border: 2px solid #ccc;
  border-radius: 4px;
  background-color: white;
  position: relative;
  transition: all 0.2s ease;
}

.check-custom input[type="checkbox"]:checked + .checkmark {
  background-color: var(--green-primary);
  border-color: var(--green-primary);
}

.check-custom input[type="checkbox"]:checked + .checkmark::after {
  content: "";
  position: absolute;
  left: 5px;
  top: 2px;
  width: 4px;
  height: 8px;
  border: solid white;
  border-width: 0 2px 2px 0;
  transform: rotate(45deg);
}
</style>
