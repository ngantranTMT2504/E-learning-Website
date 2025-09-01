<template>
    <div>
        <TeacherSidebar />
        <div class="fixed-top z-3 top-0">
            <TeacherNavbar/>
        </div>
        <main class="main-content">
            <RouterView/>
        </main>
    </div>
</template>

<script setup>
import TeacherSidebar from '@/components/Teacher/TeacherSidebar.vue';
import TeacherNavbar from '@/components/Teacher/TeacherNavbar.vue';
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