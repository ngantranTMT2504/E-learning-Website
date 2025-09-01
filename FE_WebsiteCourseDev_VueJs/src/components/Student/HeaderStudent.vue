<template>
  <nav class="py-3 px-4 shadow position-fixed top-0 start-0 end-0 z-3 bg-white">
    <div class="d-flex align-items-center justify-content-between">
      <RouterLink class="fs-4" to="/">
        <img :src="logo" alt="logo" class="logo-img">
      </RouterLink>
      <button class="d-md-none border-0 bg-transparent btn-menu" @click="toggleMenu">
        <i class="bi bi-list"></i>
      </button>

      <ul class="d-none d-md-flex gap-4 mb-0 ps-0 align-items-center">

        <li>
          <RouterLink class="nav-link" to="/">Trang chủ</RouterLink>
        </li>
        <li>
          <RouterLink class="nav-link" to="#">Về chúng tôi</RouterLink>
        </li>
        <li>
          <RouterLink class="nav-link" to="/course">Khóa học</RouterLink>
        </li>
        <li>
          <RouterLink class="nav-link" to="#">Tin Tức</RouterLink>
        </li>
        <li>
          <RouterLink class="nav-link" to="/contact">Liên hệ</RouterLink>
        </li>
      </ul>

      <div v-if="authStore.isLoggedIn == false" class="d-none d-md-flex justify-content-end">
          <ButtonPrimary variant="white" @click="goRegister('teacher')" class="me-4">Giảng dạy</ButtonPrimary>

          <ButtonPrimary class="me-2" @click="goRegister('student')">Đăng ký</ButtonPrimary>

        <RouterLink to="/auth/login">
          <ButtonPrimary>Đăng nhập</ButtonPrimary>
        </RouterLink>
      </div>

      <div v-if="authStore.isLoggedIn">
        <InformationDropdownCustom 
                        :userName="authStore.userName"
                        :avatarUrl="defaultAvt"
                        :menuItems="menuItems" />
      </div>
    </div>

    <div v-if="showMenu" class="mobile-menu mt-2 d-md-none">
      <ul class="list-unstyled mb-2">
        <li>
          <RouterLink class="nav-link" to="/">Trang chủ</RouterLink>
        </li>
        <li>
          <RouterLink class="nav-link" to="#">Về chúng tôi</RouterLink>
        </li>
        <li>
          <RouterLink class="nav-link" to="/course">Khóa học</RouterLink>
        </li>
        <li>
          <RouterLink class="nav-link" to="#">Tin Tức</RouterLink>
        </li>
        <li>
          <RouterLink class="nav-link" to="#">Liên hệ</RouterLink>
        </li>
      </ul>
      <div  v-if="authStore.isLoggedIn == false" class="d-flex flex-column gap-2">
        <ButtonPrimary variant="white" class="me-4">Giảng dạy</ButtonPrimary>
        <ButtonPrimary >Đăng ký</ButtonPrimary>
        <ButtonPrimary >Đăng nhập</ButtonPrimary>
      </div>
      <div v-if="authStore.isLoggedIn">
        <InformationDropdownCustom 
                        :userName="authStore.userName"
                        :avatarUrl="defaultAvt"
                        :menuItems="menuItems" />
      </div>

    </div>
  </nav>
</template>

<script setup>
import { ref } from 'vue'
import { RouterLink, useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/authStore'
import { useRegisterRole } from '@/stores/registerRole'

import InformationDropdownCustom from '../Common/InformationDropdownCustom.vue';
import ButtonPrimary from '@/components/Common/ButtonCustom.vue'
import logo from '@/assets/logoIT.png'
import defaultAvt from '@/assets/images/default-avatar.jpg'

const showMenu = ref(false)
const authStore = useAuthStore()
const registerRole = useRegisterRole()
const router = useRouter();

const goRegister = (role) => {
  registerRole.setRole(role)
  router.push(`/auth/register`)
}

const toggleMenu = () => {
  showMenu.value = !showMenu.value
};

const handleLogout = () => {
  authStore.logout()
  router.push('/auth/login')
}

const menuItems = [
  {
    label: 'Giao diện sáng',
    icon: 'bi bi-brightness-high-fill text-warning',
    class: '',
    action: {
      type: 'function',
      handler: () => switchTheme('light')
    }
  },
  {
    label: 'Thông tin cá nhân',
    icon: 'bi bi-person-lines-fill text-success',
    class: '',
    action: {
      type: 'combined',
      to: '/profile',
      handler: () => console.log('Đã vào hồ sơ + gọi hàm tuỳ ý')
    }
  },
  { divider: true },
  {
    label: 'Đăng xuất',
    icon: 'bi bi-box-arrow-right',
    class: 'text-danger fw-semibold',
    action: {
      type: 'function',
      handler: () => handleLogout()
    }
  }
];

</script>

<style scoped>
.nav-link {
  font-size: 16px;
  color: var(--green-primary);
  font-weight: 700;
}

.nav-link:hover {
  color: var(--bare-orange);
}

.shadow {
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}

.btn-menu {
  color: var(--green-text);
  font-size: 30px;
  font-weight: 600;
}

.logo-img {
  height: 44px;
}
</style>
