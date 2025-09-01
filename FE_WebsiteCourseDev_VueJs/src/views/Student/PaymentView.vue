<template>
    <section class="container pt-5 pb-5">
        <div class="row g-4">
            <div class="col-lg-8">
                <div class="row banner text-white border rounded shadow-sm overflow-hidden p-4">
                    <div class="col-lg-5 mb-3 mb-lg-0">
                        <img src="https://s3-sgn09.fptcloud.com/codelearnstorage/files/thumbnails/AI-applications_bd4d24f5e00f4aabbd8c8636c815b438.jpg"
                            class="img-fluid rounded w-100 h-100 object-fit-cover" alt="Khóa học" />
                    </div>
                    <div class="col-lg-7">
                        <div>
                            <h2 class="fw-bold mb-3">Game "ăn liền" cùng Scratch</h2>
                            <p class="description">
                                Khóa học giúp trẻ 6–11 tuổi làm quen lập trình qua Scratch, tự tay tạo game đơn giản,
                                rèn tư duy logic,
                                sáng tạo và bước đầu tiếp cận thế giới công nghệ một cách vui nhộn
                            </p>
                        </div>
                        <div class="mt-1 d-flex flex-wrap gap-3 fs-7">
                            <span><strong>Tác giả:</strong> Văn Tèo</span>
                            <span><i class="bi bi-people-fill me-1"></i><strong>Học viên:</strong> 49,999</span>
                            <RatingCustom :rating="4.5" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="card shadow-sm p-4">
                    <h5 class="fw-bold mb-3">Phương thức thanh toán</h5>
                    <div class="row g-3">
                        <div class="col-6">
                            <label for="qr"
                                class="payment-option d-flex flex-column justify-content-center align-items-center">
                                <img src="https://codelearn.io/images/payment/vietqr_3.png" alt="VietQR" width="50"
                                    class="mb-2" />
                                <span>VietQR</span>
                                <input id="qr" type="radio" name="payment" checked />
                            </label>
                        </div>
                        <div class="col-6">
                            <label for="banking"
                                class="payment-option d-flex flex-column justify-content-center align-items-center">
                                <img src="https://codelearn.io/images/payment/mobilebanking_3.png" alt="Banking"
                                    width="50" class="mb-2" />
                                <span>Banking</span>
                                <input id="banking" type="radio" name="payment" />
                            </label>
                        </div>
                        <div class="col-6">
                            <label for="momo"
                                class="payment-option d-flex flex-column justify-content-center align-items-center">
                                <img src="https://upload.wikimedia.org/wikipedia/vi/f/fe/MoMo_Logo.png" alt="Banking"
                                    width="50" class="mb-2" />
                                <span>Momo</span>
                                <input id="momo" type="radio" name="payment" />
                            </label>
                        </div>
                        <div class="col-6">
                            <label for="vnpay"
                                class="payment-option d-flex flex-column justify-content-center align-items-center">
                                <img src="https://vinadesign.vn/uploads/images/2023/05/vnpay-logo-vinadesign-25-12-57-55.jpg" alt="Banking"
                                    width="50" class="mb-2" />
                                <span>VNPAY</span>
                                <input id="vnpay" type="radio" name="payment" />
                            </label>
                        </div>
                    </div>

                    <hr class="my-4" />
                    <h5 class="fw-bold">Tổng tiền</h5>
                    <ul class="list-group border-0 mb-3">
                        <li class="list-group-item border-0 border-bottom d-flex justify-content-between">
                            <span>Tổng tiền</span>
                            <strong>{{ formatPrice(3333333) }}</strong>
                        </li>
                        <li class="list-group-item border-0 border-bottom d-flex justify-content-between">
                            <span><strong>Tổng thanh toán</strong></span>
                            <strong class="text-primary">{{ formatPrice(88888) }}</strong>
                        </li>
                    </ul>
                    <ButtonCustom class="w-100">Thanh toán</ButtonCustom>
                </div>
            </div>
        </div>
    </section>
</template>

<script setup>
import formatPrice from '@/utils/formatPrice';
import RatingCustom from '@/components/Common/RatingCustom.vue';
import ButtonCustom from '@/components/Common/ButtonCustom.vue';
</script>

<style scoped>
.banner {
    /* background: linear-gradient(135deg, #14726c, #1f8f89); */
    background: var(--green);
}

.description {
    font-size: 0.95rem;
    line-height: 1.6;
}


.payment-option {
    position: relative;
    border: 2px solid #dee2e6;
    border-radius: 10px;
    padding: 12px;
    text-align: center;
    cursor: pointer;
    transition: border-color 0.3s, box-shadow 0.3s;
    height: 120px;
    user-select: none;
}

.payment-option:hover {
    box-shadow: 0 0 0 2px rgba(13, 110, 253, 0.2);
}

.payment-option input[type="radio"] {
    position: absolute;
    top: 10px;
    right: 10px;
    transform: scale(1.2);
    accent-color: var(--green-primary);
}
</style>
