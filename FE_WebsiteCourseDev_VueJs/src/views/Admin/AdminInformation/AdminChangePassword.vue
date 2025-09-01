<template>
  <div class="card mx-auto bg-light p-4 shadow-sm">
    <h6 class="text-muted text-uppercase mb-3">đổi mật khẩu</h6>
    
    <div class="mb-3">
      <label class="form-label">Mật khẩu hiện tại</label>
      <input
        type="password"
        class="form-control"
        v-model="form.currentPassword"
        placeholder="Nhập mật khẩu hiện tại"
      />
    </div>

    <div class="mb-3">
      <label class="form-label">Mật khẩu mới</label>
      <input
        type="password"
        class="form-control"
        v-model="form.newPassword"
        placeholder="Nhập mật khẩu mới"
      />
    </div>

    <div class="mb-3">
      <label class="form-label">Xác nhận mật khẩu mới</label>
      <input
        type="password"
        class="form-control"
        v-model="form.confirmPassword"
        placeholder="Nhập lại mật khẩu mới"
      />
    </div>

    <div v-if="errorMessage" class="alert alert-danger">{{ errorMessage }}</div>
    <div v-if="successMessage" class="alert alert-success">{{ successMessage }}</div>

    <div class="d-flex justify-content-end">
        <ButtonCustom @click="handleChangePassword">
        Đổi mật khẩu
        </ButtonCustom>
    </div>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'

const form = ref({
  currentPassword: '',
  newPassword: '',
  confirmPassword: ''
})

const errorMessage = ref('')
const successMessage = ref('')

const handleChangePassword = () => {
  errorMessage.value = ''
  successMessage.value = ''

  if (
    !form.value.currentPassword ||
    !form.value.newPassword ||
    !form.value.confirmPassword
  ) {
    errorMessage.value = 'Vui lòng điền đầy đủ thông tin.'
    return
  }

  if (form.value.newPassword !== form.value.confirmPassword) {
    errorMessage.value = 'Mật khẩu mới và xác nhận không khớp.'
    return
  }

  // TODO: Gọi API đổi mật khẩu ở đây
  console.log('Đổi mật khẩu:', form.value)

  successMessage.value = 'Đổi mật khẩu thành công!'
  // Xóa form
  form.value = {
    currentPassword: '',
    newPassword: '',
    confirmPassword: ''
  }
}
</script>
<style scoped>
</style>