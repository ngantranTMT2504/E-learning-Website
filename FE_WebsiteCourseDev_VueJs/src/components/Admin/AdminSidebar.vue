<template>
  <div>

    <!-- Sidebar desktop -->
    <div class="sidebar d-none d-md-block bg-light border-end-0">
      <div class=" p-3 border-bottom sticky-top bg-light z-3 box-bottom-shadow">
        <img :src="logo" alt="Logo" height="44" />
      </div>
      <div class="menu-scroll">
        <div class="offcanvas-body">
          <AdminMenuSidebar />
        </div>
      </div>
    </div>


    <!-- Offcanvas mobile -->
    <div class="offcanvas offcanvas-start mobile-sidebar" tabindex="-1" id="mobileAdminSidebar">
      <div class="offcanvas-header border-bottom py-3">
        <!-- <h2 class="offcanvas-title">Menu</h2> -->
        <img :src="logo" alt="Logo" width="120" height="44" />
        <button type="button" class="btn-close me-2" data-bs-dismiss="offcanvas"></button>
      </div>
      <div class="offcanvas-body menu-scroll">
        <AdminMenuSidebar />
      </div>
    </div>
  </div>
</template>

<script setup>
import logo from '@/assets/logoIT.png'
import AdminMenuSidebar from './AdminMenuSidebar.vue';

</script>

<style scoped>
.sidebar {
  width: 300px;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1030;
  scroll-behavior: smooth;
}

.mobile-sidebar {
  width: 18.75rem;
}

.menu-scroll {
  overflow-y: auto;
  scrollbar-gutter: stable;
  max-height: calc(100vh - 90px);
}

.box-bottom-shadow {
  box-shadow: 0 .125rem 0 rgba(0, 0, 0, 0.03);
}
</style>
