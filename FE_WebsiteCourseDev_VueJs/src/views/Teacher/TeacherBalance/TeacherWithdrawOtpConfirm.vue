<template>
  <div class="container py-5">
    <div class="col-md-6 mx-auto">
      <div class="card p-4 shadow rounded-4">
        <h4 class="mb-3 text-center text-uppercase fw-bold">Xác nhận OTP</h4>

        <p class="text-muted text-center">Vui lòng nhập mã OTP đã được gửi tới email/SĐT để xác nhận giao dịch.</p>

        <input
          v-model="otp"
          type="text"
          class="form-control mb-3 text-center"
          placeholder="Nhập mã OTP"
        />

        <button
          class="btn btn-success w-100 mb-2"
          :disabled="otp.length !== 6"
          @click="confirmOTP"
        >
          Xác nhận
        </button>

        <button class="btn btn-outline-secondary w-100" @click="resendOTP">
          Gửi lại mã OTP
        </button>

        <div v-if="message" class="alert alert-info mt-3">{{ message }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()
const otp = ref('')
const message = ref('')
const requestId = ref('')

onMounted(() => {
  requestId.value = route.query.id
})

function confirmOTP() {
  // Giả lập xác nhận thành công
  message.value = 'Xác nhận thành công! Yêu cầu đang được xử lý...'
  setTimeout(() => router.push('/teacher/balance'), 2000)
}

function resendOTP() {
  message.value = 'Mã OTP mới đã được gửi.'
}
</script>
