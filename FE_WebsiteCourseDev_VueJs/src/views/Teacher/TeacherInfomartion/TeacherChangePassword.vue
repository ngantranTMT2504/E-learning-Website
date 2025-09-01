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
import { updateItem } from '@/services/apiMiddlewareV2.js'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'

const apiChangePassword = '/api/accounts/change-password/'
// Form dữ liệu
const form = ref({
  currentPassword: '',
  newPassword: '',
  confirmPassword: ''
})

// Thông báo
const errorMessage = ref('')
const successMessage = ref('')

// Lấy accountId từ token hoặc gán tạm
const accountId = 13 // Thay bằng ID thực tế nếu có

const handleChangePassword = async () => {
  errorMessage.value = ''
  successMessage.value = ''

  // Kiểm tra dữ liệu đầu vào
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

  try {
    await updateItem(apiChangePassword + accountId, form.value)
    successMessage.value = 'Đổi mật khẩu thành công!'
    form.value = {
      currentPassword: '',
      newPassword: '',
      confirmPassword: ''
    }
  } catch (error) {
    const res = error.response
    if (res?.data?.message) {
      errorMessage.value = res.data.message
    } else {
      errorMessage.value = 'Đổi mật khẩu thất bại. Vui lòng thử lại.'
    }
  }
}
</script>

<style scoped>
</style>