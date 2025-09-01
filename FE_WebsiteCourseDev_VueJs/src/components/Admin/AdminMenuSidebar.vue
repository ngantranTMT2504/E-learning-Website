<template>
  <ul class="nav flex-column px-2 mt-2">
    <li v-for="menu in menuConfig" :key="menu.to" class="nav-item mb-2 fs-menu">
      <!-- Không có submenu -->
      <RouterLink v-if="!menu.children" :to="menu.to" class="nav-link" :class="{ active: route.path === menu.to }">
        <i :class="['bi', menu.icon, 'me-2']"></i>
        {{ menu.label }}
      </RouterLink>

      <!-- Có submenu -->
      <template v-else>
        <a class="nav-link d-flex justify-content-between align-items-center" href="#"
          @click.prevent="toggleMenu(menu.label)" :class="{ active: route.path.startsWith(menu.to) }" role="button"
          :aria-expanded="openMenus[menu.label]" :aria-controls="getCollapseId(menu)">
          <div>
            <i :class="['bi', menu.icon, 'me-2']"></i>
            {{ menu.label }}
          </div>
          <i v-if="menu.children.length > 1" class="bi bi-chevron-down transition-icon"
            :class="{ 'rotate-chevron': openMenus[menu.label] }"></i>
        </a>
        <Transition name="slide">
          <div v-show="openMenus[menu.label]" class="menu-slide" :id="getCollapseId(menu)">
            <ul class="nav flex-column ms-4 mt-2">
              <li v-for="child in menu.children" :key="child.to" class="nav-item mb-1">
                <RouterLink :to="child.to" class="nav-link small" :class="{ active: route.path === child.to }">
                  {{ child.label }}
                </RouterLink>
              </li>
            </ul>
          </div>
        </Transition>

      </template>
    </li>
  </ul>
</template>

<script setup>
import { ref, watch } from "vue";
import { useRoute, RouterLink } from "vue-router";

const route = useRoute();

const menuConfig = [
  {
    label: "Trang chủ",
    icon: "bi-house-fill",
    to: "/",
  },
  {
    label: "Tổng quan",
    icon: "bi-speedometer2",
    to: "/admin/dashboard",
  },
  {
    label: "Quản lý tài khoản",
    icon: "bi-people-fill",
    to: "/admin/account-management",
  },
  {
    label: "Quản lý loại khóa học",
    icon: "bi-journal-bookmark-fill",
    to: "/admin/type-course/list",
  },
    {
    label: "Quản lý chiết khấu",
    icon: "bi-tag-fill",
    to: "/admin/commission/list",
  },
   {
    label: "Quản lý Chứng chỉ GV",
    icon: "bi-award-fill",
    to: "/admin/teacher-certificate/list",
  },
  {
    label: "Thống kê",
    icon: "bi-bar-chart-fill",
    prefix: "/admin/statistical",
    children: [
      { label: "Doanh Thu Tháng", to: "/admin/statistical/monthly-revennue-statistical" },
      { label: "Khoá Học", to: "/admin/statistical/course" },
    ],
  },
  {
    label: "Thông tin cá nhân",
    icon: "bi-person-fill",
    to: "/admin/info",
  },
  {
    label: "Cài đặt",
    icon: "bi-gear-fill",
    to: "/admin/gear",
  },
];

const openMenus = ref({});

watch(
  () => route.path,
  (path) => {
    menuConfig.forEach((menu) => {
      if (menu.children) {
        // Dùng prefix nếu có, fallback sang menu.to
        const basePath = menu.prefix || menu.to;
        // openMenus.value[menu.label] = path.startsWith(basePath)
        if (path.startsWith(basePath)) {
          openMenus.value[menu.label] = true;
        }
      }
    });
  },
  { immediate: true }
);

const toggleMenu = (label) => {
  openMenus.value[label] = !openMenus.value[label];
};

const getCollapseId = (menu) => {
  return menu.label.toLowerCase().replace(/\s+/g, "-") + "-submenu";
};
</script>

<style scoped>
.nav-link {
  color: #333;
  border-radius: 0.375rem;
  /* transition: background 0.2s ease; */
}

.nav-link:hover {
  background-color: #f1f1f1;
}

.nav-link.active {
  background-color: var(--green-primary);
  color: white !important;
}

.transition-icon {
  transition: transform 0.5s cubic-bezier(0.25, 0.8, 0.25, 1);
  transform-origin: center;
  display: inline-block;
}

.rotate-chevron {
  transform: rotate(180deg);
}

@media (min-width: 768px) {
  .fs-menu {
    font-size: 1rem;
  }
}

.menu-slide {
  overflow: hidden;
}

.slide-enter-active,
.slide-leave-active {
  transition: max-height 0.4s ease, opacity 0.3s ease;
}

.slide-enter-from,
.slide-leave-to {
  max-height: 0;
  opacity: 0;
}

.slide-enter-to,
.slide-leave-from {
  max-height: 500px; 
  opacity: 1;
}

a:hover,a:focus{
  color: var(--green-primary);
}

</style>
