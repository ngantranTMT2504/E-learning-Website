<template>
  <div>
    <!-- Breadcrumb -->
    <BreadCrumbCustom :items="[
      { label: 'Khoá học', to: '/teacher/course/list' },
      { label: 'Thêm khoá học' }
    ]" class="mb-3" />

    <h2 class="fw-bold text-uppercase mb-3">Thêm khoá học mới</h2>

    <!-- Tabs -->
    <ul class="nav nav-tabs custom-tabs mb-4">
      <li class="nav-item">
        <button class="nav-link" :class="{ active: step === 1 }" @click="step = 1">
          <span class="step-number">1</span>
          <span class="step-label">Thông tin khoá học</span>
        </button>
      </li>
      <li class="nav-item">
        <button class="nav-link" :class="{ active: step === 2, disabled: !isCourseValid }" :disabled="!isCourseValid"
          @click="step = 2">
          <span class="step-number">2</span>
          <span class="step-label">Bài học đầu tiên</span>
        </button>
      </li>
    </ul>

    <!-- Step 1 -->
    <div v-if="step === 1">
      <div class="row">
        <!-- Cột trái -->
        <div class="col-lg-6">
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
            <input class="form-control" v-model="course.shortDiscription" />
          </div>

          <div class="mb-3">
            <label class="form-label">Mô tả chi tiết *</label>
            <textarea class="form-control" v-model="course.discription" rows="5"></textarea>
          </div>
        </div>

        <!-- Cột phải -->
        <div class="col-lg-6">
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

      <div class="mt-2 text-end">
        <ButtonCustom @click="step = 2">Tiếp tục</ButtonCustom>
      </div>
    </div>

    <!-- Step 2 -->
    <div v-if="step === 2 && isCourseValid" class="row g-4">
      <div class="col-lg-6">
        <section class="mb-4">
          <h5 class="fw-bold mb-3">Thông tin bài học</h5>
          <div class="mb-3">
            <label class="form-label">Tiêu đề *</label>
            <input type="text" class="form-control" v-model="lesson.title" placeholder="Ví dụ: Giới thiệu Python" />
          </div>
          <div class="mb-3">
            <label class="form-label">Mô tả</label>
            <textarea class="form-control" rows="8" v-model="lesson.discription"
              placeholder="Mô tả bài học..."></textarea>
          </div>
        </section>

        <section>
          <h5 class="fw-bold mb-3">Thiết lập bổ sung</h5>
          <div class="row g-3">
            <div class="col-md-6">
              <label class="form-label">Thứ tự</label>
              <input type="number" class="form-control" v-model.number="lesson.lessonOrder" disabled />
            </div>
            <div class="col-md-6">
              <label class="form-label">Ngày đăng</label>
              <input type="date" class="form-control" v-model="lesson.postedDate" disabled />
            </div>
          </div>
        </section>
      </div>

      <div class="col-lg-6">
        <section class="mb-4">
          <h5 class="fw-bold mb-3">Tài nguyên bài học</h5>
          <div class="mb-3">
            <label class="form-label">Video *</label>
            <input type="file" class="form-control" accept="video/*" @change="handleUpload($event, 'video')" />
            <div v-if="uploadProgress.video > 0" class="progress mt-2">
              <div class="progress-bar" :style="{ width: uploadProgress.video + '%' }">{{ uploadProgress.video }}%</div>
            </div>
          </div>

          <div>
            <label class="form-label">Tài liệu bài tập</label>
            <input type="file" class="form-control" accept=".pdf,.doc,.docx"
              @change="handleUploadExercise($event, 'exercise')" />
            <div v-if="uploadProgress.exercise > 0" class="progress mt-2">
              <div class="progress-bar bg-success" :style="{ width: uploadProgress.exercise + '%' }">
                {{ uploadProgress.exercise }}%
              </div>
            </div>
          </div>
        </section>

        <section v-if="lesson.videoUrl">
          <h5 class="fw-bold mb-3">Xem trước video</h5>
          <div class="video-preview border rounded bg-black overflow-hidden">
            <video controls class="w-100" :src="lesson.videoUrl"></video>
          </div>
        </section>
      </div>

      <!-- Nút lưu -->
      <div class="d-flex justify-content-between mt-4">
        <ButtonCustom @click="step = 1">
          <i class="bi bi-arrow-left me-2"></i> Quay lại
        </ButtonCustom>
        <a @click="submitAll">
          <ButtonCustom><i class="bi bi-save me-2"></i> Lưu bài học</ButtonCustom>
        </a>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, reactive, computed, onMounted, watch } from 'vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'
import BreadCrumbCustom from '@/components/Common/BreadCrumbCustom.vue'
import { getList, createItem } from '@/services/apiMiddlewareV2'
import SlugUtil from '@/utils/slugUtil'
import { useRouter } from 'vue-router';
import RainbowLoading from '@/components/Common/RainbowLoading.vue'

//api 
const apiCourseType = '/api/course-type'
const apiUploadImage = '/api/upload-image'
const apiUploadVideo = '/api/lesson/upload-video'
const apiTeacherCourse = '/api/teacher/course'
const apiLesson = '/api/lesson'

const accountId = 2
const step = ref(1)
const courseTypes = ref([])
const router = useRouter();

const course = reactive({
  title: '',
  slug: '',
  accountId: accountId,
  commissionId: 1,
  courseTypeId: -1,
  shortDiscription: '',
  discription: '',
  price: 0,
  discount: 0,
  image: '',
  status: true
})

watch(() => course.title, (newTitle) => {
  course.slug = SlugUtil.toSlug(newTitle)
})

