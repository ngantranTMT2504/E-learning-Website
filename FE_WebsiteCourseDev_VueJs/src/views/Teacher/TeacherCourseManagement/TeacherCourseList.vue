<template>
  <BreadCrumbCustom :items="[
    { label: 'Khoá học', to: '/teacher/course/list' },
    { label: 'Danh sách khoá học' }
  ]" class="mb-3" />
  <div class="d-flex justify-content-between mb-3">
    <h2 class="fw-bold text-uppercase">quản lý khoá học</h2>
    <RouterLink to="/teacher/course/create">
      <ButtonCustom>Thêm khoá học</ButtonCustom>
    </RouterLink>
  </div>
  <TableListCustom :data="courseList" :columns="columns" :totalRows="totalCount" :pageSize="pageSize"
    :rowsPerPageOptions="[5, 10, 15]" @onRequest="handleRequest">
    <template #cell-createdDate="{ row }">
      <span class="text-muted">{{ formatDate(row.createdDate) }}</span>
    </template>
    <template #cell-averageRating="{ row }">
      <RatingCustom :rating="row.averageRating ?? 0" />
    </template>
    <template #cell-image="{ row }">
      <img :src="row.image" alt="" width="80px">
    </template>
    <template #cell-action="{ row }">
      <ActionMenuCustom :uid="row.id" :menuItems="[
        {
          label: 'Thêm bài học', icon: 'bi bi-plus-square', class: 'text-success', to: {
            path: '/teacher/lesson/create',
            query: { courseId: row.id }
          }
        },
        // { label: 'Xem', icon: 'bi bi-eye', class: 'text-primary', to: `/teacher/course/view/${row.id}` },
        { label: 'Sửa', icon: 'bi bi-pencil-square', class: 'text-warning', to: `/teacher/course/update/${row.id}` },
        { label: 'Xoá', icon: 'bi bi-trash', class: 'text-danger', onClick: () => confirmDelete(row.id) }
      ]" />
    </template>
  </TableListCustom>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { deleteItem, getList } from '@/services/apiMiddlewareV2.js'
import TableListCustom from '@/components/Common/TableListCustom.vue'
import ActionMenuCustom from '@/components/Common/ActionMenuCustom.vue'
import BreadCrumbCustom from '@/components/Common/BreadCrumbCustom.vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'
import RatingCustom from '@/components/Common/RatingCustom.vue'

//api
const API = "/api/teacher/course"
// Dữ liệu bảng
const courseList = ref([])
const totalCount = ref(0)
const currentPage = ref(1)
const pageSize = ref(5)
const accountId = 4
// Bộ lọc cơ bản
const filters = {
  keyword: ref(''),
  title: ref(''),
  slug: ref(''),
  minPrice: ref(null),
  maxPrice: ref(null),
  courseTypeIds: ref([]),
  teacherIds: ref([]),
  accountId: ref(null),
  discount: ref(null),
  createdDate: ref(''),
  updatedDate: ref(''),
  averageRating: ref(null),
  status: ref(null)
}

// Cột hiển thị
const columns = [
  { label: 'Tên khóa học', field: 'title', sortable: true },
  { label: 'Chuyên đề', field: 'courseTypeName', sortable: true },
  { label: 'Hình', field: 'image' },
  { label: 'Ngày tạo', field: 'createdDate', sortable: true },
  { label: 'Chiết khấu', field: 'commissionPercentage', sortable: true, width: '130px' },
  { label: 'Đánh giá', field: 'averageRating', sortable: true, width: '120px' },
  { label: 'Thao tác', field: 'action', width: '100px', align: 'center' }
]

// Format ngày
function formatDate(dateStr) {
  return dateStr ? new Date(dateStr).toLocaleDateString('vi-VN') : ''
}

// Gộp filter từ `filters` object
function getFilterParams() {
  return Object.fromEntries(
    Object.entries(filters).map(([key, refVal]) => [key, refVal.value])
  )
}

// Gọi API
const fetchCourses = async (params = {}) => {
  try {
    currentPage.value = params.page ?? currentPage.value
    pageSize.value = params.pageSize ?? pageSize.value
    filters.title.value = params.search ?? filters.title.value

    const sortList = params.sort ?? []

    const queryParams = {
      ...getFilterParams(),
      accountId: accountId,
      page: currentPage.value - 1,
      size: pageSize.value,
      search: params.search || '',
      sort: sortList.map(s => `${s.field},${s.order}`)
    }

    const result = await getList(API, queryParams)

    // const body = result?.body || {}
    courseList.value = result.data || []
    totalCount.value = result.totalItems || 0
  } catch (error) {
    console.error('Lỗi khi tải danh sách khoá học:', error)
  }
}

const confirmDelete = async (courseId) => {
  const selectedCourse = courseList.value.find(course => course.id === courseId)

  if (!selectedCourse) {
    alert('Không tìm thấy khoá học!')
    return
  }

  if (selectedCourse.lessonTotal > 0) {
    alert('Khoá học đã có bài học, không thể xoá!')
    return
  }

  const confirmed = window.confirm('Bạn có chắc muốn xoá khoá học này không?')
  if (!confirmed) return

  try {
    await deleteItem(`${API}/${courseId}`)
    alert('Xoá thành công!')
    fetchCourses() // reload lại danh sách
  } catch (error) {
    console.error('Lỗi khi xoá:', error)
    alert('Đã xảy ra lỗi khi xoá khoá học.')
  }
}


// Nhận emit từ bảng con
const handleRequest = (params) => {
  fetchCourses(params)
}

// Tải dữ liệu lần đầu
onMounted(() => fetchCourses())
</script>


<style scoped>
.d {
  font-size: 1rem;
}
</style>