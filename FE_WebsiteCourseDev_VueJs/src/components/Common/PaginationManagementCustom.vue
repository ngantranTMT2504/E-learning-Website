<template>
  <nav>
    <ul class="pagination mb-0">
      <li class="page-item rounded-start-circle" :class="{ disabled: currentPage === 1 }">
        <button class="page-link rounded-start-circle" @click="update(currentPage - 1)"><i class="bi bi-chevron-left"></i></button>
      </li>

      <li class="page-item" :class="{ active: currentPage === 1 }">
        <button class="page-link" @click="update(1)">1</button>
      </li>

      <li v-if="showFirstEllipsis" class="page-item disabled">
        <span class="page-link">...</span>
      </li>

      <li
        v-for="pg in visiblePages"
        :key="pg"
        :class="{ active: pg === currentPage }"
        class="page-item"
      >
        <button class="page-link" @click="update(pg)">{{ pg }}</button>
      </li>

      <li v-if="showLastEllipsis" class="page-item disabled">
        <span class="page-link">...</span>
      </li>

      <li
        v-if="totalPages > 1"
        class="page-item"
        :class="{ active: currentPage === totalPages }"
      >
        <button class="page-link" @click="update(totalPages)">
          {{ totalPages }}
        </button>
      </li>

      <li class="page-item rounded-end-circle" :class="{ disabled: currentPage === totalPages }">
        <button class="page-link rounded-end-circle" @click="update(currentPage + 1)"><i class="bi bi-chevron-right"></i></button>
      </li>
    </ul>
  </nav>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  currentPage: Number,
  totalPages: Number
})

const emit = defineEmits(['update:page'])

const maxVisible = 3

const visiblePages = computed(() => {
  const pages = []
  const start = Math.max(2, props.currentPage - Math.floor(maxVisible / 2))
  const end = Math.min(props.totalPages - 1, start + maxVisible - 1)
  for (let i = start; i <= end; i++) pages.push(i)
  return pages
})

const showFirstEllipsis = computed(() =>
  visiblePages.value[0] > 2
)

const showLastEllipsis = computed(() =>
  visiblePages.value.at(-1) < props.totalPages - 1
)

function update(pg) {
  if (pg >= 1 && pg <= props.totalPages) {
    emit('update:page', pg)
  }
}
</script>
<style scoped>
.page-link {
  border-radius: 50%;
  width: 40px;
  height: 40px;
  padding: 0;
  margin: 0 4px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #dee2e6;
  background-color: #fff;
  transition: all 0.2s ease;
  color: var(--green-primary);
}

.page-link:hover{
  color: var(--green-primary);
}

.page-link:focus {
  box-shadow: none;
  outline: none;
  color: var(--green-primary);
}

.page-item.active .page-link {
  background-color: var(--green-primary);
  color: white;
  border-color: var(--green-primary);
}

.page-item.disabled .page-link {
  opacity: 0.5;
  pointer-events: none;
}

.page-link:focus{
  background-color: #fff;
}

</style>