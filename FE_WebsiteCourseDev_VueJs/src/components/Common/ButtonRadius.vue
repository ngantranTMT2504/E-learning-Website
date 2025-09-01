<template>
  <button
    :class="['btn fw-semibold rounded-pill', typeClass]" 
    @click="$emit('click')"
  >
    <slot />
  </button>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  type: {
    type: String,
    default: 'green'
  }
});

const typeClass = computed(() => {
  return {
    green: 'btn-green',
    white: 'btn-white',
    bare: 'btn-bare'
  }[props.type] || ''
});
</script>

<style scoped>


/* Các class màu */
.btn-green {
  background-color: var(--green-primary);
  color: var(--white-text);
  border: 2px solid var(--green-primary);
  transition: 0.3s ease-out;
}

.btn-green:hover{
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
  border: 2px solid var(--green-primary);
  background-color:  var(--green-primary);
  color: white;
}

.btn-bare {
  background-color: var(--bare-orange);
  color: var(--green-text);
  border: 2px solid var(--bare-orange);
}

</style>
