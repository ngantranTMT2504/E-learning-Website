<template>
    <div>
        <div class="row wrapper-chart">
            <div class="col-lg-6">

            </div>
            <div class="col-lg-6">

            </div>

        </div>
        <div class="table-statistical">
            <TableListCustom :data="typecourseList" :columns="columns" :totalRows="totalCount" :pageSize="3"
                :rowsPerPageOptions="[1, 2, 3, 4]" @onRequest="fetchCourses">
                <template #cell-createdAt="{ row }">
                    <span class="text-muted">{{ formatDate(row.createdAt) }}</span>
                </template>
                <template #cell-action="{ row }">
                    <ActionMenuCustom :uid="row.id" :menuItems="[
                        { label: 'Thêm bài học', icon: 'bi bi-eye', class: 'text-primary', to: `/teacher/course/view/${row.id}` },
                        { label: 'Xem', icon: 'bi bi-eye', class: 'text-primary', to: `/teacher/course/view/${row.id}` },
                        { label: 'Sửa', icon: 'bi bi-pencil-square', class: 'text-warning', to: `/teacher/course/update/id=1` },
                        { label: 'Xoá', icon: 'bi bi-trash', class: 'text-danger', onClick: () => confirmDelete(row.id) }
                    ]" />
                </template>
            </TableListCustom>

        </div>
    </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import TableListCustom from '@/components/Common/TableListCustom.vue'
import ActionMenuCustom from '@/components/Common/ActionMenuCustom.vue'

const typecourseList = ref([])
const totalCount = ref(0)

const columns = [
    { label: 'Giáo viên', field: 'teacher', sortable: true },
    { label: 'Khóa học', field: 'course'},
    { label: 'học viên', field: 'student'},
    { label: 'Ngày đăng ký', field: 'date'},
    { label: 'Thao tác', field: 'action', width: '100px', align: 'center'}

]

function fetchCourses() {
    const fakeData = [
       {
    teacher: "Nguyễn Văn A", course: 'Nguyễn Văn A', student: 'Ngỗ Thị Manh', date: '2025-07-05'
  },
  {
    teacher: "Lê Thị C",
    course: "Thiết kế Web",
    student: "Ngô Văn D",
    date: "2025-07-10"
  },
  {
    teacher: "Phạm Văn E",
    course: "Cơ sở dữ liệu",
    student: "Lý Thị F",
    date: "2025-07-20"
  },
  {
    teacher: "Phạm Văn E",
    course: "Cơ sở dữ liệu",
    student: "Lý Thị F",
    date: "2025-07-20"
  },
  {
    teacher: "Phạm Văn E",
    course: "Cơ sở dữ liệu",
    student: "Lý Thị F",
    date: "2025-07-20"
  }
    ]

    typecourseList.value = fakeData
    totalCount.value = fakeData.length
}

onMounted(fetchCourses);
</script>
<style></style>