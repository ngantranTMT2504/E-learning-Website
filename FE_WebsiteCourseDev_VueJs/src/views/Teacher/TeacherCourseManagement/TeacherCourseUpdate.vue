<template>
  <div>
    <BreadCrumbCustom :items="[
      { label: 'Khoá học', to: '/teacher/course/list' },
      { label: 'Cập nhật khoá học' }
    ]" class="mb-3" />

    <h2 class="fw-bold text-uppercase mb-3">Cập nhật khoá học</h2>

    <div class="card shadow-sm">
      <div class="card-body p-4">
        <div>
          <div class="row">
            <!-- Cột trái -->
            <div class="col-md-6">
              <div class="mb-3">
                <label class="form-label">Tên khoá học *</label>
                <input type="text" class="form-control" v-model="course.title" />
              </div>

              <div class="mb-3">
                <label class="form-label">Slug *</label>
                <input type="text" class="form-control" v-model="course.slug" disabled />
              </div>

              <div class="mb-3">
                <label class="form-label">Danh mục *</label>
                <select class="form-select" v-model.number="course.courseTypeId">
                  <option disabled value="-1">-- Chọn danh mục --</option>
                  <option v-for="type in courseTypes" :key="type.id" :value="type.id">{{ type.name }}</option>
                </select>
              </div>

              <div class="mb-3">
                <label class="form-label">Mô tả ngắn *</label>
                <input class="form-control" v-model="course.shortDiscription" rows="3" />
              </div>

              <div class="mb-3">
                <label class="form-label">Mô tả chi tiết *</label>
                <textarea class="form-control" v-model="course.discription" rows="5"></textarea>
              </div>
            </div>

            <!-- Cột phải -->
            <div class="col-md-6">
              <div class="mb-3">
                <label class="form-label">Giá *(VNĐ)</label>
                <input type="number" class="form-control" v-model="course.price" />
              </div>

              <div class="mb-3">
                <label class="form-label">Giảm giá (%)</label>
                <input type="number" step="0.01" class="form-control" v-model="course.discount" />
              </div>

              <div class="mb-3">
                <label class="form-label">Hình ảnh *</label>

                <div class="image-preview-wrapper" @click="triggerFileInput">
                  <template v-if="isUploading">
                    <RainbowLoading />
                  </template>


                  <template v-else-if="previewImage">
                    <img :src="previewImage" alt="Xem trước ảnh" class="image-preview" />
                  </template>

                  <template v-else>
                    <div class="image-empty-placeholder">
                      <i class="bi bi-image fs-1 mb-2"></i><br />
                      <span>Chưa có hình ảnh</span>
                    </div>
                  </template>
                </div>

                <input ref="hiddenFileInput" type="file" accept="image/*" style="display: none"
                  @change="handleImageUpload" />
              </div>

            </div>
          </div>

          <div class="d-flex justify-content-between">
            <RouterLink to="/teacher/course/list">
              <ButtonCustom class="btn-bare">Quay lại</ButtonCustom>
            </RouterLink>
            <a @click="submit">
              <ButtonCustom><i class="bi bi-save me-2"></i> Cập nhật</ButtonCustom>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, watch, onMounted } from 'vue'
import { useRoute, RouterLink, useRouter } from 'vue-router'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'
import BreadCrumbCustom from '@/components/Common/BreadCrumbCustom.vue'
import { getById, getList, updateItem } from '@/services/apiMiddlewareV2'
import SlugUtil from '@/utils/slugUtil'
import RainbowLoading from '@/components/Common/RainbowLoading.vue'

const route = useRoute()
const router = useRouter()
const courseId = route.params.id

const apiCourseType = '/api/course-type'
const apiUploadImage = '/api/upload-image'
const apiTeacherCourse = '/api/teacher/course'

const courseTypes = ref([])

const course = reactive({
  title: '',
  slug: '',
  teacherId: 5,
  commissionId: 1,
  courseTypeId: -1,
  shortDiscription: '',
  discription: '',
  price: 0,
  discount: 0,
  image: '',
  updatedDate: ''
})

watch(() => course.title, (newTitle) => {
  course.slug = SlugUtil.toSlug(newTitle)
})

const previewImage = ref('')
const hiddenFileInput = ref(null)
const uploadedNewImage = ref(false)
const isUploading = ref(false)

onMounted(async () => {
  try {
    courseTypes.value = await getList(apiCourseType)

    const courseData = await getById(`${apiTeacherCourse}/${courseId}`)
    Object.assign(course, courseData)
    previewImage.value = courseData.image || ''
  } catch (error) {
    console.error('Lỗi tải dữ liệu:', error)
  }
})

const triggerFileInput = () => {
  hiddenFileInput.value?.click()
}

async function handleImageUpload(event) {
  const file = event.target.files?.[0]
  if (!file) return

  isUploading.value = true // bật spinner

  try {
    const formData = new FormData()
    formData.append('file', file)

    const res = await fetch(import.meta.env.VITE_API_BASE_URL + apiUploadImage, {
      method: 'POST',
      body: formData
    })

    if (!res.ok) throw new Error('Upload thất bại')
    const data = await res.json()
    if (!data.url) throw new Error('Không nhận được URL ảnh từ server')

    course.image = data.url
    previewImage.value = data.url
    uploadedNewImage.value = true
  } catch (err) {
    console.error('Lỗi upload ảnh:', err)
    previewImage.value = ''
    course.image = ''
    uploadedNewImage.value = false
  } finally {
    isUploading.value = false // tắt spinner
  }
}

const submit = async () => {
  if (course.title === '') {
    alert('Vui lòng nhập tiêu đề!')
    return
  }

  try {
    const payload = { ...course }
    if (!uploadedNewImage.value) {
      payload.image = previewImage.value // giữ ảnh cũ nếu không upload mới
    }
    payload.updatedDate = new Date().toISOString()
    await updateItem(`${apiTeacherCourse}/${courseId}`, payload)

    router.push('/teacher/course/list');
  } catch (error) {
    console.error('Lỗi submit:', error)
    alert('Có lỗi khi cập nhật khoá học.')
  }
}
</script>

<style scoped>
.image-preview-wrapper {
  height: 300px;
  border: 1px dashed #bbb;
  border-radius: 6px;
  padding: 10px;
  background-color: #f9f9f9;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: border-color 0.2s ease;
}

.image-preview-wrapper:hover {
  border-color: #5e72e4;
}

.image-preview {
  width: auto;
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
}

.image-empty-placeholder {
  text-align: center;
  color: #999;
  font-size: 14px;
}
</style>
