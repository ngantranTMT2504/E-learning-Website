<template>
  <label class="custom-toggle">
    <input
      type="checkbox"
      :checked="modelValue"
      @change="$emit('update:modelValue', $event.target.checked)">
    <span class="custom-toggle-slider"></span>
  </label>
</template>

<script setup>
defineProps({
  modelValue: {
    type: Boolean,
    required: true
  }
})
defineEmits(['update:modelValue'])
</script>

<style scoped>
.custom-toggle {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 24px;
}

.custom-toggle input {
  opacity: 0;
  width: 0;
  height: 0;
}

.custom-toggle-slider {
  position: absolute;
  cursor: pointer;
  width: 100%;
  height: 100%;
  border-radius: 20px;
  background-color: #ccc;
  transition: 0.3s;
  box-sizing: border-box;
}

/* Nút tròn */
.custom-toggle-slider::before {
  content: "";
  position: absolute;
  height: 18px;
  width: 18px;
  left: 3px;
  top: 50%;
  transform: translateY(-50%);
  background-color: white;
  border-radius: 50%;
  transition: 0.3s;
}

/* Chữ NO */
.custom-toggle-slider::after {
  content: "NO";
  position: absolute;
  right: 6px;
  top: 50%;
  transform: translateY(-50%);
  font-size: 12px;
  color: white;
  transition: 0.3s;
}

/* Khi bật */
.custom-toggle input:checked + .custom-toggle-slider {
  background-color: white;
  border: 1px solid var(--green-primary);
}

.custom-toggle input:checked + .custom-toggle-slider::before {
  transform: translate(26px, -50%);
  background-color: var(--green-primary);
}

.custom-toggle input:checked + .custom-toggle-slider::after {
  content: "YES";
  left: 6px;
  right: auto;
  color: var(--green-primary);
}

</style>
