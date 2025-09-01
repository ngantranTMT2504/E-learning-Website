<template>
    <div class="my-0">
        <div class="container">
            <div class="row py-5">
                <div class="col-lg-5">
                    <div class="mb-4">
                        <div class="text-center ">
                            <h2 class="">Trở thành {{ registerRole.role === 'teacher' ? 'giáo viên' : 'học viên' }} công
                                nghệ!</h2>
                            <p class="fs-6">Khám phá một cộng đồng hỗ trợ việc dạy và học trực tuyến. Nhận quyền truy
                                cập
                                ngay vào tất cả các tài nguyên tạo khóa học.</p>
                        </div>
                    </div>

                    <div>
                        <div>
                            <form role="form" @submit.prevent="register">
                                <div class="mb-4">
                                    <CustomInput v-model="form.fullname" id="fullname" label="Họ tên" type="text" />

                                    <small class="text-danger" v-if="$v.fullname.$error">
                                        <span v-if="$v.fullname.required.$invalid">Họ tên không được bỏ
                                            trống</span>
                                        <span v-else-if="$v.fullname.minLength.$invalid">Họ tên ít nhất 6 ký
                                            tự.</span>
                                    </small>
                                </div>
                                <div class="mb-4">
                                    <CustomInput v-model="form.email" id="email" label="Email" type="email" />

                                    <small class="text-danger" v-if="$v.email.$error">
                                        <span v-if="$v.email.required.$invalid">Email không được bỏ
                                            trống</span>
                                        <span v-else-if="$v.email.email.$invalid">Email không đúng định
                                            dạng.</span>
                                    </small>
                                </div>
                                <div class="mb-4">
                                    <CustomInput v-model="form.password" id="password" label="Mật khẩu"
                                        type="password" />

                                    <small class="text-danger" v-if="$v.password.$error">
                                        <span v-if="$v.password.required.$invalid">Password không được bỏ
                                            trống</span>
                                        <span v-else-if="$v.password.minLength.$invalid">Password ít nhất 6 ký
                                            tự.</span>
                                    </small>
                                </div>

                                <div class="row my-4">
                                    <CheckboxCustom>
                                        Nhận email về thông tin cập nhật và chương trình khuyến mãi
                                    </CheckboxCustom>
                                </div>

                                <div class="text-center">

                                    <ButtonPrimary type="submit" >Đăng ký
                                    </ButtonPrimary>
                                </div>
                            </form>
                        </div>

                        <div class="mt-3">
                            <p class="fs-7">Khi đăng ký bạn đồng ý với <RouterLink to="#" class="policy fw-semibold">
                                    Điều
                                    khoản sử dụng </RouterLink> và <RouterLink to="#" class="policy fw-semibold">Chính
                                    sách
                                    bảo mật</RouterLink> của chúng tôi</p>
                        </div>
                        <div class="">
                            <p class=" text-center">Đã có tài khoản? <RouterLink to="/auth/login"
                                    class="btn-login fw-semibold">Đăng nhập
                                </RouterLink>
                            </p>
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
import { RouterLink, useRouter, useRoute } from 'vue-router'
import { useRegisterRole } from '@/stores/registerRole'
import { ref } from 'vue'
import ButtonPrimary from '@/components/Common/ButtonCustom.vue'
import CustomInput from '@/components/Common/InputCustom.vue'
import CheckboxCustom from '@/components/Common/CheckboxCustom.vue'
import loginImg from '@/assets/images/AuthForm/login.png'
import useVuelidate from '@vuelidate/core'
import { required, minLength, email } from '@vuelidate/validators'
import axios from 'axios';

const API_student = "http://localhost:8080/api/auth/student/register";
const API_teacher = "http://localhost:8080/api/auth/teacher/register"; 

const registerRole = useRegisterRole()
const route = useRoute()
const router = useRouter()
const agree = ref(false);
const form = ref({
    fullname: "",
    email: "",
    password: "",
    role: null,
    active: true
});

const rules = {
    email: { required, email },
    password: { required, minLength: minLength(6) },
    fullname: { required, minLength: minLength(6) }
};

const $v = useVuelidate(rules, form);


const register = async () => {
    const isValid = await $v.value.$validate();

    if (!isValid) {
        return;
    }

    const payload = {
        email: form.value.email,
        password: form.value.password,
        fullname: form.value.fullname,
        role: {
            id: registerRole.role === "teacher" ? 2 : 1,
            name: registerRole.role === "teacher" ? "TEACHER" : "STUDENT"
        },
        active: true
    };

    try {
        const apiUrl = registerRole.role === "teacher" ? API_teacher : API_student;
        console.log("Dữ liệu gửi đi:", payload);

        await axios.post(apiUrl, payload);

        router.push("/auth/login");

    } catch (error) {
        console.error("Lỗi khi đăng ký:", error);
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
</style>