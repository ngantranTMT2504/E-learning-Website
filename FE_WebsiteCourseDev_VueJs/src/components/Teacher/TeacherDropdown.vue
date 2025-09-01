<template>
  <div class="dropdown">
    <!-- Button hiển thị avatar + tên -->
    <button
      class="btn d-flex align-items-center dropdown-toggle border-0 bg-transparent pe-0 pe-lg-2"
      type="button"
      id="userDropdown"
      data-bs-toggle="dropdown"
      aria-expanded="false"
    >
      <img
        src="https://ui-avatars.com/api/?name=Toan&background=0d6efd&color=fff"
        alt="Avatar"
        class="rounded-circle me-2"
        width="32"
        height="32"
      />
      <span class="fw-semibold d-none d-md-inline">Mộc Toàn</span>
    </button>

    <!-- Dropdown menu -->
    <ul class="dropdown-menu dropdown-menu-end shadow-sm" aria-labelledby="userDropdown">
      <li>
        <button class="dropdown-item d-flex align-items-center" type="button">
          <i class="bi bi-brightness-high-fill me-2 text-warning"></i> Giao diện sáng
        </button>
      </li>
      <li>
        <button class="dropdown-item d-flex align-items-center" type="button">
          <i class="bi bi-moon-fill me-2 text-secondary"></i> Giao diện tối
        </button>
      </li>
      <li>
        <button class="dropdown-item d-flex align-items-center" type="button">
          <i class="bi bi-aspect-ratio-fill me-2"></i> Toàn màn hình
        </button>
      </li>
      <li><hr class="dropdown-divider" /></li>
      <li>
        <button class="dropdown-item d-flex align-items-center text-danger fw-semibold" type="button">
          <i class="bi bi-box-arrow-right me-2"></i> Đăng xuất
        </button>
      </li>
    </ul>
  </div>
</template>

<script setup>

</script>

<style scoped>
.dropdown-item:hover {
  background-color: #f8f9fa;
}
</style>
