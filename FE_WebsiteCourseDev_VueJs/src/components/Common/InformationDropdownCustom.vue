<template>
  <div class="dropdown">
    <button class="btn d-flex align-items-center dropdown-toggle border-0 bg-transparent" type="button"
      :id="'userDropdown-' + userName" data-bs-toggle="dropdown" aria-expanded="false">
      <img :src="validAvatar" alt="Avatar" class="rounded-circle object-fit-cover me-2" width="32" height="32" />
      <span class="fw-semibold d-none d-md-inline">{{ userName }}</span>
    </button>

    <ul class="dropdown-menu dropdown-menu-end shadow-sm" :aria-labelledby="'userDropdown-' + userName">
      <template v-for="(item, index) in menuItems" :key="index">
        <li v-if="item.divider">
          <hr class="dropdown-divider" />
        </li>
        <li v-else>
          <RouterLink
            v-if="item.action?.type === 'link'"
            :to="item.action.to"
            class="dropdown-item d-flex align-items-center"
            :class="item.class"
          >
            <i :class="item.icon + ' me-2'"></i> {{ item.label }}
          </RouterLink>

          <button
            v-else-if="item.action?.type === 'function'"
            @click="item.action.handler"
            class="dropdown-item d-flex align-items-center"
            :class="item.class"
          >
            <i :class="item.icon + ' me-2'"></i> {{ item.label }}
          </button>

          <button
            v-else-if="item.action?.type === 'combined'"
            @click="handleCombined(item.action)"
            class="dropdown-item d-flex align-items-center"
            :class="item.class"
          >
            <i :class="item.icon + ' me-2'"></i> {{ item.label }}
          </button>
        </li>
      </template>
    </ul>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'

const props = defineProps({
  userName: String,
  avatarUrl: String,
  menuItems: Array
})

const validAvatar = computed(() => {
  return props.avatarUrl?.trim()
    ? props.avatarUrl
    : `https://ui-avatars.com/api/?name=${encodeURIComponent(props.userName)}&background=004643&color=fff`
})

const router = useRouter()
function handleCombined(action) {
  if (action.handler) action.handler()
  if (action.to) router.push(action.to)
}
</script>

<style scoped>
img {
  aspect-ratio: 1;
  object-fit: cover;
}

.dropdown-item:hover {
  background-color: #f8f9fa;
}

.dropdown-item:focus {
  outline: none;
  box-shadow: none;
}

.dropdown-item:active {
  background-color: #f8f9fa;
  color: inherit;
}

/* Avatar */
img.rounded-circle {
  border: 2px solid #dee2e6;
  transition: transform 0.2s ease;
}
img.rounded-circle:hover {
  transform: scale(1.05);
}

/* Nút dropdown */
.dropdown-toggle {
  padding: 0.4rem 0.6rem;
  border-radius: 8px;
  transition: background-color 0.2s ease;
}
.dropdown-toggle:hover {
  background-color: #f1f3f5;
}

/* Menu dropdown */
.dropdown-menu {
  border-radius: 10px;
  padding: 0.5rem;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
  min-width: 220px;
  background-color: #ffffff;
  z-index: 1000;
  animation: fadeSlideIn 0.25s ease forwards;
}

/* Animation khi mở dropdown */
@keyframes fadeSlideIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* Item trong menu */
.dropdown-item {
  border-radius: 6px;
  padding: 0.5rem 0.75rem;
  transition: background-color 0.2s ease, color 0.2s ease;
  font-size: 1rem;
  font-weight: normal; 
  display: flex;
  align-items: center;
}

.dropdown-item:hover {
  background-color: var(--light-gray);
}

.dropdown-divider {
  margin: 0.5rem 0;
  border-color: #dee2e6;
}

/* Responsive text */
@media (max-width: 768px) {
  .dropdown-toggle span {
    display: none;
  }
}

</style>
