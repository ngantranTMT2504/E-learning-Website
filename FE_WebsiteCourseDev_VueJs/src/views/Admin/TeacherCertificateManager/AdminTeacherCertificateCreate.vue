<template>
  <BreadCrumbCustom
    :items="[
      { label: 'Khoá học', to: '/admin/teacher-certificate/create' },
      { label: 'Thêm loại khoá học' }
    ]"
    class="mb-3"
  />

  <div class="card p-4">
    <h3 class="fw-bold mb-3 text-uppercase">Thêm chứng chỉ giáo viên</h3>

    <form @submit.prevent="handleSubmit">
      <div class="mb-3">
        <label class="form-label">Tên loại khoá học</label>
        <input
          v-model="form.name"
          type="text"
          class="form-control"
          placeholder="Nhập tên loại khoá học"
          required
        />
      </div>

      <div class="form-check mb-3">
        <input
          v-model="form.status"
          class="form-check-input"
          type="checkbox"
          id="statusCheck"
        />
        <label class="form-check-label" for="statusCheck">
          Hoạt động
        </label>
      </div>

      <div class="d-flex justify-content-end gap-2">
        <RouterLink to="/admin/type-course/list" class="btn btn-secondary">
          Quay lại
        </RouterLink>
        <button type="submit" class="btn btn-primary">
          Lưu
        </button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import BreadCrumbCustom from '@/components/Common/BreadCrumbCustom.vue'


const router = useRouter()

const form = ref({
  name: '',
  status: true
})

async function handleSubmit() {
  try {
    await createCourseType(form.value)
   console.log("đăng ký thành công")
    router.push('/admin/type-course/list')
  } catch (err) {
    console.error(err)
    console.log("đăng ký thất bại")
  }
}
</script>

<style scoped>
.card {
  max-width: 600px;
  margin: 0 auto;
}
</style>