const previewImage = ref('')
const hiddenFileInput = ref(null)
const isUploading = ref(false)

const lesson = reactive({
  courseId: null,
  title: '',
  discription: '',
  videoUrl: '',
  exerciseUrl: '',
  lessonOrder: 1,
  postedDate: new Date().toISOString().slice(0, 10),
  status: true
})

const uploadProgress = reactive({
  video: 0,
  exercise: 0
})

const isCourseValid = computed(() => {
  return course.title && course.slug && course.courseTypeId !== -1
})

// Load danh mục từ backend
onMounted(async () => {
  try {
    const res = await getList(apiCourseType)
    courseTypes.value = res.data.filter(type => type.status == true)
  } catch (error) {
    console.error('Lỗi tải danh mục:', error)
  }
})

// Upload thumbnail qua API riêng
const triggerFileInput = () => {
  hiddenFileInput.value?.click()
}

async function handleImageUpload(event) {
  const file = event.target.files?.[0]
  if (!file) return

  isUploading.value = true

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

    // Lưu ảnh vào course dưới dạng chuỗi
    course.image = data.url
    previewImage.value = data.url

  } catch (err) {
    console.error('Lỗi upload ảnh:', err)

    // Reset ảnh về trạng thái rỗng
    previewImage.value = ''
    course.image = ''
  } finally {
    isUploading.value = false
  }
}


// Upload video qua API
const handleUpload = async (event, type) => {
  const file = event.target.files?.[0]
  if (!file) return

  uploadProgress[type] = 20

  const formData = new FormData()
  formData.append('file', file)

  try {
    const res = await fetch(import.meta.env.VITE_API_BASE_URL + apiUploadVideo, {
      method: 'POST',
      body: formData
    })

    const data = await res.json()

    const videoUrl = data?.url
    if (!videoUrl) {
      console.error('Không có URL video trong phản hồi từ server')
      uploadProgress[type] = 0
      return
    }

    if (type === 'video') {
      lesson.videoUrl = videoUrl
    }

    uploadProgress[type] = 100
  } catch (error) {
    console.error(`Lỗi upload ${type}:`, error)
    uploadProgress[type] = 0
  }
}

const handleUploadExercise = async (event) => {
  const file = event.target.files?.[0]
  if (!file) return

  uploadProgress.exercise = 20

  const formData = new FormData()
  formData.append('file', file)

  try {
    const res = await fetch(import.meta.env.VITE_API_BASE_URL + apiUploadImage, {
      method: 'POST',
      body: formData

    })

    if (!res.ok) {
      const errText = await res.text()
      console.error('Lỗi từ server:', errText)
      uploadProgress.exercise = 0
      return
    }

    const data = await res.json()

    if (!data.url) {
      console.error('Không nhận được URL từ phản hồi server')
      uploadProgress.exercise = 0
      return
    }

    lesson.exerciseUrl = data.url
    uploadProgress.exercise = 100
  } catch (error) {
    console.error('Lỗi upload bài tập:', error)
    uploadProgress.exercise = 0
  }
}



// Tạo khoá học + bài học
const submitAll = async () => {
  if (!lesson.title || !lesson.videoUrl) {
    alert('Vui lòng nhập tiêu đề và video!')
    return
  }

  try {
    // Tạo khóa học
    const coursePayload = {
      title: course.title,
      slug: course.slug,
      accountId: course.accountId,
      commissionId: course.commissionId,
      courseTypeId: course.courseTypeId,
      shortDiscription: course.shortDiscription,
      discription: course.discription,
      price: course.price,
      discount: course.discount,
      image: course.image,
      status: course.status
    }

    const courseRes = await createItem(apiTeacherCourse, coursePayload)
    lesson.courseId = courseRes.id

    // Tạo bài học
    const lessonPayload = {
      courseId: lesson.courseId,
      title: lesson.title,
      discription: lesson.discription,
      videoUrl: lesson.videoUrl,
      exerciseUrl: lesson.exerciseUrl,
      lessonOrder: lesson.lessonOrder,
      postedDate: lesson.postedDate,
      status: lesson.status
    }

    await createItem(apiLesson, lessonPayload)

    router.push('/teacher/course/list');
  } catch (error) {
    console.error('Lỗi submit:', error)
    alert(' Có lỗi khi tạo khoá học hoặc bài học.')
  }
}

</script>

<style scoped>
.custom-tabs .nav-link {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 10px 20px;
  font-weight: 500;
  color: #555;
  border: none;
  border-bottom: 3px solid transparent;
  transition: all 0.3s ease;
  background-color: transparent;
}

.custom-tabs .nav-link .step-number {
  display: inline-block;
  background: #dee2e6;
  color: #000;
  border-radius: 50%;
  width: 28px;
  height: 28px;
  line-height: 28px;
  text-align: center;
  font-size: 14px;
  font-weight: bold;
}

.custom-tabs .nav-link.active {
  color: var(--green-primary);
  border-bottom: 3px solid var(--green-primary);
  background-color: #f8f9fa;
}

.custom-tabs .nav-link.active .step-number {
  background: var(--green-primary);
  color: #fff;
}

.custom-tabs .nav-link.disabled {
  pointer-events: none;
  opacity: 0.5;
}

/* Smooth fade animation */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

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

.progress {
  height: 20px;
  border-radius: 10px;
}

.progress-bar {
  font-weight: 500;
}

textarea {
  resize: vertical;
}

.video-preview {
  aspect-ratio: 16/9;
  background: #000;
}

video {
  max-height: 100%;
  border-radius: 6px;
}
</style>