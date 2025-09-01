<template>
  <div class="">
    <h2 class="mb-4 text-uppercase fw-bold">Quản lý số dư</h2>

    <div class="row g-4">
      <!-- Số dư -->
      <div class="col-lg-4">
        <div class="card shadow rounded-4">
          <div class="card-body">
            <h5 class="card-title">Số dư hiện tại</h5>
            <p class="display-6 fw-bold text-success">{{ balance | currency }}</p>
            <ButtonCustom class="w-100" @click="goToWithdraw">Rút tiền</ButtonCustom>
          </div>
        </div>
      </div>

      <!-- Lịch sử giao dịch chi tiết -->
      <div class="col-lg-8">
        <TableListCustom
          :data="transactions"
          :columns="columns"
          :totalRows="totalCount"
          :pageSize="5"
          :rowsPerPageOptions="[5, 10, 20]"
          @onRequest="fetchTransactions"
        >
          <template #cell-createdAt="{ row }">
            <span class="text-muted">{{ formatDate(row.createdAt) }}</span>
          </template>

          <template #cell-status="{ row }">
            <span :class="getStatusClass(row.status)">
              {{ row.status }}
            </span>
          </template>

          <template #cell-amount="{ row }">
            <span :class="row.amount > 0 ? 'text-success fw-bold' : 'text-danger fw-bold'">
              {{ row.amount | currency }}
            </span>
          </template>

          <template #cell-action="{ row }">
            <button class="btn btn-sm btn-outline-secondary" @click="viewTransaction(row)">Xem</button>
          </template>
        </TableListCustom>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import TableListCustom from '@/components/Common/TableListCustom.vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'

const router = useRouter()
const balance = ref(1245000)
const currency = ref(0)
const columns = [
  { label: 'Mã GD', field: 'code', sortable: true },
  { label: 'Hình thức', field: 'method', sortable: true },
  { label: 'Số tiền', field: 'amount', sortable: true },
  { label: 'Ngày giao dịch', field: 'createdAt', sortable: true },
  { label: 'Trạng thái', field: 'status', sortable: true },
  { label: 'Thao tác', field: 'action', width: '100px', align: 'center' }
]

const transactions = ref([])
const totalCount = ref(0)

function fetchTransactions({ page, pageSize }) {
  const all = [
    {
      id: 1,
      code: 'GD20250710001',
      method: 'Momo',
      amount: -500000,
      status: 'Thành công',
      createdAt: '2025-07-10'
    },
    {
      id: 2,
      code: 'GD20250705002',
      method: 'Ví học viên',
      amount: 800000,
      status: 'Thành công',
      createdAt: '2025-07-05'
    },
    {
      id: 3,
      code: 'GD20250701003',
      method: 'Ngân hàng',
      amount: -300000,
      status: 'Đang xử lý',
      createdAt: '2025-07-01'
    }
  ]

  totalCount.value = all.length
  const start = (page - 1) * pageSize
  const end = start + pageSize
  transactions.value = all.slice(start, end)
}

function viewTransaction(row) {
  alert(`Chi tiết giao dịch:\nMã: ${row.code}\nPhương thức: ${row.method}\nSố tiền: ${row.amount.toLocaleString()} VNĐ`)
}

function goToWithdraw() {
  router.push('/teacher/withdraw')
}

function formatDate(date) {
  return new Date(date).toLocaleDateString('vi-VN')
}

function getStatusClass(status) {
  if (status === 'Thành công') return 'badge bg-success'
  if (status === 'Đang xử lý') return 'badge bg-warning text-dark'
  return 'badge bg-secondary'
}
</script>

<style scoped>
.card-title {
  font-weight: 600;
}

.badge {
  padding: 0.4em 0.7em;
  font-size: 0.85rem;
  border-radius: 0.6rem;
}
</style>
