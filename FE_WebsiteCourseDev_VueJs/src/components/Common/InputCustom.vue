<template>
  <div class="mb-3 custom-input">
    <input
      :type="type"
      :id="id"
      :placeholder="placeholder || ' '"
      :value="modelValue"
      @input="$emit('update:modelValue', $event.target.value)"
      required
    />
    <label :for="id">{{ label }}</label>
  </div>
</template>

<script setup>
defineProps({
  modelValue: String,
  label: String,
  id: String,
  type: {
    type: String,
    default: 'text'
  },
  placeholder: String
})
defineEmits(['update:modelValue'])
</script>

<style scoped>
.custom-input {
  position: relative;
}

.custom-input input {
  padding: 10px;
  width: 100%;
  border: 1px solid black;
  border-radius: 5px;
}

.custom-input input:focus {
  outline: none;
}

.custom-input label {
  position: absolute;
  left: 15px;
  top: 10px;
  background-color: white;
  transition: 0.2s ease-in-out;
  pointer-events: none;
}

.custom-input input:focus + label,
.custom-input input:valid + label,
.custom-input input:not(:placeholder-shown) + label {
  top: -20%;
  font-size: 0.9rem;
}
</style>
