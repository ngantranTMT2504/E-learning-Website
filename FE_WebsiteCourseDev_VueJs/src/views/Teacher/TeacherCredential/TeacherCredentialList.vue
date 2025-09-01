<template>
  <div>
    <h2 class="fw-bold text-uppercase mb-4">Bằng cấp & Chứng chỉ</h2>
    <div class="row">
      <!-- Form bên phải -->
      <div class="col-xl-4 order-xl-2 mb-4">
        <div class="card shadow position-sticky" style="top: 80px;">
          <div class="card-body">
            <h5 class="card-title text-center fw-bold mb-3 text-uppercase">
              {{ isEditing ? 'Cập nhật Bằng cấp' : 'Thêm Bằng cấp' }}
            </h5>
            <form @submit.prevent="isEditing ? updateItem() : addQualification()">
              <div class="mb-2">
                <input v-model="form.certificateName" type="text" class="form-control" placeholder="Tên Bằng cấp"
                  required />
              </div>
              <div class="mb-2">
                <input v-model="form.issuingOrganization" type="text" class="form-control" placeholder="Tổ chức cấp"
                  required />
              </div>
              <div class="mb-2">
                <input v-model="form.issuedYear" type="number" class="form-control" placeholder="Năm cấp" required />
              </div>
              <div class="mb-3">
                <label class="form-label">Ảnh minh chứng</label>
                <input type="file" class="form-control" accept="image/*" @change="handleImageUpload" />
                <img v-if="certificateImage" :src="certificateImage" class="img-fluid rounded mt-2" alt="Preview" />
              </div>
               <ButtonCustom class="w-100">
                {{ isEditing ? 'Cập nhật' : 'Thêm mới' }}
              </ButtonCustom>
              <button type="button" @click="resetForm" class="btn btn-secondary w-100 mt-2">Làm mới</button>
            </form>
          </div>
        </div>
      </div>

      <!-- Danh sách bằng cấp -->
      <div class="col-xl-8 order-xl-1">
        <div v-if="qualifications.length > 0">
          <div v-for="item in qualifications" :key="item.id" class="card mb-4 position-relative">
            <!-- Nút sửa & xóa -->
            <div class="position-absolute top-0 end-0 p-2 z-2">
              <div class="d-flex gap-2">
                <a @click="editItem(index)"><ButtonCustom variant="white">Sửa</ButtonCustom></a>
                <a @click="deleteItem(index)"><ButtonCustom>Xoá</ButtonCustom></a>
              </div>
            </div>
            <div class="card-body">
              <h5 class="card-title">{{ item.certificateName }}</h5>
              <p class="card-text">Tổ chức cấp: {{ item.issuingOrganization }}</p>
              <p class="card-text">
                <small class="text-muted">Năm cấp: {{ item.issuedYear }}</small>
              </p>
              <img v-if="item.certificateImage" :src="item.certificateImage" class="img-fluid rounded mt-2 mb-2"
                alt="Ảnh minh chứng" width="150px"/>
            </div>
          </div>
        </div>
        <p v-else class="text-muted">Không có bằng cấp nào được tìm thấy.</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import {
  getList,
  createItem as postItem,
  updateItem as putItem,
  deleteItem as deleteItemById
} from '@/services/apiMiddlewareV2.js'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'
const apiUploadImage = '/api/upload-image'
const apiLesson = '/api/teacher-certificate'
const accountId = 4
const teacherId = ref(null);

// Danh sách và biến form
const qualifications = ref([])
const totalCount = ref(0)
const form = ref({
  id: null,
  certificateName: '',
  issuingOrganization: '',
  issuedYear: '',
  certificateImage: ''
})
const certificateImage = ref('')
const isEditing = ref(false)

// Load danh sách bằng cấp
const fetchCertificates = async () => {
  try {
    const queryParams = { accountId }
    const result = await getList(apiLesson, queryParams)
    qualifications.value = result.data || []
    totalCount.value = result.totalItems || 0
    if (qualifications.value.length > 0) {
      teacherId.value = qualifications.value[0].teacherId;

    }
  } catch (error) {
    console.error('Lỗi khi tải danh sách bằng cấp:', error)
  }
}

// Xử lý upload ảnh
// const handleImageUpload = (event) => {
//   const file = event.target.files[0]
//   if (file) {
//     const reader = new FileReader()
//     reader.onload = () => {
//       certificateImage.value = reader.result
//       form.value.certificateImage = reader.result
//     }
//     reader.readAsDataURL(file)
//   }

// function handleImageUpload(event) {
//   const file = event.target.files[0]
//   if (file) {
//     form.value.certificateImage = file.name // chỉ lấy tên file
//   }
// }

const handleImageUpload = async (event) => {
  const file = event.target.files?.[0]
  if (!file) return



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

      return
    }

    const data = await res.json()

    if (!data.url) {

      return
    }

    form.value.certificateImage = data.url

  } catch (error) {
    console.error('Lỗi upload bài tập:', error)

  }
}



// Thêm mới

const addQualification = async () => {
  if (
    !form.value.certificateName 
   
    
  ) {
    alert('Vui lòng nhập đầy đủ thông tin bắt buộc!')
    return
  }

  try {
    const payload = {
      teacherId: teacherId.value, // ✅ CHỖ NÀY RẤT QUAN TRỌNG
      certificateName: form.value.certificateName,
      certificateImage: form.value.certificateImage,
      issuedYear: form.value.issuedYear,
      issuingOrganization: form.value.issuingOrganization
    }

    console.log('Payload gửi lên:', payload)

    await postItem(apiLesson, payload)
    await fetchCertificates()
    resetForm()
  } catch (error) {

  }
}



// Cập nhật
const updateItem = async () => {
  try {
    await putItem(`${apiLesson}/${form.value.id}`, form.value)
    await fetchCertificates()
    resetForm()
  } catch (error) {
    console.error('Lỗi cập nhật:', error)
  }
}

// Xoá
const deleteItem = async (id) => {
  if (confirm('Bạn có chắc chắn muốn xoá?')) {
    try {
      await deleteItemById(`${apiLesson}/${id}`)
      await fetchCertificates()
    } catch (error) {
      console.error('Lỗi xoá:', error)
    }
  }
}

// Chỉnh sửa
const editItem = (item) => {
  form.value = { ...item }
  certificateImage.value = item.certificateImage || ''
  isEditing.value = true
}

// Làm mới form
const resetForm = () => {
  form.value = {
    id: null,
    certificateName: '',
    issuingOrganization: '',
    issuedYear: '',
    certificateImage: ''
  }
  certificateImage.value = ''
  isEditing.value = false
}

// Load lần đầu
onMounted(async () => {

  await fetchCertificates()
})

</script>

<style scoped>
/* Tuỳ chỉnh thêm nếu cần */
</style>
