<template>
  <BreadCrumbCustom :items="[
    { label: 'Giáo Viên', to: '/admin/teacher/list' },
    { label: 'Chứng Chỉ Giáo Viên' }
  ]" class="mb-3" />
  <div class="d-flex justify-content-between mb-3">
    <h2 class="fw-bold text-uppercase">Quản lý Chứng Chỉ Giáo Viên</h2>
    <div></div>
  </div>

  <TableListCustom :data="certificateList" :columns="columns" :totalRows="totalItems" :pageSize="pageSize"
    :rowsPerPageOptions="[5, 10, 15]" :currentPage="currentPage" @onRequest="handlePageChange">
    <template #cell-certificateImage="{ row }">
      <img :src="row.certificateImage" alt="image" width="100" />
    </template>

    <template #cell-uploadedDate="{ row }">
      <span class="text-muted">{{ formatDate(row.uploadedDate) }}</span>
    </template>
  </TableListCustom>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

// Component
import TableListCustom from '@/components/Common/TableListCustom.vue'
import BreadCrumbCustom from '@/components/Common/BreadCrumbCustom.vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'

// API middleware
import { getList, deleteItem } from '@/services/apiMiddlewareV2.js'

// Toasts (tùy chọn bạn có thể thay bằng thư viện toast thật)
function showSuccess(msg) {
  alert(msg)
}
function showError(msg) {
  alert(msg)
}

// State
const endpoint = '/api/teacher-certificate'
const certificateList = ref([])
const totalItems = ref(0)
const currentPage = ref(1)
const pageSize = ref(5)

// Cột bảng
const columns = [
  { label: 'Tên chứng chỉ', field: 'certificateName', sortable: true },
  { label: 'Hình ảnh', field: 'certificateImage', width: '120px' },
  { label: 'Nơi cấp', field: 'issuingOrganization', sortable: true  },
  { label: 'Năm cấp', field: 'issuedYear', sortable: true  },
  { label: 'Ngày tải lên', field: 'uploadedDate', sortable: true  },
  { label: 'Giáo viên', field: 'teacherName' }
]

const filters = {
  certificateName: ref(''),
  teacherId: ref(''),
  uploadedDate: ref('')
}

function getFilterParams() {
  return Object.fromEntries(
    Object.entries(filters).map(([key, refVal]) => [key, refVal.value])
  )
}

// Load danh sách từ API
const fetchCertificates= async (params = {}) => {
  try {

    currentPage.value = params.page ?? currentPage.value
    pageSize.value = params.pageSize ?? pageSize.value
    filters.certificateName.value = params.search ?? filters.certificateName.value

    const sortList = params.sort ?? []

    const queryParams = {
      ...getFilterParams(),
      page: currentPage.value - 1,
      size: pageSize.value,
      search: params.search || '',
      sort: sortList.map(s => `${s.field},${s.order}`)
    }

    const res = await getList(endpoint, queryParams)
    certificateList.value = res.content ?? res.data ?? []
    totalItems.value = res.totalElements ?? res.totalItems ?? 0
  } catch (err) {
    console.error('Lỗi khi lấy danh sách chứng chỉ:', err)
    // showError('Không thể tải danh sách!')
  }
}

// Xử lý phân trang
function handlePageChange(params) {
  fetchCertificates(params)
}

// Format ngày
function formatDate(dateStr) {
  const d = new Date(dateStr)
  return d.toLocaleDateString('vi-VN', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric'
  })
}

// Gọi API khi trang được mount
onMounted(() => {
  fetchCertificates()
})
</script>
