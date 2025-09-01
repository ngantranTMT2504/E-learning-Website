<template>
  <div>
    <h2 class="fw-bold text-uppercase mb-3">Quản lý loại khoá học</h2>
    <div class="row">
      <!-- Form bên phải -->
      <div class="col-xl-5 order-xl-2 mb-5 mb-xl-0">
        <div class="card shadow position-sticky" style="top: 80px;">
          <div class="card-body">
            <h3 class="fw-bold mb-3 text-uppercase text-center">
              {{ isEdit ? 'Cập nhật loại khoá học' : 'Thêm loại khoá học' }}
            </h3>
            <div>
              <div class="mb-3">
                <label class="form-label text-start">Tên loại khoá học</label>
                <input
                  ref="nameInput"
                  type="text"
                  class="form-control"
                  placeholder="Nhập tên loại khoá học"
                  v-model="formData.name"
                  :disabled="isSubmitting"
                />
              </div>

              <CheckboxCustom v-model="formData.status" :disabled="isSubmitting">
                Hoạt động
              </CheckboxCustom>

              <div class="d-flex justify-content-end gap-2 mt-3">
                <ButtonCustom :disabled="isSubmitting" @click="handleSubmit">
                  {{ isEdit ? 'Cập nhật' : 'Lưu' }}
                </ButtonCustom>
                <ButtonCustom
                  variant="secondary"
                  :disabled="isSubmitting"
                  @click="resetForm"
                >
                  Huỷ
                </ButtonCustom>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Bảng danh sách bên trái -->
      <div class="col-xl-7 order-xl-1">
        <TableListCustom
          :data="courseTypes"
          :columns="columns"
          :totalRows="totalItems"
          :pageSize="pageSize"
          :rowsPerPageOptions="[5, 10, 15]"
          :currentPage="currentPage + 1"
          @onRequest="handlePageChange"
        >
          <template #cell-status="{ row }">
            <SwitchCheckbox
              :modelValue="row.status"
              @update:modelValue="val => handleToggleStatus(row, val)"
            />
          </template>

          <template #cell-action="{ row }">
            <ActionMenuCustom
              :uid="row.id"
              :menuItems="[
                {
                  label: 'Sửa',
                  icon: 'bi bi-pencil-square',
                  class: 'text-warning',
                  onClick: () => startEdit(row)
                },
                {
                  label: 'Xoá',
                  icon: 'bi bi-trash',
                  class: 'text-danger',
                  onClick: () => confirmDelete(row.id)
                }
              ]"
            />
          </template>
        </TableListCustom>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue'

// Component custom
import TableListCustom from '@/components/Common/TableListCustom.vue'
import ActionMenuCustom from '@/components/Common/ActionMenuCustom.vue'
import ButtonCustom from '@/components/Common/ButtonCustom.vue'
import SwitchCheckbox from '@/components/Common/SwitchCheckbox.vue'
import CheckboxCustom from '@/components/Common/CheckboxCustom.vue'

// API service
import {
  getList,
  createItem,
  updateItem,
  deleteItem
} from '@/services/apiMiddlewareV2.js'

// Thư viện Toast để hiển thị thông báo (nếu bạn chưa dùng, cài đặt: npm install vue-toastification)

// Hàm hiển thị thông báo lỗi
function showError(msg) {

}

// Hàm hiển thị thông báo thành công
function showSuccess(msg) {
  
}

// State và reactive refs
const endpoint = '/api/course-type'
const isEdit = ref(false)
const isSubmitting = ref(false)
const formData = ref({ id: null, name: '', status: true })

const courseTypes = ref([])
const totalItems = ref(0)
const currentPage = ref(0) // 0-based
const pageSize = ref(5)

// Columns cho bảng
const columns = [
  { label: 'Tên loại khóa học', field: 'name', sortable: true },
  { label: 'Trạng thái', field: 'status', width: '150px', align: 'center' },
  { label: 'Thao tác', field: 'action', width: '100px', align: 'center' }
]

// Ref input để focus
const nameInput = ref(null)

