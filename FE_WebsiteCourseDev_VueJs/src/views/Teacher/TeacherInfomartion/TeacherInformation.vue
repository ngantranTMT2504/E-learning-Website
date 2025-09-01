<template>
  <div>
    <h2 class="fw-bold text-uppercase mb-3">Thông tin cá nhân</h2>
    <div class="row">
      <!-- Avatar & Info -->
      <div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
        <div class="card shadow position-sticky" style="top: 80px;">
          <div class="card-body text-center">
            <label class="avatar-wrapper mx-auto mb-3" for="avatar-input">
              <template v-if="isUploading">
                <div class="spinner-border loading-custom" role="status"></div>
              </template>
              <div v-else-if="form.avatar" class="overflow-hidden">
                <img :src="form.avatar" class="avatar-image object-fit-cover" alt="" />
              </div>
              <div v-else>
                <div class="avatar-empty">
                  <i class="bi bi-image fs-2 color-image-icon"></i>
                  <div class="mt-2 text-muted">Ảnh đại diện</div>
                </div>
              </div>

              <input type="file" id="avatar-input" class="d-none" @change="handleAvatarUpload" accept=".png,.jpg,.gif,.jpeg"/>
            </label>
            <h4 class="mt-3">{{ form.fullname }}</h4>
            <div class="text-muted mb-1">
              <i class="bi bi-envelope me-1"></i>{{ form.email }}
            </div>
            <div class="text-muted mb-1">
              <i class="bi bi-geo-alt me-1"></i>TP. Hồ Chí Minh, Việt Nam
            </div>
            <div class="text-muted">
              <i class="bi bi-telephone me-1"></i>0123 456 789
            </div>
          </div>
        </div>
      </div>

      <!-- Form -->
      <div class="col-xl-8 order-xl-1">
        <div class="card shadow-sm bg-light">
          <div class="card-body">
            <h6 class="text-muted text-uppercase mb-3">Thông tin cá nhân</h6>
            <div class="mb-3">
              <label class="form-label">Email</label>
              <input v-model="form.email" type="email" class="form-control" disabled />
            </div>
            <div class="mb-3">
              <label class="form-label">Họ tên</label>
              <input v-model="form.fullname" type="text" class="form-control" />
            </div>
            <div class="row">
              <div class="col-md-6 mb-3">
                <label class="form-label">Giới tính</label>
                <select v-model="form.gender" class="form-select">
                  <option value="">-- Chọn giới tính --</option>
                  <option value="true">Nam</option>
                  <option value="false">Nữ</option>
                </select>
              </div>
              <div class="col-md-6 mb-3">
                <label class="form-label">Ngày sinh</label>
                <input v-model="form.birthday" type="date" class="form-control" />
              </div>
            </div>
            <div class="d-flex justify-content-end">
              <a @click="saveProfile"><ButtonCustom>Lưu thông tin</ButtonCustom></a>
            </div>
          </div>
        </div>

        <h2 class="fw-bold text-uppercase my-3">Đổi mật khẩu</h2>
        <TeacherChangePassword />
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'
import TeacherChangePassword from './TeacherChangePassword.vue'
import { getList, updateItem } from '@/services/apiMiddlewareV2'

const apiUploadImage = '/api/upload-image'
const apiAccount = '/api/accounts'
const accountId = 4

const avatar = ref('')
const isUploading = ref(false)
const form = ref({
  email: '',
  fullname: '',
  gender: '',
  birthday: '',
  avatar: ''
})

const getAccount = async () => {
  try {
    const res = await getList(apiAccount)
    const account = res.find(acc => acc.id == accountId)
    if (account) {
      form.value = {
        email: account.email || '',
        fullname: account.fullname || '',
        gender: account.gender !== null ? account.gender.toString() : '',
        birthday: account.birthday || '',
        avatar: account.avatar || ''
      }
      avatar.value = account.avatar ? `/images/${account.avatar}` : ''
    }
  } catch (error) {
    console.error('Lỗi khi lấy tài khoản:', error)
  }
}

async function handleAvatarUpload(event) {
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
    if (!data.url && !data.filename) throw new Error('Không nhận được URL ảnh từ server')

    const imageUrl = data.url || `/images/${data.filename}`
    avatar.value = imageUrl
    form.value.avatar = data.filename || data.url
  } catch (err) {
    console.error('Lỗi upload avatar:', err)
    avatar.value = ''
    form.value.avatar = ''
  } finally {
    isUploading.value = false
  }
}

const saveProfile = async () => {
  try {
    await updateItem(`${apiAccount}/${accountId}`, form.value)
    alert('Cập nhật thành công!')
  } catch (error) {
    alert('Cập nhật thất bại')
    console.error(error)
  }
}

onMounted(() => {
  getAccount()
})
</script>

<style scoped>
.avatar-wrapper {
  width: 160px;
  height: 160px;
  border-radius: 50%;
  overflow: hidden;
  border: 3px solid var(--green-primary);
  background-color: #f5f5f5;
  position: relative;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
}

.avatar-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 50%;
}

.avatar-empty {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
}
.color-image-icon{
  color: var(--green-primary);
}
.loading-custom{
  background: linear-gradient(135deg, #004643, #00B99B, #f5f5f5);
  border-radius: 50%;
}
</style>
