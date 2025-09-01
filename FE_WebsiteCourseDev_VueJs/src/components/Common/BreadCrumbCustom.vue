<template>
  <nav aria-label="breadcrumb">
    <ol class="breadcrumbs">
      <li v-for="(item, index) in items" :key="index" class="breadcrumb-item">
        <RouterLink v-if="item.to" :to="item.to">
          {{ item.label }}
        </RouterLink>
        <span v-else>{{ item.label }}</span>

         <span
          v-if="index < items.length - 1"
          class="breadcrumb-separator">
          <i class="bi bi-chevron-right breadcrumb-separator"></i>
        </span>
      </li>
    </ol>
  </nav>
</template>

<script setup>
import { RouterLink } from 'vue-router';

const props = defineProps({
  items: {
    type: Array,
    required: true,
    validator: (value) =>
      value.every(item => typeof item.label === 'string' && ('to' in item || !item.to))
  }
});
</script>

<style scoped>
.breadcrumbs {
  display: flex;
  flex-wrap: wrap;
  padding: 0;
  margin: 0;
  list-style: none;
}

.breadcrumb-item + .breadcrumb-item::before {
  content: "";
  color: #6c757d;
}

.breadcrumb-item {
  display: flex;
  align-items: center;
  margin-right: 0px;
}

.breadcrumb-item a {
  color: var(--green-primary);
  font-weight: 500;
  text-decoration: none;
}

.breadcrumb-separator {
  margin: 0 2px;
  color: #6c757d; 
  font-weight: bold;
}
</style>

