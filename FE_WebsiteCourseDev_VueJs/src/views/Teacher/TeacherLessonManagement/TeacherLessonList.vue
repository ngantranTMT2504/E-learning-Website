<template>
    <BreadCrumbCustom :items="[
        { label: 'Bài học', to: '/teacher/lesson/list' },
        { label: 'Danh sách bài học' }
    ]" class="mb-3" />

    <div class="d-flex justify-content-between mb-3">
        <h2 class="fw-bold text-uppercase">quản lý bài học</h2>
        <RouterLink to="/teacher/lesson/create">
            <ButtonCustom>Thêm bài học</ButtonCustom>
        </RouterLink>
    </div>
    <TableListCustom :data="lessonList" :columns="columns" :totalRows="totalCount" :pageSize="5"
        :rowsPerPageOptions="[5, 10, 15]" @onRequest="handleRequest">
        <template #cell-postedDate="{ row }">
            <span class="text-muted">{{ formatDate(row.postedDate) }}</span>
        </template>
        <!-- <template #cell-videoUrl="{ row }">
            <div v-if="isVideoFile(row.videoUrl)">
                <video :src="row.videoUrl" width="180" controls ></video>
            </div>

            <div v-else-if="isYoutube(row.videoUrl)">
                <iframe width="180" height="100" :src="convertYoutubeEmbed(row.videoUrl)" frameborder="0"
                    allowfullscreen></iframe>
            </div>

            <div v-else-if="isVimeo(row.videoUrl)">
                <iframe width="180" height="100" :src="convertVimeoEmbed(row.videoUrl)" frameborder="0"
                    allowfullscreen></iframe>
            </div>

            <div v-else-if="isCloudinary(row.videoUrl)">
                <video :src="row.videoUrl" width="180" controls ></video>
            </div>

            <div v-else>
                <span class="text-muted">Không xác định</span>
            </div>
        </template> -->
        <template #cell-videoUrl="{ row }">
            <video :src="row.videoUrl" width="360" controls ></video>
        </template>
        <!-- <template #cell-discription="{ row }">
            <div v-html="row.discription"></div>
        </template> -->
        <template #cell-action="{ row }">
            <ActionMenuCustom :uid="row.id" :menuItems="[
                // { label: 'Xem', icon: 'bi bi-eye', class: 'text-primary', to: `/teacher/lesson/view/${row.id}` },
                { label: 'Sửa', icon: 'bi bi-pencil-square', class: 'text-warning', to: `/teacher/lesson/update/${row.id}` },
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
//api
const apiLesson = '/api/lesson'

const lessonList = ref([])
const totalCount = ref(0)
const currentPage = ref(1)
const pageSize = ref(5)
const accountId = 4

// Bộ lọc 
const filters = {
    title: ref(''),
    lessonOrder: ref(null),
    courseName: ref(''),
    teacherIds: ref([]),
    accountId: ref(null),
    comissionPercent: ref(null),
    postedDate: ref(''),
    status: ref(null)
}

// Cột hiển thị
const columns = [
    { label: 'Video', field: 'videoUrl', width: '360px' },
    { label: 'Tên bài học', field: 'title', sortable: true },
    { label: 'Khoá học', field: 'courseName', sortable: true },
    { label: 'Ngày tạo', field: 'postedDate', sortable: true },
    { label: 'Trạng thái', field: 'status', sortable: true },
    { label: 'Thao tác', field: 'action', width: '110px', align: 'center' }
]

function formatDate(dateStr) {
    return dateStr ? new Date(dateStr).toLocaleDateString('vi-VN') : ''
}

function getFilterParams() {
    return Object.fromEntries(
        Object.entries(filters).map(([key, refVal]) => [key, refVal.value])
    )
}

// Gọi API
const fetchLessons = async (params = {}) => {
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

        const result = await getList(apiLesson, queryParams)
        console.log(result.data);
        
        lessonList.value = result.data || []
        totalCount.value = result.totalItems || 0
    } catch (error) {
        console.error('Lỗi khi tải danh sách khoá học:', error)
    }
}

// Nhận emit từ bảng con
const handleRequest = (params) => {
  const adjustedSort = params.sort?.map(s => {
    if (s.field === 'courseName') {
      return { ...s, field: 'course.title' } // sửa field sort
    }
    return s
  })
  fetchLessons({ ...params, sort: adjustedSort })
}

const confirmDelete = async (lessonId) => {

  const confirmed = window.confirm('Bạn có chắc muốn xoá khoá học này không?')
  if (!confirmed) return

  try {
    await deleteItem(`${apiLesson}/${lessonId}`)
    alert('Xoá thành công!')
    fetchLessons() // reload lại danh sách
  } catch (error) {
    console.error('Lỗi khi xoá:', error)
    alert('Đã xảy ra lỗi khi xoá bài học.')
  }
}

// Tải dữ liệu lần đầu
onMounted(() => fetchLessons())
</script>
<style scoped></style>