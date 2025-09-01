<template>
  <div class="card shadow-sm">
    <!-- Tìm kiếm -->
    <div v-if="enableSearch" class="card-header bg-light d-flex justify-content-between align-items-center">
      <h5 class="mb-0"></h5>
      <input v-model="searchTerm" class="form-control w-50" placeholder="Tìm kiếm..." />
    </div>

    <!-- Bảng -->
    <div class="card-body table-responsive">
      <table class="table table-hover align-middle">
        <thead class="table-secondary">
          <tr>
            <th v-for="col in columns" :key="col.field"
              @click="col.sortable && toggleSort(col.field)"
              :class="[
                col.align === 'center' ? 'text-center' : '',
                'align-middle'
              ]"
              :style="{ width: col.width ?? 'auto', cursor: col.sortable ? 'pointer' : 'default' }"
            >
              <span class="d-flex justify-content-between align-items-center w-100">
                <span class="fw-bold">{{ col.label }}</span>
                <template v-if="col.sortable">
                  <span class="sort-icons d-flex flex-column align-items-center py-2">
                    <i :class="['bi bi-caret-up-fill', getSortClass(col.field, 'asc')]"></i>
                    <i :class="['bi bi-caret-down-fill', getSortClass(col.field, 'desc')]"></i>
                  </span>
                </template>
              </span>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(row, i) in data" :key="i">
            <td v-for="col in columns" :key="col.field"
              :class="col.align === 'center' ? 'mx-auto' : ''"
              :style="{ width: col.width ?? 'auto' }"
              
            >
              <slot :name="`cell-${col.field}`" :row="row">
                {{ row[col.field] }}
              </slot>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Footer -->
    <div class="card-footer d-flex justify-content-between flex-wrap gap-3 align-items-center">
      <div class="d-flex align-items-center gap-2">
        <select v-model.number="pageSize" class="form-select form-select-sm w-auto">
          <option v-for="opt in pageOptions" :key="opt" :value="opt">{{ opt }}</option>
        </select>
        <span class="text-muted">hàng/trang</span>
      </div>

      <PaginationManagementCustom :currentPage="page" :totalPages="totalPages" @update:page="page = $event" />
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue'
import PaginationManagementCustom from './PaginationManagementCustom.vue'

const props = defineProps({
  data: Array,
  columns: Array,
  totalRows: Number,
  pageSize: Number,
  rowsPerPageOptions: Array,
  enableSearch: { type: Boolean, default: true }
})

const emit = defineEmits(['onRequest'])

const page = ref(1)
const pageSize = ref(props.pageSize ?? 10)
const searchTerm = ref('')
const sortFields = ref([])
const columns = Array.isArray(props.columns) ? props.columns : []
const pageOptions = props.rowsPerPageOptions ?? [5, 10, 20, 50]

const totalPages = computed(() =>
  Math.max(1, Math.ceil((props.totalRows ?? 0) / pageSize.value))
)

function toggleSort(field) {
  const index = sortFields.value.findIndex(s => s.field === field)
  let updatedSorts = [...sortFields.value]

  if (index >= 0) {
    const current = updatedSorts[index]
    current.order = current.order === 'asc' ? 'desc' : 'asc'
    // Di chuyển field mới được click lên đầu
    updatedSorts.splice(index, 1)
    updatedSorts.unshift(current)
  } else {
    updatedSorts.unshift({ field, order: 'asc' })
  }

  sortFields.value = updatedSorts
}


function getSortClass(field, direction) {
  const s = sortFields.value.find(s => s.field === field)
  if (!s) return 'text-muted'
  return s.order === direction ? 'text-dark fw-bold' : 'text-muted'
}

// Gửi event khi thay đổi
watch([page, pageSize, searchTerm, sortFields], () => {
  emit('onRequest', {
    page: page.value,
    pageSize: pageSize.value,
    search: searchTerm.value,
    sort: sortFields.value
  })
}, { immediate: true })
</script>

<style scoped>
.sort-icons i {
  font-size: 1rem;
  margin-top: -6px;
  margin-bottom: -6px;
}
.sort-icons .text-dark {
  color: black !important;
}
.sort-icons .text-muted {
  color: silver !important;
}
.form-control:focus {
  box-shadow: none;
  border: 1px solid var(--green-primary);
}
</style>
