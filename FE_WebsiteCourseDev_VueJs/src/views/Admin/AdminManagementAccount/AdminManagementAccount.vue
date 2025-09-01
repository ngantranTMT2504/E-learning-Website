<template>
    <div class="">
    <h2 class="fw-bold text-uppercase mb-3">Danh sách người dùng</h2>

    <!-- Tabs -->
    <ul class="nav nav-tabs custom-tabs mb-4">
      <li class="nav-item">
        <a class="nav-link active" data-bs-toggle="tab" href="#teacher">
          <span class="step-number">1</span>
          <span class="step-label">Tài khoản giảng viên</span>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" data-bs-toggle="tab" href="#student">
          <span class="step-number">2</span>
          <span class="step-label">Tài khoản học viên</span>
        </a>
      </li>
    </ul>

    <!-- Step Content -->
    <div class="tab-content">
      <div class="tab-pane container active" id="teacher"><AdminManagementTeacher/></div>
      <div class="tab-pane container fade" id="student"><AdminManagementStudent/></div>
    </div>
  </div>
</template>

<script setup>
import AdminManagementTeacher from './AdminManagementTeacher.vue';
import AdminManagementStudent from './AdminManagementStudent.vue';
</script>

<style scoped>
.custom-tabs .nav-link {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 10px 20px;
  font-weight: 500;
  color: #555;
  border: none;
  border-bottom: 3px solid transparent;
  transition: all 0.3s ease;
  background-color: transparent;
}

.custom-tabs .nav-link .step-number {
  display: inline-block;
  background: #dee2e6;
  color: #000;
  border-radius: 50%;
  width: 28px;
  height: 28px;
  line-height: 28px;
  text-align: center;
  font-size: 14px;
  font-weight: bold;
}

.custom-tabs .nav-link.active {
  color: var(--green-primary);
  border-bottom: 3px solid var(--green-primary);
  background-color: #f8f9fa;
}

.custom-tabs .nav-link.active .step-number {
  background: var(--green-primary);
  color: #fff;
}

.custom-tabs .nav-link.disabled {
  pointer-events: none;
  opacity: 0.5;
}

/* Smooth fade animation */
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.3s ease;
}
.fade-enter-from, .fade-leave-to {
  opacity: 0;
}

</style>