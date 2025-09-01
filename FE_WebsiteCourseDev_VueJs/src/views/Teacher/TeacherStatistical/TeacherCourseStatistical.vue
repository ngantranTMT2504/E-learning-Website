<template>
  <BreadCrumbCustom :items="[
    { label: 'Thống kê', to: '/teacher/statistical/course' },
    { label: 'Thống kê khoá học' }
  ]" class="mb-3" />

  <div class="d-flex justify-content-between mb-3">
    <h2 class="fw-bold text-uppercase">thống kê khoá học</h2>
    <ButtonCustom @click="">Xuất Excel</ButtonCustom>
  </div>

  <TableListCustom
    :data="courseList"
    :columns="columns"
    :totalRows="totalCount"
    :pageSize="pageSize"
    :rowsPerPageOptions="[3, 5, 10]"
    @onRequest=""
  >
    <template #cell-createdDate="{ row }">
      <span class="text-muted">{{ formatDate(row.createdDate) }}</span>
    </template>

    <template #cell-status="{ row }">
      <span :class="row.status === 'true' ? 'text-success' : 'text-danger'">
        {{ row.status === 'true' ? 'Đang mở' : row.status === 'false' ? 'Đã kết thúc' : row.status }}
      </span>
    </template>
  </TableListCustom>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getList, postWithBlob, setAuthorization } from '@/services/apiMiddlewareV2.js'
import TableListCustom from '@/components/Common/TableListCustom.vue'
import BreadCrumbCustom from '@/components/Common/BreadCrumbCustom.vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'

const courseList = ref([])
const totalCount = ref(0)
const pageSize = 3
const teacherId = 5

const columns = [
  { label: 'Tên khóa học', field: 'title', sortable: true },
  { label: 'Số học viên', field: 'studentCount', sortable: true },
  { label: 'Doanh thu (VNĐ)', field: 'revenue', sortable: true },
  { label: 'Trạng thái', field: 'status', sortable: true },
  { label: 'Ngày tạo', field: 'createdDate', sortable: true },
]

function formatDate(dateStr) {
  const date = new Date(dateStr)
  return isNaN(date) ? '-' : date.toLocaleDateString('vi-VN')
}

// function handleRequest({ page, size, sort, asc }) {
//   const params = {
//     page,
//     size,
//     sort: sort ?? 'createdDate',
//     asc: asc ?? false,
//     teacherId
//   }

//   getList('', params)
//     .then(res => {
//       courseList.value = res.content
//       totalCount.value = res.totalElements
//     })
//     .catch(err => {
//       console.error('Lỗi khi tải dữ liệu:', err)
//     })
// }

// function exportExcel() {
//   postWithBlob('', { teacherId })
//     .then((data) => {
//       const url = window.URL.createObjectURL(new Blob([data]))
//       const link = document.createElement('a')
//       link.href = url
//       link.setAttribute('download', 'course_statistics.xlsx')
//       document.body.appendChild(link)
//       link.click()
//       link.remove()
//     })
//     .catch((error) => {
//       console.error('Export Excel thất bại:', error)
//     })
// }

// Tắt token để test không cần JWT
// setAuthorization(false)

// onMounted(() => {
//   handleRequest({ page: 0, size: pageSize })
// })
</script>
