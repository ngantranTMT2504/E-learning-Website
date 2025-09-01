<template>
  <div class="dropdown text-center">
    <button
      class="btn btn-sm border bg-white px-2 py-1 dropdown-toggle"
      type="button"
      :id="'actionMenu-' + uid"
      data-bs-toggle="dropdown"
      aria-expanded="false"
    >
      <i class="bi bi-three-dots-vertical"></i>
    </button>

    <ul
      class="dropdown-menu dropdown-menu-end shadow-sm"
      :aria-labelledby="'actionMenu-' + uid"
    >
      <template v-for="(item, index) in menuItems" :key="index">
        <li v-if="item.divider">
          <hr class="dropdown-divider custom-divider" />
        </li>
        <li v-else>
          <RouterLink
            v-if="item.to"
            :to="item.to"
            class="dropdown-item d-flex align-items-center"
            :class="item.class"
          >
            <i :class="item.icon + ' me-2'"></i> {{ item.label }}
          </RouterLink>
          <button
            v-else
            class="dropdown-item d-flex align-items-center"
            :class="item.class"
            @click="item.onClick"
          >
            <i :class="item.icon + ' me-2'"></i> {{ item.label }}
          </button>
        </li>
      </template>
    </ul>
  </div>
</template>

<script setup>

defineProps({
  uid: {
    type: [String, Number],
    default: ''
  },
  menuItems: {
    type: Array,
    default: () => []
  }
})
</script>

<style scoped>
.dropdown-toggle::after {
  display: none;
}

.custom-divider {
  border-top: 1px solid #212529;
  opacity: 1;
  margin: 0.4rem 0;
}

/* Nút dropdown */
.dropdown-toggle {
  position: relative;
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
  width: max-content;
  min-width: 150px;
  max-width: auto;
  white-space: nowrap;
  background-color: #ffffff;
  z-index: 1000;
  animation: fadeSlideIn 0.25s ease forwards;
  margin-top: 0.4rem;
}

/* Animation khi mở dropdown */
@keyframes fadeSlideIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(10);
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
