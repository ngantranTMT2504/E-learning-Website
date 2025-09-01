<template>
  <div>
    <!-- Breadcrumb -->
    <BreadCrumbCustom :items="[
      { label: 'Bài học', to: '/teacher/lesson/list' },
      { label: 'Cập nhật bài học' }
    ]" class="mb-3" />

    <!-- Title -->
    <h2 class="fw-bold text-uppercase mb-3">Cập nhật bài học</h2>

    <div class="card shadow-sm">
      <div class="card-body p-4">
        <div class="row g-4">
          <!-- Cột trái: Thông tin & Thiết lập -->
          <div class="col-lg-6">
            <!-- 1. Thông tin bài học -->
            <section class="mb-4">
              <h5 class="fw-bold mb-3">Thông tin bài học</h5>

              <div class="mb-3">
                <label class="form-label">Tiêu đề *</label>
                <input type="text" class="form-control" v-model="lesson.title"
                  placeholder="Ví dụ: Giới thiệu về Python" />
              </div>

              <div class="mb-3">
                <label class="form-label">Khóa học *</label>
                <select class="form-select" v-model="lesson.courseId">
                  <option disabled value="-1">-- Chọn khóa học --</option>
                  <option v-for="course in courses" :key="course.id" :value="course.id">
                    {{ course.title }}
                  </option>
                </select>
              </div>

              <div class="mb-3">
                <label class="form-label">Thứ tự</label>
                <input type="number" class="form-control" v-model.number="lesson.lessonOrder" min="2" />
              </div>
            </section>

            <!-- 2. Thiết lập thêm -->
            <section>
              <h5 class="fw-bold mb-3">Thiết lập bổ sung</h5>
              <div class="mb-3">
                <label class="form-label">Mô tả</label>
                <!-- <textarea class="form-control" rows="4" v-model="lesson.discription"
                  placeholder="Nội dung mô tả bài học..."></textarea> -->
                <TextAreaCustom v-model="lesson.discription" />
              </div>
            </section>
          </div>

          <!-- Cột phải: Upload & Preview -->
          <div class="col-lg-6">
            <section class="mb-4">
              <h5 class="fw-bold mb-3">Tài nguyên bài học</h5>
              <div class="mb-3">
                <label class="form-label">Video *</label>
                <input type="file" class="form-control" accept="video/*" @change="handleUpload($event, 'video')" />
                <div v-if="uploadProgress.video > 0" class="progress mt-2">
                  <div class="progress-bar" :style="{ width: uploadProgress.video + '%' }">{{ uploadProgress.video }}%
                  </div>
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
        </div>

        <!-- Nút Lưu -->
        <div class="text-end mt-4">
          <a @click="submit">
            <ButtonCustom><i class="bi bi-save me-2"></i> Cập nhật bài học</ButtonCustom>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue'
import BreadCrumbCustom from '@/components/Common/BreadCrumbCustom.vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'
import { useRoute, useRouter } from 'vue-router'
import { getById, getList, updateItem } from '@/services/apiMiddlewareV2'
import TextAreaCustom from '@/components/Common/TextAreaCustom.vue'

//api 
const apiUploadImage = '/api/upload-image'
const apiUploadVideo = '/api/lesson/upload-video'
const apiTeacherCourse = '/api/teacher/course'
const apiLesson = '/api/lesson'

const route = useRoute()
const router = useRouter()
const lessonId = route.params.id
console.log(lessonId);

const accountId = 4
const courses = ref([])

const lesson = reactive({
  courseId: -1,
  title: '',
  discription: '',
  videoUrl: '',
  exerciseUrl: '',
  lessonOrder: 2,
  postedDate: new Date().toISOString().slice(0, 10),
  status: true
});

// Load khoá học
onMounted(async () => {
  try {
    const res = await getList(`${apiTeacherCourse}?accountId=${accountId}`)
    courses.value = res.data
    console.log(courses.value);

    const lessonData = await getById(`${apiLesson}/${lessonId}`)
    Object.assign(lesson, lessonData)
  } catch (error) {
    console.error('Lỗi tải danh mục:', error)
  }
})

const uploadProgress = reactive({
  video: 0,
  exercise: 0
})

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

const submit = async () => {
  if (!lesson.title || !lesson.videoUrl) {
    alert('Vui lòng nhập tiêu đề và video!')
    return
  }

  try {
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

    await updateItem(`${apiLesson}/${lessonId}`, lessonPayload)

    router.push('/teacher/lesson/list');
  } catch (error) {
    console.error('Lỗi submit:', error)
    alert(' Có lỗi khi tạo bài học.')
  }
}

</script>
<style scoped>
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
