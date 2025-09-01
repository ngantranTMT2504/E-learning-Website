<template>
  <div class="container">
    <h2 class="fw-bold text-uppercase mb-3">Thông tin cá nhân</h2>
    <div class="row">
      <!-- Avatar & Profile Info (Sticky) -->
      <div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
        <div class="card shadow position-sticky" style="top: 80px;">
          <div class="card-body text-center">
            <label class="avatar-wrapper mx-auto mb-3" for="avatar-input">
              <template v-if="avatar">
                <img :src="avatar" class="avatar-image" alt="Ảnh đại diện" />
              </template>
              <template v-else>
                <div class="avatar-placeholder">Chưa có ảnh</div>
              </template>
              <input type="file" id="avatar-input" class="d-none" @change="onAvatarChange" />
            </label>
            <h4 class="mt-3">{{ user.fullName }}</h4>
            <div class="text-muted mb-1">
              <i class="bi bi-geo-alt-fill me-1"></i>{{ user.location }}
            </div>
            <div class="text-muted mb-1">
              <i class="bi bi-briefcase me-1"></i>{{ user.jobTitle }}
            </div>
            <div class="text-muted">
              <i class="bi bi-mortarboard me-1"></i>{{ user.university }}
            </div>
          </div>
        </div>
      </div>

      <!-- Account Form -->
      <div class="col-xl-8 order-xl-1">
        <div class="card shadow bg-light">
          <div class="card-body">
            <form @submit.prevent="">
              <h6 class="text-muted text-uppercase mb-3">Thông tin cá nhân</h6>
              <div class="mb-3">
                <label class="form-label">Email</label>
                <input v-model="form.email" type="email" class="form-control" placeholder="Email" disabled/>
              </div>
              <div class="mb-3">
                <label class="form-label">Họ tên</label>
                <input v-model="form.fullname" type="text" class="form-control" placeholder="Họ tên" />
              </div>
              <div class="row">
                <div class="col-md-6 mb-3">
                  <label class="form-label">Giới tính</label>
                  <select v-model="form.gender" class="form-select">
                    <option value="">-- Chọn giới tính --</option>
                    <option value="Nam">Nam</option>
                    <option value="Nữ">Nữ</option>
                    <option value="Khác">Khác</option>
                  </select>
                </div>
                <div class="col-md-6 mb-3">
                  <label class="form-label">Ngày sinh</label>
                  <input v-model="form.birthday" type="date" class="form-control" />
                </div>
              </div>

              <hr class="my-4" />
              <h6 class="text-muted text-uppercase mb-3">Thông tin liên hệ</h6>
              <div class="mb-3">
                <label class="form-label">Địa chỉ</label>
                <input v-model="form.address" type="text" class="form-control" placeholder="Địa chỉ" />
              </div>
              <div class="row">
                <div class="col-md-6 mb-3">
                  <label class="form-label">Thành phố</label>
                  <input v-model="form.city" type="text" class="form-control" placeholder="Thành phố" />
                </div>
                <div class="col-md-6 mb-3">
                  <label class="form-label">Quốc gia</label>
                  <input v-model="form.country" type="text" class="form-control" placeholder="Quốc gia" />
                </div>
              </div>

              <div class="d-flex justify-content-end">
                <ButtonCustom>Lưu</ButtonCustom>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'
const avatar = ref('')

const user = ref({
  fullName: 'ThS. Nguyễn Văn A',
  location: 'TP. Hồ Chí Minh, Việt Nam',
  jobTitle: 'Giảng viên CNTT',
  university: 'Đại học Khoa học Tự nhiên'
})

const form = ref({
  email: 'nguyenvana@gmail.com',
  fullname: 'Nguyễn Văn A',
  gender: '',
  birthday: '',
  address: '',
  city: '',
  country: ''
})

function onAvatarChange(event) {
  const file = event.target.files[0]
  if (file) {
    const reader = new FileReader()
    reader.onload = () => {
      avatar.value = reader.result
    }
    reader.readAsDataURL(file)
  }
}
</script>

<style scoped>
.avatar-wrapper {
  width: 160px;
  height: 160px;
  border-radius: 50%;
  overflow: hidden;
  border: 3px solid #ccc;
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
}

.avatar-placeholder {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #888;
  font-size: 1rem;
  background-color: #e9ecef;
  text-align: center;
}
</style>
