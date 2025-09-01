<template>
    <div class="my-0">
        <div class="container">
            <div class="row py-5">
                <div class="col-lg-5">
                    <div class="mb-4">
                        <div class="text-center ">
                            <h2 class="">Xác nhận năng lực giảng dạy!</h2>
                            <p class="fs-6">Hãy chứng minh bạn có năng lực giảng dạy và cho ra các bài học chất lượng
                                cao.
                            </p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <form role="form">
                                <div class="mb-4">
                                    <CustomInput v-model="accountHolder" id="accountHolder" label="Chủ tài khoản" type="text" />
                                </div>
                                <div class="mb-4">
                                   <CustomInput v-model="accountNumber" id="accountNumber" label="Số tài khoản" type="text" />
                                </div>
                                <div class="mb-3">
                                    <CustomInput v-model="bankName" id="bankName" label="Ngân Hàng" type="text" />
                                </div>

                                <div class="text-center">
                                    <ButtonPrimary @click="register">Tiếp tục</ButtonPrimary>
                                </div>
                            </form>
                        </div>

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
import { RouterLink, useRouter } from 'vue-router'
import ButtonPrimary from '@/components/Common/ButtonCustom.vue'
import CustomInput from '@/components/Common/InputCustom.vue'
import CheckboxCustom from '@/components/Common/CheckboxCustom.vue'
import loginImg from '@/assets/images/AuthForm/login.png'

const router = useRouter();

const register = () => {
    router.push("/auth/login")
};

</script>
<style scoped>
.policy,
.btn-login {
    text-decoration: underline;
    color: var(--green-text);
}

.form-select,
.form-degree,
.btn-certificates,
.form-certificate {
    border: 1px solid black;
}

.btn-certificates:hover {
    border: 1px solid black;
}

.icon-button img {
    width: 36px;
}

.icon-button {
    border: 1 solid black;
    margin-inline: 20px;
}

.check-custom {
    display: flex;
    align-items: center;
    cursor: pointer;
    user-select: none;
    font-size: 1rem;
}
</style>