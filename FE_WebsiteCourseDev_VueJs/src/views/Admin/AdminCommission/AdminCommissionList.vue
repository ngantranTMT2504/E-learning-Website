<template>
  <div>
    <h2 class="fw-bold text-uppercase mb-3">Quản lý Chiết Khấu</h2>
    <div class="row">
      <!-- Form bên phải -->
      <div class="col-xl-6 order-xl-2 mb-5 mb-xl-0">
        <div class="card shadow position-sticky" style="top: 80px;">
          <div class="card-body">
            <h3 class="fw-bold mb-3 text-uppercase text-center">
              {{ isEdit ? 'Cập nhật Chiết Khấu' : 'Thêm Chiết Khấu' }}
            </h3>

            <div class="mb-3">
              <label class="form-label">Ngày áp dụng</label>
              <input
                type="date"
                class="form-control"
                v-model="formData.applicableDate"
                :disabled="isSubmitting"
              />
            </div>

            <div class="mb-3">
              <label class="form-label">Phần trăm chiết khấu</label>
              <input
                ref="percentageInput"
                type="number"
                min="0"
                max="100"
                class="form-control"
                v-model="formData.percentage"
                :disabled="isSubmitting"
              />
            </div>

            <CheckboxCustom v-model="formData.status" :disabled="isSubmitting">
              Hoạt động
            </CheckboxCustom>

            <div class="d-flex justify-content-end gap-2 mt-3">
              <ButtonCustom :disabled="isSubmitting" @click="handleSubmit">
                {{ isEdit ? 'Cập nhật' : 'Lưu' }}
              </ButtonCustom>
              <ButtonCustom variant="secondary" :disabled="isSubmitting" @click="resetForm">
                Huỷ
              </ButtonCustom>
            </div>
          </div>
        </div>
      </div>

      <!-- Bảng bên trái -->
      <div class="col-xl-6 order-xl-1">
        <TableListCustom
          :data="commissions"
          :columns="columns"
          :totalRows="totalItems"
          :pageSize="pageSize"
          :rowsPerPageOptions="[5, 10, 15]"
          :currentPage="currentPage + 1"
          @onRequest="handlePageChange"
        >
          <template #cell-applicableDate="{ row }">
            <span class="text-muted">{{ formatDate(row.applicableDate) }}</span>
          </template>

          <template #cell-percentage="{ row }">
            <span class="text-muted">{{ row.percentage }}%</span>
          </template>

         
        </TableListCustom>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue'

// Component custom
import TableListCustom from '@/components/Common/TableListCustom.vue'
import ActionMenuCustom from '@/components/Common/ActionMenuCustom.vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'
import SwitchCheckbox from '@/components/Common/SwitchCheckbox.vue'
import CheckboxCustom from '@/components/Common/CheckboxCustom.vue'

// API
import {
  getList,
  createItem,
  updateItem,
  deleteItem
} from '@/services/apiMiddlewareV2.js'

// Toast
function showError(msg) {
  alert(msg) // hoặc dùng Toast
}

function showSuccess(msg) {
  alert(msg) // hoặc dùng Toast
}
const filters = {
  percentage: ref(''),
  applicableDate: ref('')
}
function getFilterParams() {
  return Object.fromEntries(
    Object.entries(filters).map(([key, refVal]) => [key, refVal.value])
  )
}
// State
const endpoint = '/api/commission'
const isEdit = ref(false)
const isSubmitting = ref(false)
const formData = ref({ id: null, percentage: '', applicableDate: '', status: true })

const commissions = ref([])
const totalItems = ref(0)
const currentPage = ref(1)
const pageSize = ref(5)

const columns = [
  { label: 'Phần trăm chiết khấu', field: 'percentage', sortable: true },
  { label: 'Ngày áp dụng', field: 'applicableDate', sortable: true },
  
]

const percentageInput = ref(null)

