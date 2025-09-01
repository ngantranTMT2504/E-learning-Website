<template>
    <footer class="w-100 shadow bg-green-primary text-white">
        <div class="container">
            <div class="row">
                <div class="col-md-3 pt-5">
                    <div class="pt-md-5 mb-4">
                        <h3 class="">Giới thiệu</h3>
                        <p class="fs-6">Trang web cung cấp các khóa học chất lượng, giúp bạn nâng cao kỹ năng lập trình,
                            phát
                            triển phần mềm và công nghệ thông tin từ cơ bản đến nâng cao.</p>
                        <ul class="d-flex gap-3 list-unstyled float-md-left float-lft">
                            <li class="fs-4">
                                <a href="#">
                                    <i class="fa-brands fa-github"></i>
                                </a>
                            </li>
                            <li class="fs-4">
                                <a href="#">
                                    <i class="fa-brands fa-facebook"></i>
                                </a>
                            </li>
                            <li class="fs-4">
                                <a href="#">
                                    <i class="fa-brands fa-twitter"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3 pt-5">
                    <div class="pt-md-5 mb-4 ml-md-5">
                        <h3 class="">Trợ giúp</h3>
                        <ul class="list-unstyled">
                            <li><a href="#" class="py-2 d-block">Chăm sóc khách hàng</a></li>
                            <li><a href="#" class="py-2 d-block">Trợ giúp pháp lý</a></li>
                            <li><a href="#" class="py-2 d-block">Dịch vụ</a></li>
                            <li><a href="#" class="py-2 d-block">Quyền riêng tư & chính sách</a></li>
                            <li><a href="#" class="py-2 d-block">Chính sách hoàn tiền</a></li>
                            <li><a href="#" class="py-2 d-block">Liên hệ</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3 pt-5">
                    <div class="ftco-footer-widget pt-md-5 mb-4">
                        <h3 class="ftco-heading-2">Khóa học</h3>
                        <ul class="list-unstyled">
                            <li><a href="#" class="py-2 d-block">Computer Engineering</a></li>
                            <li><a href="#" class="py-2 d-block">Web Design</a></li>
                            <li><a href="#" class="py-2 d-block">Data Analysis</a></li>
                            <li><a href="#" class="py-2 d-block">AI</a></li>
                            <li><a href="#" class="py-2 d-block">Computer Technician</a></li>
                            <li><a href="#" class="py-2 d-block">Web Developer</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3 pt-5">
                    <div class="ftco-footer-widget pt-md-5 mb-4">
                        <h4 class="ftco-heading-2">Thông tin liên hệ</h4>
                        <div class="block-23 mb-3">
                            <ul class="p-0">
                                <li>
                                    <i class="bi bi-building"></i>
                                    <span class="ms-2">Toà nhà FPT Polytechnic, đường số 22, phường Thường Thạnh, quận Cái Răng, TP Cần Thơ </span>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="bi bi-telephone-fill"></i>
                                        <span class="ms-2">+84 392 392 210</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="bi bi-send-fill"></i>
                                        <span class="ms-2">info@yourdomain.com</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</template>

<script setup>

</script>
<style scoped>

.shadow{
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}

.bg-green-primary {
    background-color: var(--green-primary);
}

footer a {
    color: white ;
    text-decoration: none;
    transition: .3s ease-in-out;
}
footer a:hover {
    transform: translateX(10px);
    color: var(--bare-orange);
}

</style>