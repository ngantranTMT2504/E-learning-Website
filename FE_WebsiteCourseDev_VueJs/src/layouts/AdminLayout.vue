<template>
    <div>
        <AdminSidebar />
        <div class="fixed-top z-3 top-0">
            <AdminNavbar/>
        </div>
        <main class="main-content">
            <RouterView/>
        </main>
    </div>
</template>

<script setup>
import AdminSidebar from '@/components/Admin/AdminSidebar.vue';
import AdminNavbar from '@/components/Admin/AdminNavbar.vue';
import { RouterView } from 'vue-router';
</script>

<style scoped>
.main-content {
    margin-left: 300px;
    padding: 2rem;
    margin-top: 4rem;
}

@media (max-width: 767.98px) {
    .main-content {
        margin-left: 0px;
    }
}
</style>