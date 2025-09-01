<template>
  <div class="">
    <h2 class="mb-4 text-uppercase fw-bold">Quản lý thẻ ngân hàng</h2>

    <!-- Danh sách thẻ -->
    <div class="d-flex flex-wrap gap-4 justify-content-center">
      <div
        v-for="(card, index) in bankCards"
        :key="index"
        class="bank-card text-white position-relative"
        :class="getBankClass(card.bank)"
      >
        <div class="fs-5 fw-bold">{{ card.bank }}</div>
        <div class="fs-6 mt-2">{{ formatCardNumber(card.accountNumber) }}</div>
        <div class="fs-6 mt-1 text-uppercase">{{ card.owner }}</div>

        <!-- Nút sửa/xoá -->
        <div class="position-absolute top-0 end-0 mt-2 me-2 d-flex gap-2">
          <!-- <button class="btn btn-sm btn-light text-dark" @click="editCard(index)">Sửa</button> -->
          <button class="btn btn-sm btn-close" @click="deleteCard(index)"></button>
        </div>
      </div>
    </div>

    <!-- Form thêm/sửa -->
    <div class="card mt-5 p-4 shadow-sm">
      <h5 class="mb-3">{{ editingIndex === null ? 'Thêm thẻ mới' : 'Chỉnh sửa thẻ' }}</h5>

      <form @submit.prevent="saveCard">
        <div class="row g-3">
          <div class="col-md-4">
            <label class="form-label">Ngân hàng</label>
            <select class="form-select" v-model="form.bank" required>
              <option value="" selected>-- Vui lòng chọn ngân hàng --</option>
              <option v-for="b in bankList" :key="b" :value="b">{{ b }}</option>
            </select>
          </div>
          <div class="col-md-4">
            <label class="form-label">Số tài khoản</label>
            <input
              type="text"
              class="form-control"
              v-model="form.accountNumber"
              required
              placeholder="VD: 012345678901"
            />
          </div>
          <div class="col-md-4">
            <label class="form-label">Chủ tài khoản</label>
            <input
              type="text"
              class="form-control"
              v-model="form.owner"
              required
              placeholder="VD: Nguyễn Văn A"
            />
          </div>
        </div>
        <div class="d-flex justify-content-end mt-4">
          <ButtonCustom>
            {{ editingIndex === null ? 'Thêm thẻ' : 'Lưu thay đổi' }}
          </ButtonCustom>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'

const bankList = [
  'Vietcombank', 'TPBank', 'BIDV', 'MBBank', 'Techcombank', 'ACB',
  'Sacombank', 'VPBank', 'Agribank', 'HDBank', 'VIB', 'SHB', 'SeABank', 'OCB',
]

const bankCards = ref([
  {
    bank: 'Vietcombank',
    accountNumber: '012345678901',
    owner: 'Nguyễn Mộc Toàn',
  },
  {
    bank: 'BIDV',
    accountNumber: '123456789012',
    owner: 'Nguyễn Quang Minh',
  },
  {
    bank: 'TPBank',
    accountNumber: '123456789012',
    owner: 'Nguyễn Trí Tài',
  },
])

const form = ref({
  bank: '',
  accountNumber: '',
  owner: '',
  issueDate: '',
  expiryDate: '',
})

const editingIndex = ref(null)

function saveCard() {
  if (editingIndex.value === null) {
    bankCards.value.push({ ...form.value })
  } else {
    bankCards.value[editingIndex.value] = { ...form.value }
    editingIndex.value = null
  }
  resetForm()
}

function editCard(index) {
  form.value = { ...bankCards.value[index] }
  editingIndex.value = index
}

function deleteCard(index) {
  if (confirm('Bạn có chắc chắn muốn xoá thẻ này?')) {
    bankCards.value.splice(index, 1)
    resetForm()
  }
}

function resetForm() {
  form.value = {
    bank: '',
    accountNumber: '',
    owner: '',
    issueDate: '',
    expiryDate: '',
  }
}

function getBankClass(bank) {
  const key = bank?.toLowerCase().replace(/\s+/g, '') || 'default'
  return `bg-${key}`
}

function formatCardNumber(number) {
  return number.replace(/(.{4})/g, '$1 ').trim()
}
</script>

<style scoped>
.bank-card {
  width: 350px; 
  height: 200px; 
  border-radius: 12px;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  position: relative;
  box-shadow: 0 0 12px rgba(0, 0, 0, 0.2);
}

/* Gradient theo ngân hàng */
.bg-vietcombank {
  background: linear-gradient(135deg, #007d3e, #4caf50);
}
.bg-tpbank {
  background: linear-gradient(135deg, #7f3f98, #ff9600);
}
.bg-bidv {
  background: linear-gradient(135deg, #002c6d, #0055a4);
}
.bg-mbbank {
  background: linear-gradient(135deg, #004d99, #1976d2);
}
.bg-techcombank {
  background: linear-gradient(135deg, #e60000, #ff4d4d);
}
.bg-acb {
  background: linear-gradient(135deg, #0066cc, #3399ff);
}
.bg-sacombank {
  background: linear-gradient(135deg, #0073cf, #66ccff);
}
.bg-vpbank {
  background: linear-gradient(135deg, #009640, #79d70f);
}
.bg-agribank {
  background: linear-gradient(135deg, #7d1c1c, #b22222);
}
.bg-hdbank {
  background: linear-gradient(135deg, #ed1b24, #ffcc00);
}
.bg-vib {
  background: linear-gradient(135deg, #0033a0, #ffa500);
}
.bg-shb {
  background: linear-gradient(135deg, #ff8c00, #ffb347);
}
.bg-seabank {
  background: linear-gradient(135deg, #c8102e, #f04e98);
}
.bg-ocb {
  background: linear-gradient(135deg, #007a33, #b9e937);
}
.bg-default {
  background: linear-gradient(135deg, #6c757d, #adb5bd);
}
</style>