// Lấy danh sách
const fetchCommissions= async (params = {}) => {
  try {
  currentPage.value = params.page ?? currentPage.value
    pageSize.value = params.pageSize ?? pageSize.value
    filters.percentage.value = params.search ?? filters.percentage.value


    const sortList = params.sort ?? []

    const queryParams = {
      ...getFilterParams(),
      page: currentPage.value - 1,
      size: pageSize.value,
      search: params.search || '',
      sort: sortList.map(s => `${s.field},${s.order}`)
    }

    const res = await getList(endpoint, queryParams)
    commissions.value = res.content ?? res.data ?? []
    totalItems.value = res.totalElements ?? res.totalItems ?? 0
  } catch (err) {
    console.error('Lỗi lấy danh sách:', err)
    showError('Lỗi khi lấy danh sách chiết khấu!')
  }
}

// Phân trang
function handlePageChange(params) {
  fetchCommissions(params)
}

onMounted(() => {
  fetchCommissions()
})

// Reset form
function resetForm() {
  formData.value = { id: null, percentage: '', applicableDate: '', status: true }
  isEdit.value = false
  isSubmitting.value = false
}

// Sửa
function startEdit(row) {
  formData.value = { ...row }
  isEdit.value = true
  isSubmitting.value = false
  window.scrollTo({ top: 0, behavior: 'smooth' })
  nextTick(() => {
    percentageInput.value?.focus()
  })
}

// Submit
async function handleSubmit() {
  if (isSubmitting.value) return
  if (!formData.value.percentage || isNaN(formData.value.percentage)) {
    showError('Vui lòng nhập phần trăm hợp lệ!')
    return
  }
  if (!formData.value.applicableDate) {
    showError('Vui lòng nhập ngày áp dụng!')
    return
  }

  isSubmitting.value = true

  try {
    if (isEdit.value) {
      await updateItem(`${endpoint}/${formData.value.id}`, {
        percentage: formData.value.percentage,
        applicableDate: formData.value.applicableDate,
        status: formData.value.status
      })
      showSuccess('Cập nhật thành công!')
    } else {
      await createItem(endpoint, {
        percentage: formData.value.percentage,
        applicableDate: formData.value.applicableDate,
        status: formData.value.status
      })
      showSuccess('Thêm mới thành công!')
      currentPage.value = 0
    }
    await fetchCommissions(currentPage.value, pageSize.value)
    resetForm()
  } catch (err) {
    console.error('Lỗi xử lý dữ liệu:', err)
    showError('Đã có lỗi xảy ra khi lưu dữ liệu!')
  } finally {
    isSubmitting.value = false
  }
}

// Toggle trạng thái
async function handleToggleStatus(row, newStatus) {
  try {
    await updateItem(`${endpoint}/${row.id}`, {
      percentage: row.percentage,
      applicableDate: row.applicableDate,
      status: newStatus
    })
    const index = commissions.value.findIndex(item => item.id === row.id)
    if (index !== -1) commissions.value[index].status = newStatus
    showSuccess('Cập nhật trạng thái thành công!')
  } catch (error) {
    console.error('Lỗi toggle trạng thái:', error)
    showError('Không thể cập nhật trạng thái!')
    row.status = !newStatus
  }
}

// Xoá
async function confirmDelete(id) {
  if (!confirm('Bạn có chắc chắn muốn xoá chiết khấu này?')) return

  try {
    await deleteItem(`${endpoint}/${id}`)
    showSuccess('Xoá thành công!')
    if (commissions.value.length === 1 && currentPage.value > 0) {
      currentPage.value -= 1
    }
    await fetchCommissions(currentPage.value, pageSize.value)
  } catch (err) {
    console.error('Lỗi xoá:', err)
    showError('Không thể xoá chiết khấu!')
  }
}

// Format ngày

  // Format ngày
function formatDate(dateStr) {
  const d = new Date(dateStr)
  return d.toLocaleDateString('vi-VN', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric'
  })
}
</script>
