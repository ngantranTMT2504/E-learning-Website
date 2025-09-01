<template>
    <TableListCustom :data="courseList" :columns="columns" :totalRows="totalCount" :pageSize="3"
        :rowsPerPageOptions="[1,2,3,4]" @onRequest="fetchCourses">
        <template #cell-createdAt="{ row }">
            <span class="text-muted">{{ formatDate(row.createdAt) }}</span>
        </template>
        <template #cell-action="{ row }">
            <SwitchCheckbox />
        </template>
    </TableListCustom>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import TableListCustom from '@/components/Common/TableListCustom.vue'
import SwitchCheckbox from '@/components/Common/SwitchCheckbox.vue'

const courseList = ref([])
const totalCount = ref(0)

const changeStatus = async (id) => {
    
};

const columns = [
    { label: 'Tên khóa học', field: 'title', sortable: true },
    { label: 'Chuyên đề', field: 'category', sortable: true },
    { label: 'Ngày tạo', field: 'createdAt', sortable: true },
    { label: 'Thao tác', field: 'action', width: '100px', align: 'center'}
]

function formatDate(dateStr) {
    return new Date(dateStr).toLocaleDateString('vi-VN')
}

// async function fetchCourses(params) {
//   try {
//     const res = await axios.get('/api/courses', { params })
//     courseList.value = res.data.items ?? []
//     totalCount.value = res.data.total ?? 0
//   } catch (err) {
//     console.error('Lỗi khi tải khóa học:', err)
//     courseList.value = []
//     totalCount.value = 0
//   }
// }

function handleView(row) {
  console.log('Xem khoá học:', row)
  // router.push(`/teacher/courses/${row.id}`)
}

function fetchCourses() {
    const fakeData = [
        { title: 'Vue 3 cơ bản', category: 'Frontend', createdAt: '2024-12-01' },
        { title: 'NodeJS REST API', category: 'Backend', createdAt: '2025-01-10' },
        { title: 'React nâng cao', category: 'Frontend', createdAt: '2024-12-15' },
        { title: 'Laravel Security', category: 'Backend', createdAt: '2025-02-01' },
        { title: 'TypeScript Patterns', category: 'Frontend', createdAt: '2025-03-01' },
        { title: 'MongoDB Essentials', category: 'Database', createdAt: '2025-03-10' },
        { title: 'GraphQL Basics', category: 'API', createdAt: '2025-03-15' },
        { title: 'Tailwind CSS', category: 'Frontend', createdAt: '2024-11-25' },
        { title: 'Docker Fundamentals', category: 'DevOps', createdAt: '2025-04-01' },
        { title: 'CI/CD với GitHub Actions', category: 'DevOps', createdAt: '2025-04-10' }
    ]

    courseList.value = fakeData
    totalCount.value = fakeData.length
}

onMounted(fetchCourses)
</script>
<style scoped>

</style>