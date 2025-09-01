<template>
  <div class="row">
    <div class="col">
      <div class="block-27">
        <ul v-if="totalPage > 0">
          <!-- Nút Previous -->
          <li>
            <a
              href="#"
              class="me-2"
              @click.prevent="goToPage(currentPage - 1)"
              :class="{ disabled: currentPage === 0 }"
            >&lt;</a>
          </li>

          <!-- Trang số -->
          <li
            v-for="(page, index) in visiblePages"
            :key="index"
            :class="{ active: page === currentPage }"
            class="me-2"
          >
            <span v-if="page === '...'">...</span>
            <a v-else href="#" @click.prevent="goToPage(page)">
              {{ page + 1 }}
            </a>
          </li>

          <!-- Nút Next -->
          <li>
            <a
              href="#"
              @click.prevent="goToPage(currentPage + 1)"
              :class="{ disabled: currentPage === totalPage - 1 }"
            >&gt;</a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  currentPage: {
    type: Number,
    default: 0, // bắt đầu từ 0
  },
  totalPage: {
    type: Number,
    required: true,
    default: 1,
  },
});

const emit = defineEmits(['update:currentPage']);

const goToPage = (page) => {
  if (page >= 0 && page < props.totalPage && page !== props.currentPage) {
    emit('update:currentPage', page);
  }
};

const visiblePages = computed(() => {
  const pages = [];
  const total = props.totalPage;
  const current = props.currentPage;

  if (total <= 5) {
    for (let i = 0; i < total; i++) pages.push(i);
  } else {
    pages.push(0); // Trang đầu tiên
    if (current > 2) pages.push('...');
    const start = Math.max(1, current - 1);
    const end = Math.min(total - 2, current + 1);
    for (let i = start; i <= end; i++) pages.push(i);
    if (current < total - 3) pages.push('...');
    pages.push(total - 1); // Trang cuối
  }
  return pages;
});
</script>

<style scoped>
.block-27 ul {
  padding: 0;
  margin: 0;
}

.block-27 ul li {
  display: inline-block;
  margin-bottom: 4px;
  font-weight: 400;
}

.block-27 ul li a,
.block-27 ul li span {
  color: var(--green-primary);
  text-align: center;
  display: inline-block;
  width: 40px;
  height: 40px;
  line-height: 40px;
  border-radius: 50%;
  border: 1px solid #e6e6e6;
}

.block-27 ul li.active a,
.block-27 ul li.active span,
.block-27 ul li:hover a {
  background: var(--green-primary);
  background: linear-gradient(135deg, var(--green-primary) 0%, var(--green-primary) 100%);
  color: var(--white-text);
  border: 1px solid transparent;
}

.block-27 ul li a.disabled {
  pointer-events: none;
  opacity: 0.5;
}
</style>
