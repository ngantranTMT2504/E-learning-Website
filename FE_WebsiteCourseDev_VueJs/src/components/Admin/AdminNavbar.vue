<template>
    <div>
        <nav class="navbar navbar-light bg-light py-3 border-bottom shadow-sm pe-lg-4">
            <div class="container-fluid d-flex justify-content-between align-items-center">
                <!-- Nút menu mobile -->
                <div>
                    <button class="btn d-md-none py-0 p-2" type="button" data-bs-toggle="offcanvas"
                        data-bs-target="#mobileAdminSidebar">
                        <i class="bi bi-list fs-2"></i>
                    </button>
                </div>

                <div class="d-flex align-items-center">
                    <form class="d-flex">
                        <input class="form-control me-2" type="search" placeholder="Search" />
                    </form>
                    <div class="position-relative me-3">
                        <i class="bi bi-bell fs-4"></i>
                        <span v-if="notificationCount > 0"
                            class="position-absolute top-0 start-100 translate-middle badge rounded-circle bg-danger p-1 notification">
                            {{ notificationCount }}
                        </span>
                    </div>
                    <i class="bi bi-house-door fs-4"></i>
                    <InformationDropdownCustom 
                        :userName="user.name" 
                        :avatarUrl="user.avatar"
                        :menuItems="menuItems" />
                </div>
            </div>
        </nav>
    </div>
</template>


<script setup>
import InformationDropdownCustom from '../Common/InformationDropdownCustom.vue';
import { ref } from 'vue'
import avatar1 from '@/assets/avatar1.jpg' 
import { useAuthStore } from '@/stores/authStore'
import { useRouter } from 'vue-router'

const user = {
    name: 'Mộc Toàn',
    avatar: avatar1,
    role: 'admin'
}

const authStore = useAuthStore()
const router = useRouter();

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
    label: 'Trang chủ',
    icon: 'bi bi-house-door-fill',
    class: '',
    action: {
      type: 'link',
      to: '/'
    }
  },
  {
    label: 'Mở popup trợ giúp',
    icon: 'bi bi-question-circle-fill text-info',
    class: '',
    action: {
      type: 'function',
      handler: () => showHelpModal()
    }
  },
  {
    label: 'Mở hồ sơ & gọi xử lý',
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
]


const notificationCount = ref(23) 
</script>

<style scoped>
.notification {
    top: 0;
    right: 0;
    transform: translate(50%, -50%);
    width: 1.2rem;
    height: 1.2rem;
    font-size: 0.6rem;
    display: flex;
    align-items: center;
    justify-content: center;
}

.form-control:focus{
  box-shadow: none;
  border: 1px solid var(--green-primary);
}
</style>