<template>
    <div class="my-0">
        <div class="container">
            <div class="row py-5">
                <div class="col-lg-5">
                    <div class="mb-4">
                        <div class="text-center ">
                            <h2 class="">Đăng nhập để bắt đầu</h2>
                            <p class="fs-6">Khám phá một cộng đồng hỗ trợ học trực tuyến.</p>
                        </div>
                    </div>

                    <div>
                        <form role="form" class="mb-3" @submit.prevent="login">
                            <div class="mb-4">
                                <CustomInput v-model="form.email" id="email" label="Email" type="email" />

                                <small class="text-danger" v-if="$v.email.$error">
                                    <span v-if="$v.email.required.$invalid">Email không được bỏ
                                        trống</span>
                                    <span v-else-if="$v.email.email.$invalid">Email không đúng định
                                        dạng.</span>
                                </small>
                            </div>
                            <div>
                                <CustomInput v-model="form.password" id="password" label="Mật khẩu" type="password" />

                                <small class="text-danger" v-if="$v.password.$error">
                                    <span v-if="$v.password.required.$invalid">Password không được bỏ
                                        trống</span>
                                    <span v-else-if="$v.password.minLength.$invalid">Password ít nhất 6 ký
                                        tự.</span>
                                </small>
                            </div>
                            <div class="text-center">
                                <ButtonPrimary type="submit">Đăng nhập</ButtonPrimary>
                            </div>
                        </form>
                        <div class="mt-3">
                            <div class="text-center mt-2 mb-4"><small>Đăng nhập với</small></div>
                            <div class="text-center">
                                <RouterLink to="#" class="icon-button">
                                    <img :src="github" alt="github icon">
                                </RouterLink>
                                <RouterLink to="#" class="icon-button">
                                    <img :src="google" alt="google icon">
                                </RouterLink>
                                <RouterLink to="#" class="icon-button">
                                    <img :src="facebook" alt="facebook icon">
                                </RouterLink>
                            </div>
                        </div>
                    </div>
                    <div class="mt-3">
                        <p class=" text-center">Chưa có tài khoản? <RouterLink to="/auth/register"
                                class="btn-login fw-semibold">Đăng ký
                            </RouterLink>
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
import { RouterLink, useRouter } from 'vue-router'
import useVuelidate from '@vuelidate/core'
import { required, minLength, email } from '@vuelidate/validators'
import { ref } from 'vue';
import { jwtDecode } from "jwt-decode";
import axios from 'axios';
import { useAuthStore } from '@/stores/authStore'

import ButtonPrimary from '@/components/Common/ButtonCustom.vue'
import CustomInput from '@/components/Common/InputCustom.vue'
import loginImg from '@/assets/images/AuthForm/login.png'
import github from '@/assets/images/github-48.png'
import google from '@/assets/images/google-48.png'
import facebook from '@/assets/images/facebook-48.png'


const API = "http://localhost:8080/api/auth/login";
const router = useRouter();
const authStore = useAuthStore()

const form = ref({
    email: "",
    password: "",
});

const rules = {
    email: { required, email },
    password: { required, minLength: minLength(6) }
};

const $v = useVuelidate(rules, form);

const login = async () => {
    const isValid = await $v.value.$validate();

    if (!isValid) {
        // showError("Vui lòng kiểm tra lại thông tin!");
        return;
    }


    console.log("Dữ liệu gửi đi:", JSON.stringify(form.value));
    const payload = {
        email: form.value.email,
        password: form.value.password
    }
    console.log(payload);
    try {
        const response = await axios.post(API, payload)

        if (response?.data?.length > 0) {

            authStore.loginSuccess(response?.data)
            const role = authStore.role

            // console.log("decode", JSON.stringify(role, null, 2))

            if (role === 'Admin') {
                await router.push("/admin")
            } else if(role === 'Teacher'){
                await router.push("/teacher")
            } else {
                await router.push("/")
            }
        } else {
            console.log(response.data);
        }
    } catch (error) {
        console.log(error);

    }
};

</script>
<style scoped>
.policy,
.btn-login {
    text-decoration: underline;
    color: var(--green-text);
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

.check-custom input[type="checkbox"] {
    display: none;
}

.check-custom .checkmark {
    width: 18px;
    height: 18px;
    border: 2px solid #ccc;
    border-radius: 4px;
    background-color: white;
    position: relative;
    transition: all 0.2s ease;
}

.check-custom input[type="checkbox"]:checked+.checkmark {
    background-color: var(--green-primary);
    border-color: var(--green-primary);
}

.check-custom input[type="checkbox"]:checked+.checkmark::after {
    content: "";
    position: absolute;
    left: 5px;
    top: 2px;
    width: 4px;
    height: 8px;
    border: solid white;
    border-width: 0 2px 2px 0;
    transform: rotate(45deg);
}
</style>