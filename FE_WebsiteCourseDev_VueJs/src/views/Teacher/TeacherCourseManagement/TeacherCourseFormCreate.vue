<template>
  <div>
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
        <button class="nav-link" :class="{ active: step === 2, disabled: !courseValid }" :disabled="!courseValid"
          @click="step = 2">
          <span class="step-number">2</span>
          <span class="step-label">Bài học đầu tiên</span>
        </button>
      </li>
    </ul>

    <!-- Step Content -->
    <TeacherCourseInfoForm v-if="step === 1" v-model="course" @next="goToStep2" />
    <TeacherFirstLessonForm v-if="step === 2 && courseValid" :courseId="courseId" @submit="submitCourse"
      @back="step = 1" />
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import TeacherCourseInfoForm from './TeacherCourseInfoForm.vue'
import TeacherFirstLessonForm from './TeacherFirstLessonForm.vue'
import BreadCrumbCustom from '@/components/Common/BreadCrumbCustom.vue'
import { createItem } from '@/services/apiMiddlewareV2'

const step = ref(1)
const courseId = ref('')

const course = ref(getResetCourse())
const lesson = ref(getResetLesson())

function getResetCourse() {
  return {
    title: '',
    slug: '',
    teacherId: 5,
    courseTypeId: -1,
    shortDiscription: '',
    discription: '',
    price: 0,
    discount: 0,
    image: null
  }
}

function getResetLesson() {
  return {
    title: '',
    videoUrl: '',
    discription: ''
  }
}

const courseValid = computed(() =>
  course.value.title.trim() !== '' &&
  course.value.discription.trim() !== '' &&
  course.value.courseTypeId !== -1 &&
  course.value.shortDiscription.trim() !== ''
)

function goToStep2() {
  if (courseValid.value) {
    step.value = 2
  } else {
    alert('Vui lòng nhập đầy đủ thông tin khoá học!')
  }
}

async function submitCourse(lessonData) {
  try {
    const formDataCourse = new FormData()
    for (const key in course.value) {
      const val = course.value[key]

      if (val === null || val === undefined || val === '') continue  // bỏ qua giá trị rỗng

      formDataCourse.append(key, val)  // KHÔNG ép String
    }

    const courseRes = await createItem('/api/teacher/course', formDataCourse)

    const createdCourseId = courseRes.id || courseRes.data?.id
    if (!createdCourseId) throw new Error('Không lấy được ID khoá học!')

    courseId.value = createdCourseId

    const lessonPayload = {
      ...lessonData,
      courseId: createdCourseId,
      lessonOrder: 1,
      postedDate: new Date().toISOString().slice(0, 10),
      status: '1'
    }

    await createItem('/api/lesson', lessonPayload)

    alert('Khoá học & bài học đã được tạo thành công!')
    course.value = getResetCourse()
    lesson.value = getResetLesson()
    step.value = 1
    courseId.value = ''

  } catch (err) {
    console.error('Lỗi tạo:', err)
    alert('Tạo thất bại!')
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
</style>