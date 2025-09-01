<template>
  <button
    :class="['btn fw-semibold', variantClass]"
    @click="$emit('click')"
  >
    <span class="fw-bold"><slot></slot></span>
  </button>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  variant: {
    type: String,
    default: 'green',
  }
})

const variantClass = computed(() => {
  return {
    green: 'btn-green',
    white: 'btn-white',
    bare: 'btn-bare',
  }[props.variant] || ''
});
</script>

<style scoped>

.btn {
  padding: 0.5rem 1.2rem;
  font-size: 1rem;
  border-radius: 8px;
  cursor: pointer;
}

.btn-green {
  background-color: var(--green-primary);
  color: var(--white-text);
  border: 2px solid var(--green-primary);
  transition: 0.3s ease-out;
}
.btn-green:hover {
  color: var(--white-text);
  background-color: var(--green-dark);
}

.btn-white {
  background-color: var(--white-text);
  color: var(--green-primary);
  border: 2px solid var(--green-primary);
  transition: 0.3s ease-out;
}
.btn-white:hover {
  background-color: var(--light-gray);
}

.btn-bare {
  background-color: var(--bare-orange);
  color: var(--green-text);
  border: 2px solid var(--bare-orange);
}

.btn-white {
  position: relative;
  background-color: var(--white-text);
  color: var(--green-primary);
  border: 2px solid var(--green-primary); 
  overflow: hidden;
  transition: color 0.3s ease-out, box-shadow 0.3s ease-out;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
  z-index: 1;
}

.btn-white::before {
  content: '';
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background-color: var(--green-primary);
  transition: left 0.3s ease-out;
  z-index: 0; 
}

.btn-white:hover::before {
  left: 0;
}

.btn-white:hover {
  color: var(--white-text);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  border: 2px solid var(--green-primary);
}

.btn-white span {
  position: relative;
  z-index: 1;
}

</style>
