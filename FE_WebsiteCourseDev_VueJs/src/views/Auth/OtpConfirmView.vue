<template>
  <div>
    <div class="container">
      <div class="row py-5">
        <div class="col-lg-5">
          <div class="mb-4 text-center">
            <h2>Xác nhận mã OTP!</h2>
            <p class="fs-6">Vui lòng kiểm tra email và nhập mã để xác nhận đúng tài khoản</p>
          </div>

          <form role="form" >
            <CustomInput v-model="otpCode" id="otpcode" label="Mã OTP" type="text" />

            <div class="text-center">
              <ButtonPrimary @click="confirm">Tiếp tục</ButtonPrimary>
            </div>
          </form>

          <div class="mt-3 text-center">
            <p>Không nhận được email?
              <RouterLink to="/auth/register" class="btn-back fw-semibold">Quay lại</RouterLink>
            </p>
          </div>
        </div>

        <div class="col-lg-7">
          <img :src="loginImg" alt="" class="img-fluid">
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import CustomInput from '@/components/Common/InputCustom.vue'
import ButtonPrimary from '@/components/Common/ButtonCustom.vue'
import { useRouter, RouterLink } from 'vue-router'
import loginImg from '@/assets/images/AuthForm/login.png'

const otpCode = ref('')
const router = useRouter()

const confirm = () => {
  router.push("/auth/verify-teacher")
}

</script>

<style scoped>
.btn-back {
  color: var(--primary-green)
}
</style>