// Hàm lấy danh sách loại khoá học với phân trang
async function fetchCourseTypes(page = currentPage.value, size = pageSize.value) {
  try {
    const res = await getList(endpoint, { page, size })
    // Nếu API trả về cấu trúc khác, bạn chỉnh lại theo đúng
    courseTypes.value = res.content || res.data || []
    totalItems.value = res.totalElements ?? res.totalItems ?? 0
  } catch (err) {
    console.error('Lỗi lấy danh sách:', err)
    showError('Lỗi khi lấy danh sách loại khoá học!')
  }
}

// Xử lý phân trang (page là 1-based do bảng truyền vào)
function handlePageChange({ page, pageSize: size }) {
  currentPage.value = page - 1
  pageSize.value = size
  fetchCourseTypes(currentPage.value, size)
}

// Khi mounted load dữ liệu lần đầu
onMounted(() => {
  fetchCourseTypes()
})

// Reset form về trạng thái thêm mới
function resetForm() {
  formData.value = { id: null, name: '', status: true }
  isEdit.value = false
  isSubmitting.value = false
}

// Bắt đầu chỉnh sửa 1 dòng
function startEdit(row) {
  formData.value = { ...row } // copy dữ liệu để không ảnh hưởng trực tiếp bảng
  isEdit.value = true
  isSubmitting.value = false

  // Scroll lên đầu trang và focus input tên
  window.scrollTo({ top: 0, behavior: 'smooth' })
  nextTick(() => {
    nameInput.value?.focus()
  })
}

// Xử lý submit form thêm mới hoặc cập nhật
async function handleSubmit() {
  if (isSubmitting.value) return
  if (!formData.value.name.trim()) {
    showError('Vui lòng nhập tên loại khoá học!')
    return
  }
  isSubmitting.value = true

  try {
    if (isEdit.value) {
      // Cập nhật
      await updateItem(`${endpoint}/${formData.value.id}`, {
        name: formData.value.name,
        status: formData.value.status
      })
      showSuccess('Cập nhật thành công!')
    } else {
      // Thêm mới
      await createItem(endpoint, {
        name: formData.value.name,
        status: formData.value.status
      })
      showSuccess('Thêm mới thành công!')
      currentPage.value = 0 // về trang đầu sau khi thêm
    }
    await fetchCourseTypes(currentPage.value, pageSize.value)
    resetForm()
  } catch (err) {
    console.error('Lỗi xử lý dữ liệu:', err)
    showError('Đã có lỗi xảy ra khi lưu dữ liệu!')
  } finally {
    isSubmitting.value = false
  }
}

// Xử lý toggle trạng thái
async function handleToggleStatus(row, newStatus) {
  try {
    await updateItem(`${endpoint}/${row.id}`, {name:row.name, status: newStatus })
    const index = courseTypes.value.findIndex(item => item.id === row.id)
    if (index !== -1) courseTypes.value[index].status = newStatus
    showSuccess('Cập nhật trạng thái thành công!')
  } catch (error) {
    console.error('Lỗi toggle trạng thái:', error)
    showError('Không thể cập nhật trạng thái!')
    // Khôi phục lại giá trị cũ nếu lỗi
    row.status = !newStatus
  }
}

// Xác nhận và xoá loại khoá học
async function confirmDelete(id) {
  // Bạn có thể bổ sung modal confirm trước khi xoá nếu muốn
  if (!confirm('Bạn có chắc chắn muốn xoá loại khoá học này?')) return

  try {
    await deleteItem(`${endpoint}/${id}`)
    showSuccess('Xoá thành công!')
    // Nếu xoá xong mà không còn item nào ở trang hiện tại, về trang trước nếu có
    if (courseTypes.value.length === 1 && currentPage.value > 0) {
      currentPage.value -= 1
    }
    await fetchCourseTypes(currentPage.value, pageSize.value)
  } catch (err) {
    console.error('Lỗi xoá:', err)
    showError('Không thể xoá loại khoá học!')
  }
}
</script>

<style scoped>
/* Bạn có thể thêm style riêng nếu muốn */
</style>
