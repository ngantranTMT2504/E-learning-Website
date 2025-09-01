<template>
  <div class="row g-4">
    <!-- Trái: Thông tin -->
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
            <input type="date" class="form-control" v-model="lesson.postedDate" disabled/>
          </div>
        </div>
      </section>
    </div>

    <!-- Phải: Upload & Preview -->
    <div class="col-lg-6">
      <section class="mb-4">
        <h5 class="fw-bold mb-3">Tài nguyên bài học</h5>

        <div class="mb-3">
          <label class="form-label">Video *</label>
          <input type="file" class="form-control" accept="video/*" @change="handleUpload($event, 'video')" />
          <div v-if="uploadProgress.video > 0" class="progress mt-2">
            <div class="progress-bar" :style="{ width: uploadProgress.video + '%' }">
              {{ uploadProgress.video }}%
            </div>
          </div>
        </div>

        <div>
          <label class="form-label">Tài liệu bài tập</label>
          <input type="file" class="form-control" accept=".pdf,.doc,.docx" @change="handleUpload($event, 'exercise')" />
          <div v-if="uploadProgress.exercise > 0" class="progress mt-2">
            <div class="progress-bar bg-success" :style="{ width: uploadProgress.exercise + '%' }">
              {{ uploadProgress.exercise }}%
            </div>
          </div>
        </div>
      </section>

      <section v-if="lesson.videoURL">
        <h5 class="fw-bold mb-3">Xem trước video</h5>
        <div class="video-preview border rounded bg-black overflow-hidden">
          <video controls class="w-100" :src="lesson.videoURL"></video>
        </div>
      </section>
    </div>
  </div>

  <!-- Nút lưu -->
  <!-- Nút ở cuối -->
  <div class="d-flex justify-content-between mt-4">
    <ButtonCustom @click="goBack">
      <i class="bi bi-arrow-left me-2"></i> Quay lại
    </ButtonCustom>

    <ButtonCustom @click="submitLesson">
      <i class="bi bi-save me-2"></i> Lưu bài học
    </ButtonCustom>
  </div>

</template>

<script setup>
import { reactive } from 'vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'

const props = defineProps({
  courseId: [String, Number]
})

const emit = defineEmits(['submit', 'back'])

const lesson = reactive({
  courseId: props.courseId,
  title: '',
  discription: '',
  videoURL: '',
  exerciseURL: '',
  lessonOrder: 1,
  postedDate: new Date().toISOString().slice(0, 10),
  status: '1'
})

const uploadProgress = reactive({
  video: 0,
  exercise: 0
})

const handleUpload = (event, type) => {
  const file = event.target.files[0]
  if (!file) return

  const url = URL.createObjectURL(file)
  uploadProgress[type] = 0

  const interval = setInterval(() => {
    if (uploadProgress[type] >= 100) {
      clearInterval(interval)
      lesson[`${type}URL`] = url
    } else {
      uploadProgress[type] += 10
    }
  }, 100)
}

const submitLesson = () => {
  console.log('Title:', lesson.title);
  if (!lesson.title || !lesson.videoURL) {
    alert('Vui lòng nhập tiêu đề và video!')
    return
  }

  emit('submit', { ...lesson })
}

const goBack = () => {
  emit('back')
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