<template>
  <div class="container">
    <BreadCrumbCustom :items="[
        { label: 'Quản lý số dư', to: '/teacher/balance' },
        { label: 'Yêu cầu rút tiền'}
    ]" class="mb-3"/>
    <h2 class="text-uppercase fw-bold text-center mb-5">Yêu cầu rút tiền</h2>

    <div class="row justify-content-center">
      <div class="col-lg-6 col-md-8">
        <div class="card shadow-lg rounded-4 border-0">
          <div class="card-body p-4">

            <div class="mb-4">
              <label for="amount" class="form-label fw-semibold">Số tiền muốn rút</label>
              <div class="input-group">
                <input
                  v-model.number="amount"
                  type="number"
                  min="10000"
                  class="form-control"
                  id="amount"
                  placeholder="Nhập số tiền (VND)"
                />
                <span class="input-group-text">VND</span>
              </div>
            </div>

            <div class="mb-4">
              <label for="bank" class="form-label fw-semibold">Tài khoản ngân hàng</label>
              <select v-model="selectedBank" class="form-select" id="bank">
                <option value="">-- Chọn tài khoản ngân hàng --</option>
                <option
                  v-for="(bank, index) in bankAccounts"
                  :key="index"
                  :value="index"
                >
                  {{ bank.bankName }} - {{ bank.accountNumber }}
                </option>
              </select>

              <div class="mt-3">
                <RouterLink to="/teacher/bank" class="text-decoration-none small">
                  <i class="bi bi-plus-circle me-1"></i>Thêm tài khoản ngân hàng mới
                </RouterLink>
              </div>
            </div>
            <div class="d-flex justify-content-end">
              <ButtonCustom
                :disabled="!canSubmit"
                @click="submitWithdraw">
                Xác nhận
              </ButtonCustom>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter,RouterLink } from 'vue-router'
import BreadCrumbCustom from '@/components/Common/BreadCrumbCustom.vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'

const amount = ref(0)
const selectedBank = ref('')
const router = useRouter()

// Danh sách tài khoản ngân hàng mẫu
const bankAccounts = ref([
  { bankName: 'Vietcombank', accountNumber: '0123456789' },
  { bankName: 'Techcombank', accountNumber: '9876543210' }
])

const canSubmit = computed(() =>
  amount.value >= 10000 && selectedBank.value !== ''
)

function submitWithdraw() {
  const chosenBank = bankAccounts.value[selectedBank.value]

  // Bạn có thể gửi dữ liệu thật tại đây bằng API
  // Sau đó chuyển hướng sang trang OTP và truyền dữ liệu cần thiết
  router.push({
    path: '/teacher/withdraw-otp',
    query: {
      amount: amount.value,
      bankName: chosenBank.bankName,
      accountNumber: chosenBank.accountNumber
    }
  })

  // Nếu có lưu dữ liệu backend thì làm ở đây
}
</script>

<style scoped>
button:disabled{
  background-color: silver;
}
</style>
