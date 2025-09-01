<template>
  <form @submit.prevent="handleSubmit">
    <textarea
      v-model="value"
      class="form-control"
      rows="3"
      placeholder="Viết bình luận..."
    ></textarea>
    <div class="d-flex justify-content-end gap-2 mt-2">
      <button @click="emit('close')" type="button" class="btn btn-light btn-sm">
        Hủy
      </button>
      <button type="submit" class="btn btn-success btn-sm green-primary text-white">
        Bình luận
      </button>
    </div>
  </form>
</template>

<script setup>
const value = defineModel({ default: '', type: String })
const emit = defineEmits(['close', 'submit-reply']);

const handleSubmit = () => {
  emit('submit-reply', value.value);
  value.value = '';
};

</script>

<style scoped>
.green-primary {
  background: var(--green-primary);
}
</style>
