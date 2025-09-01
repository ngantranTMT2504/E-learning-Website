<template>
   		<div class="container py-5">
			<div class="row justify-content-center">
				<div class="col-md-12">
					<div class="wrapper">
						<div class="row no-gutters">
							<div class="col-lg-8 col-md-7 order-md-last d-flex align-items-stretch">
								<div class="contact-wrap w-100 p-md-5 p-4">
									<h3 class="mb-4">Thông tin</h3>
									<form class="contactForm">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group mb-3">
													<label class="label" for="name">Họ tên*</label>
													<input type="text" class="form-control" name="name" id="name" placeholder="Họ tên">
												</div>
											</div>
											<div class="col-md-6"> 
												<div class="form-group mb-3">
													<label class="label" for="email">Email*</label>
													<input type="email" class="form-control" name="email" id="email" placeholder="Email">
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group mb-3">
													<label class="label" for="subject">Tiêu đề*</label>
													<input type="text" class="form-control" name="subject" id="subject" placeholder="Tiêu đề">
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group mb-3">
													<label class="label" for="#">Nội dung*</label>
													<textarea name="message" class="form-control" id="message" cols="30" rows="4" placeholder="Nội dung"></textarea>
												</div>
											</div>
											<div class="col-md-12">
												<div class="form-group">
													<ButtonCustom >Liên hệ</ButtonCustom>
													<div class="submitting"></div>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
							<div class="col-lg-4 col-md-5 d-flex align-items-stretch">
								<div class="info-wrap text-white w-100 p-md-5 p-4">
									<h3>Hãy liên lạc</h3>
									<p class="mb-4">Chúng tôi luôn sẵn sàng lắng nghe mọi góp ý hoặc đơn giản chỉ là trò chuyện</p>
									<div class="dbox w-100 d-flex align-items-start">
										<div class="icon d-flex align-items-center justify-content-center me-2">
											<i class="bi bi-geo-alt-fill"></i>
										</div>
										<div class="text pl-3">
											<p><span>Địa chỉ:</span>Toà nhà FPT Polytechnic, đường số 22, phường Thường Thạnh, quận Cái Răng, TP Cần Thơ</p>
										</div>
									</div>
									<div class="dbox w-100 d-flex align-items-center">
										<div class="icon d-flex align-items-center justify-content-center me-2">
											<i class="bi bi-telephone-fill"></i>
										</div>
										<div class="text pl-3">
											<p class="my-auto"><span>Phone:</span> <a href="tel://1234567920">098 388 1100</a></p>
										</div>
									</div>
									<div class="dbox w-100 d-flex align-items-center">
										<div class="icon d-flex align-items-center justify-content-center me-2">
											<i class="bi bi-envelope-fill"></i>
										</div>
										<div class="text pl-3">
											<p class="my-auto"><span>Email:</span> <a href="mailto:info@yoursite.com">devcoursefpoly@gmail.com</a></p>
										</div>
									</div>
									<div class="dbox w-100 d-flex align-items-center">
										<div class="icon d-flex align-items-center justify-content-center">
											<span class="fa fa-globe"></span>
										</div>
										<div class="text pl-3">
											<p><span>Website</span> <RouterLink to="/">devcourse.com</RouterLink></p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- <div class="col-md-12 mt-5">
					<div id="map" class="bg-white"></div>
				</div> -->
			</div>
		</div>
</template>

<script setup>
import ButtonCustom from '@/components/Common/ButtonCustom.vue';
</script>

<style scoped>
.form-control:focus {
    color: var(--bs-body-color);
    border-color: var(--green-primary);
    outline: 0;
    box-shadow: none;
}
.wrapper {
    width: 100%;
    -webkit-box-shadow: 0px 2px 25px -13px rgba(0, 0, 0, 0.26);
    -moz-box-shadow: 0px 2px 25px -13px rgba(0, 0, 0, 0.26);
    box-shadow: 0px 2px 25px -13px rgba(0, 0, 0, 0.26);
}
.no-gutters {
    margin-right: 0;
    margin-left: 0;
}
.info-wrap{
    background: var(--green-primary);
}
.info-wrap a{
    color: white;
}
.info-wrap .dbox {
    width: 100%;
    color: rgba(255, 255, 255, 0.8);
    margin-bottom: 25px;
}

</style>