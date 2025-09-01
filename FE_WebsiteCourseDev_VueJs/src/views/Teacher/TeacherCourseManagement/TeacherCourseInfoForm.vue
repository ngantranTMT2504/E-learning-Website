<template>
  <div>
    <div class="row">
      <!-- Cột trái -->
      <div class="col-lg-6">
        <div class="mb-3">
          <label class="form-label">Tên khoá học *</label>
          <input type="text" class="form-control" v-model="localCourse.title" />
        </div>

        <div class="mb-3">
          <label class="form-label">Slug *</label>
          <input type="text" class="form-control" v-model="localCourse.slug" disabled/>
        </div>

        <div class="mb-3">
          <label class="form-label">Danh mục *</label>
          <select class="form-select" v-model.number="localCourse.courseTypeId">
            <option disabled value="-1">-- Chọn danh mục --</option>
            <option v-for="type in courseTypes" :key="type.id" :value="type.id">
              {{ type.name }}
            </option>
          </select>
        </div>

        <div class="mb-3">
          <label class="form-label">Mô tả ngắn *</label>
          <input class="form-control" v-model="localCourse.shortDiscription"></input>
        </div>
        
        <div class="mb-3">
          <label class="form-label">Mô tả chi tiết *</label>
          <textarea class="form-control" v-model="localCourse.discription" rows="5"></textarea>
        </div>
      </div>

      <!-- Cột phải - Hình ảnh -->
      <div class="col-lg-6">
        <div class="mb-3">
          <label class="form-label">Giá *(VNĐ)</label>
          <input type="number" class="form-control" v-model="localCourse.price" />
        </div>

        <div class="mb-3">
          <label class="form-label">Giảm giá (%)</label>
          <input type="number" step="0.01" class="form-control" v-model="localCourse.discount" />
        </div>
        
        <div class="mb-3 d-none">
          <label class="form-label">Hình ảnh</label>
          <input
            type="file"
            class="form-control"
            accept="image/*"
            @change="handleImageUpload"
          />
        </div>

        <div class="mb-3">
          <label class="form-label">Hình ảnh *</label>
          <div class="image-preview-wrapper" @click="triggerFileInput">
            <img
              v-if="isValidPreviewImage"
              :src="previewImage"
              alt="Xem trước ảnh"
              class="image-preview"
            />
            <div v-else class="image-empty-placeholder">
              <i class="bi bi-image fs-1 mb-2"></i><br />
              <span>Chưa có hình ảnh</span>
            </div>
          </div>
          <!-- Input file hidden để click từ vùng preview -->
          <input
            ref="hiddenFileInput"
            type="file"
            accept="image/*"
            style="display: none"
            @change="handleImageUpload"
          />
        </div>
      </div>
    </div>

    <div class="mt-2 text-end">
      <ButtonCustom @click="$emit('next')">Tiếp tục</ButtonCustom>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, watch, onMounted, computed } from 'vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'
import { getList } from '@/services/apiMiddlewareV2.js'

const apiCourseTypes ="/api/course-type"
const courseTypes = ref([])
const previewImage = ref('')
const hiddenFileInput = ref(null)
const apiUploadImage =import.meta.env.VITE_API_BASE_URL + '/api/upload-image'

const props = defineProps({
  modelValue: {
    type: Object,
    required: true
  }
})

const emit = defineEmits(['update:modelValue', 'next'])

const defaultCourse = {
  title: '',
  slug: '',
  teacherId: 5,
  courseTypeId: -1,
  commissionId:1,
  shortDiscription: '',
  discription: '',
  price: 0,
  discount: 0,
  image: null
}

const localCourse = reactive({
  ...defaultCourse,
  ...(props.modelValue || {})
})

// Tự động tạo slug khi nhập tiêu đề
function toSlug(str) {
  return str
    .normalize('NFD')                         
    .replace(/[\u0300-\u036f]/g, '')          
    .toLowerCase()
    .replace(/[^a-z0-9\s-]/g, '')             
    .trim()
    .replace(/\s+/g, '-')                     
}


watch(() => localCourse.title, (newTitle) => {
  localCourse.slug = toSlug(newTitle)
})

// Kiểm tra ảnh blob hợp lệ
const isValidPreviewImage = computed(() =>
  previewImage.value &&
  previewImage.value !== 'blob:null' &&
  !previewImage.value.includes('blob:null')
)

// Xử lý upload ảnh
function triggerFileInput() {
  hiddenFileInput.value.click()
}

async function handleImageUpload(event) {
  const file = event.target.files?.[0]
  if (!file) return

  try {
    const tempUrl = URL.createObjectURL(file)
    previewImage.value = tempUrl
    localCourse.imageTempUrl = tempUrl
    localStorage.setItem('previewImageTempUrl', tempUrl)

    const formData = new FormData()
    formData.append('file', file)

    const res = await fetch(apiUploadImage, {
      method: 'POST',
      body: formData
    })

    if (!res.ok) throw new Error('Upload thất bại')

    const data = await res.json()

    // 💾 Lưu URL trả về vào form
    localCourse.image = data.url // ví dụ: https://cloudinary.com/... hoặc đường dẫn ảnh trên server
    previewImage.value = data.url

    // ✅ Xoá blob tạm
    URL.revokeObjectURL(tempUrl)
    localStorage.removeItem('previewImageTempUrl')
  } catch (err) {
    console.error('Lỗi upload ảnh:', err)
    resetImageState()
  }
}

function resetImageState() {
  previewImage.value = ''
  localCourse.image = null
  localCourse.imageTempUrl = null
  localStorage.removeItem('previewImageTempUrl')
}


// Gọi API danh mục
onMounted(async () => {
  try {
    courseTypes.value = await getList(apiCourseTypes)
  } catch (err) {
    console.error('Không thể tải danh mục:', err)
  }

  const tempUrl = localStorage.getItem('previewImageTempUrl')
  if (tempUrl?.startsWith('blob:')) {
    previewImage.value = tempUrl
  } else if (typeof localCourse.image === 'string') {
    previewImage.value = localCourse.image
  }
})

// Sync dữ liệu ra ngoài
watch(localCourse, (newVal) => {
  emit('update:modelValue', { ...newVal })
}, { deep: true })

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
