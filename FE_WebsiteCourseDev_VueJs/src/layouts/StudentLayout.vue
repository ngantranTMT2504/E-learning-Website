<template >
   <HeaderStudent/>
   <div class="margin-top">
      <RouterView/>
   </div>
   <FooterStudent/>
</template>
<script setup>
import HeaderStudent from '@/components/Student/HeaderStudent.vue';
import FooterStudent from '@/components/Student/FooterStudent.vue'
import { RouterView} from 'vue-router'
</script>
<style scoped>
    .margin-top{
      margin-top: 75px;
    }
</style>