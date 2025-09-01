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
                                    <CustomInput v-model="phone" id="phone" label="Số điện thoại" type="text" />
                                </div>
                                <div class="mb-4">
                                    <select class="form-select py-2" aria-label="Trình độ học vấn">
                                        <option selected>Trình độ học vấn</option>
                                        <option value="Trung cấp">Trung cấp</option>
                                        <option value="Cao đẳng">Cao đẳng</option>
                                        <option value="Đại học">Đại học</option>
                                        <option value="Thạc sĩ">Thạc sĩ</option>
                                        <option value="Tiến sĩ">Tiến sĩ</option>
                                        <option value="Giáo sư">Giáo sư</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label class="m-2 mt-0" for="degree">Bằng tốt nghiệp</label>
                                    <input type="file" class="form-control py-2 form-degree" id="degree">
                                </div>
                                <div>
                                    <p class="m-2 mt-0">Chứng nhận, chứng chỉ (nếu có)</p>
                                    <!-- Button trigger modal -->
                                    <button type="button" class="btn btn-certificates" data-bs-toggle="modal"
                                        data-bs-target="#certificateModal">
                                        <i class="bi bi-cloud-plus"></i>
                                        Thêm chứng nhận
                                    </button>

                                    <!-- Modal -->
                                    <div class="modal fade" id="certificateModal" data-bs-backdrop="static"
                                        data-bs-keyboard="false" tabindex="-1" aria-labelledby="certificateModalLabel"
                                        aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="certificateModalLabel">
                                                        Thông tin
                                                    </h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form>
                                                        <div class="mb-3">
                                                            <CustomInput v-model="nameCetificate" id="nameCetificate"
                                                                label="Tên chứng chỉ" type="text" />
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="m-2 mt-0" for="certificateUrl">chứng nhận</label>
                                                            <input type="file" class="form-control py-2 form-certificate"
                                                                id="certificateUrl">
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                        data-bs-dismiss="modal">Hủy</button>
                                                    <ButtonPrimary>Thêm</ButtonPrimary>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row my-4">
                                    <CheckboxCustom>
                                        Tôi xác nhận rằng tất cả thông tin tôi đã cung cấp là đúng sự thật.
                                    </CheckboxCustom>
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
    router.push("/auth/bank-info")
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