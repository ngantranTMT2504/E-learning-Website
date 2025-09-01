<template>
  <div class="">
    <h2 class="text-uppercase fw-bold mb-4">Cài đặt hệ thống</h2>

    <div class="row g-4">
      <!-- Ngôn ngữ -->
      <div class="col-md-6">
        <div class="card p-3">
          <label class="form-label fw-semibold">Ngôn ngữ</label>
          <select class="form-select" v-model="settings.language">
            <option value="vi">Tiếng Việt</option>
            <option value="en">English</option>
            <option value="jp">日本語</option>
          </select>
        </div>
      </div>

      <!-- Giao diện sáng/tối -->
      <div class="col-md-6">
        <div class="card p-3">
          <label class="form-label fw-semibold">Chế độ hiển thị</label>
          <div class="form-check form-switch">
            <input class="form-check-input" type="checkbox" v-model="settings.darkMode" id="darkModeSwitch">
            <label class="form-check-label" for="darkModeSwitch">
              {{ settings.darkMode ? 'Chế độ Tối' : 'Chế độ Sáng' }}
            </label>
          </div>
        </div>
      </div>

      <!-- Màu chủ đạo -->
      <div class="col-md-6">
        <div class="card p-3">
          <label class="form-label fw-semibold">Màu chủ đạo</label>
          <input type="color" class="form-control form-control-color" v-model="settings.primaryColor" />
        </div>
      </div>

      <!-- Cỡ chữ -->
      <div class="col-md-6">
        <div class="card p-3">
          <label class="form-label fw-semibold">Cỡ chữ</label>
          <select class="form-select" v-model="settings.fontSize">
            <option value="sm">Nhỏ</option>
            <option value="md">Trung bình</option>
            <option value="lg">Lớn</option>
          </select>
        </div>
      </div>

      <!-- Định dạng ngày giờ -->
      <div class="col-md-6">
        <div class="card p-3">
          <label class="form-label fw-semibold">Định dạng ngày giờ</label>
          <select class="form-select" v-model="settings.dateFormat">
            <option value="DD/MM/YYYY">DD/MM/YYYY</option>
            <option value="YYYY-MM-DD">YYYY-MM-DD</option>
            <option value="MM-DD-YYYY">MM-DD-YYYY</option>
          </select>
        </div>
      </div>

      <!-- Bật/Tắt âm thanh -->
      <div class="col-md-6">
        <div class="card p-3">
          <label class="form-label fw-semibold">Âm thanh thông báo</label>
          <div class="form-check form-switch">
            <input class="form-check-input" type="checkbox" v-model="settings.sound" id="soundSwitch">
            <label class="form-check-label" for="soundSwitch">
              {{ settings.sound ? 'Bật' : 'Tắt' }}
            </label>
          </div>
        </div>
      </div>

      <!-- Hiển thị hướng dẫn -->
      <div class="col-md-6">
        <div class="card p-3">
          <label class="form-label fw-semibold">Hiển thị hướng dẫn sử dụng</label>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" v-model="settings.showOnboarding" id="onboardingCheck">
            <label class="form-check-label" for="onboardingCheck">
              Hiện khi đăng nhập lần đầu
            </label>
          </div>
        </div>
      </div>

      <!-- Nút hành động -->
      <div class="col-12">
        <div class="d-flex justify-content-end gap-2 mt-3">
          <button class="btn btn-outline-secondary fw-bold" @click="resetToDefault">Khôi phục mặc định</button>
          <ButtonCustom @click="saveSettings">Lưu thay đổi</ButtonCustom>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'

const settings = reactive({
  language: 'vi',
  darkMode: false,
  primaryColor: '#0d6efd',
  fontSize: 'md',
  dateFormat: 'DD/MM/YYYY',
  sound: true,
  showOnboarding: true,
})

const saveSettings = () => {
  console.log('Đã lưu:', JSON.stringify(settings))
  alert('Cài đặt đã được lưu thành công!')
}

const resetToDefault = () => {
  settings.language = 'vi'
  settings.darkMode = false
  settings.primaryColor = '#0d6efd'
  settings.fontSize = 'md'
  settings.dateFormat = 'DD/MM/YYYY'
  settings.sound = true
  settings.showOnboarding = true
}
</script>

<style scoped>

</style>
