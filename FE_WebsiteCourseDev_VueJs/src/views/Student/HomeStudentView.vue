<template>
    <div class="banner">
        <div class="row">
            <div class="col-lg-6">
                <div class="content-banner">
                    <div class="title-banner">
                        <h1>Nâng cao <span>kỹ năng</span> của bạn để <span>thăng tiến</span> trên con đường <span>sự
                                nghiệp</span></h1>
                    </div>
                    <div class="mt-4">
                        <ButtonPrimary type="green" class="me-4 px-4 py-2">Bắt đầu</ButtonPrimary>
                    </div>
                    <div class="icon-feature d-flex gap-3 mt-4">
                        <div class="icon-item">
                            <i class="bi bi-boombox"></i>
                            <span class="ms-2">Đa dạng</span>
                        </div>
                        <div class="icon-item">
                            <i class="bi bi-lightbulb"></i>
                            <span class="ms-2">Sáng tạo</span>
                        </div>
                        <div class="icon-item">
                            <i class="bi bi-command"></i>
                            <span class="ms-2">Linh hoạt</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <img :src="banner" alt="banner" class="w-100" />
            </div>
        </div>
    </div>
    <div class="companies py-3">
        <div>
            <h5 class="text-center text-secondary">Được sự tin tưởng của hơn 1000 công ty và người dùng</h5>
            <div class="container">
                <div class="row my-4">
                    <div class="col-lg-2 d-flex justify-content-center">
                        <img :src="logo1" alt="" class="img-fluid logo">
                    </div>
                    <div class="col-lg-2 d-flex justify-content-center">
                        <img :src="logo2" alt="" class="img-fluid logo">
                    </div>
                    <div class="col-lg-2 d-flex justify-content-center">
                        <img :src="logo3" alt="" class="img-fluid logo">
                    </div>
                    <div class="col-lg-2 d-flex justify-content-center">
                        <img :src="logo4" alt="" class="img-fluid logo">
                    </div>
                    <div class="col-lg-2 d-flex justify-content-center">
                        <img :src="logo5" alt="" class="img-fluid logo">
                    </div>
                    <div class="col-lg-2 d-flex justify-content-center">
                        <img :src="logo6" alt="" class="img-fluid logo">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row d-flex my-5">
            <div class="col-md-12 about-intro">
                <div class="row">
                    <div class="col-12 col-lg-6 d-flex wrapper">
                        <div class="img-wrapper wrapper1">
                            <img :src="about" alt="" class="img1 img-fluid">
                        </div>
                        <div class="img-wrapper wrapper2">
                            <img :src="about1" alt="" class="img2 img-fluid">
                        </div>
                    </div>
                    <div class="col-12 col-lg-6 pl-md-5 py-5">
                        <div class="row justify-content-start pb-3">
                            <div class="col-md-12 heading-section ">
                                <span class="subheading">Nâng cao kỹ năng của bạn</span>
                                <h2 class="mb-4">Học bất cứ điều gì bạn muốn ngay hôm nay</h2>
                                <p>
                                    Xa nơi dữ liệu trôi chảy và các dòng mã lặng lẽ hoạt động, phía sau những
                                    bức tường firewall vững chắc, tồn tại một thế giới của các lập trình viên
                                    đam mê.
                                    Họ sống trong không gian của GitHub và Stack Overflow, nơi từng dòng lệnh
                                    đều có mục đích và mỗi giải pháp đều là một hành trình học hỏi.
                                    Một dòng chảy gọi là API kết nối họ lại, truyền tải thông tin, cảm hứng và
                                    công nghệ đi khắp mọi nơi.
                                </p>
                                <p>
                                    <ButtonPrimary type="white">Liên hệ chúng tôi</ButtonPrimary>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <section class="mt-5">
            <h2 class="mb-3 title-section">Sẵn sàng để bắt đầu sự nghiệp của bạn?</h2>
            <p class="fs-6">Nhận các kỹ năng và kinh nghiệm trong thế giới thực mà các nhà tuyển dụng muốn với các máy
                gia tốc nghề nghiệp.</p>
            <div class="row mt-5">
                <div class="col-lg-4" v-for="item in topRatingCourse" :key="item.id">
                    <CardProductCustom :image="item.image" :name-teacher="item.teacherName" :name-type="item.courseTypeName" :price="item.price" :rating="item.averageRating" :slug="item.slug" :title="item.title" />

                </div>
            </div>
        </section>

        <section class="mt-5 ">
            <h2 class="mb-3 title-section">Tất cả kỹ năng mà bạn cần</h2>
            <p class="fs-6">Từ các kỹ năng quan trọng đến các chủ đề kỹ thuật,.</p>
            <div class="mt-5">
                <nav>
                    <div class="nav nav-tabs" id="nav-tab" role="tablist">
                        <button  v-for="(category, index) in categoriessss" :key="index" @click="categoryId = category.id" class="btn-category "
                            :class="{ active: index === 0 }" :id="'tab-' + index" data-bs-toggle="tab"
                            :data-bs-target="'#tab-pane-' + index" type="button" role="tab"
                            :aria-controls="'tab-pane-' + index" :aria-selected="index === 0 ? 'true' : 'false'">
                            {{ category.name }}
                        </button>
                    </div>
                </nav>
                <div class="tab-content" id="nav-tabContent">
                    <div v-for="(category, index) in categoriessss" :key="'pane-' + index" class="tab-pane fade"
                        :class="{ show: index === 0, active: index === 0 }" :id="'tab-pane-' + index" role="tabpanel"
                        :aria-labelledby="'tab-' + index" tabindex="0">
                        <div class="row mt-5">
                            <div class="col-lg-4" v-for="item in coursessss" :key="item.id">
                                <CardProductCustom :image="item.image" :name-teacher="item.teacherName" :name-type="item.courseTypeName" :price="item.price" :rating="item.averageRating" :slug="item.slug" :title="item.title" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="my-5 " id="section-counter">
            <div class="overlay"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-3 d-flex justify-content-center counter-wrap ">
                        <div class="block-18 d-flex align-items-center">
                            <div class="icon">
                                <img :src="numberLearner" alt="">
                            </div>
                            <div class="text">
                                <strong class="number" data-number="400">0</strong>
                                <span>Khóa học</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 d-flex justify-content-center counter-wrap ">
                        <div class="block-18 d-flex align-items-center">
                            <div class="icon">
                                <img :src="numberStudent" alt="">
                            </div>
                            <div class="text">
                                <strong class="number" data-number="4500">0</strong>
                                <span>Học sinh</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 d-flex justify-content-center counter-wrap ">
                        <div class="block-18 d-flex align-items-center">
                            <div class="icon">
                                <img :src="numberInstructor" alt="">
                            </div>
                            <div class="text">
                                <strong class="number" data-number="1200">0</strong>
                                <span>Giảng viên</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 d-flex justify-content-center counter-wrap ">
                        <div class="block-18 d-flex align-items-center">
                            <div class="icon">
                                <img :src="numberTime" alt="">
                            </div>
                            <div class="text">
                                <strong class="number" data-number="300">0</strong>
                                <span>Thời lượng học</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="bg-light mt-5 py-5">
            <div class="container px-4">
                <div class="row g-4">
                    <div class="col-lg-6">
                        <h2 class="mb-4 title-section">Mọi người nói gì về chúng tôi?</h2>
                        <p>
                            Chúng tôi có hơn 1000 đánh giá tích cực từ người dùng trên khắp thế giới
                        </p>
                        <p>
                            Nhiều học sinh đã phát triển tốt về kĩ năng trong nghề nghiệp cua họ
                        </p>
                        <p>Còn bạn thì sao? Hãy trải nghiệm ngay hôm nay</p>
                        <div>
                            <ButtonRadius>Học tập ngay</ButtonRadius>
                        </div>
                    </div>
                    <div class="col-lg-6 review">
                        <div id="carouselReview" class="carousel slide">
                            <div class="carousel-indicators">
                                <button type="button" data-bs-target="#carouselReview" data-bs-slide-to="0"
                                    class="active" aria-current="true" aria-label="Slide 1"></button>
                                <button type="button" data-bs-target="#carouselReview" data-bs-slide-to="1"
                                    aria-label="Slide 2"></button>
                                <button type="button" data-bs-target="#carouselReview" data-bs-slide-to="2"
                                    aria-label="Slide 3"></button>
                            </div>
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img :src="user1" class="d-block w-100" alt="...">
                                    <div class="carousel-caption d-none d-md-block">
                                        <h5 class="text-dark">First slide label</h5>
                                        <p class="text-dark">Some representative placeholder content for the first
                                            slide.</p>
                                    </div>
                                </div>
                                <div class="carousel-item">
                                    <img :src="user2" class="d-block w-100" alt="...">
                                    <div class="carousel-caption d-none d-md-block">
                                        <h5 class="text-dark">Second slide label</h5>
                                        <p class="text-dark">Some representative placeholder content for the second
                                            slide.</p>
                                    </div>
                                </div>
                                <div class="carousel-item">
                                    <img :src="user3" class="d-block w-100" alt="...">
                                    <div class="carousel-caption d-none d-md-block">
                                        <h5 class="text-dark">Third slide label</h5>
                                        <p class="text-dark">Some representative placeholder content for the third
                                            slide.</p>
                                    </div>
                                </div>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselReview"
                                data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselReview"
                                data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>
<script setup>
import banner from '@/assets/images/Student/Banner/student-holding-laptop.png'
import numberLearner from '@/assets/icons/icons-elearning-64.png'
import numberStudent from '@/assets/icons/icons-student-50.png'
import numberInstructor from '@/assets/icons/icons-training-50.png'
import numberTime from '@/assets/icons/icons-time-50.png'
import ButtonPrimary from '@/components/Common/ButtonCustom.vue'
import ButtonRadius from '@/components/Common/ButtonRadius.vue'
import logo1 from '@/assets/images/Student/Companies-logo/cisco-logo.png'
import logo2 from '@/assets/images/Student/Companies-logo/PG_logo.png'
import logo3 from '@/assets/images/Student/Companies-logo/samsung-logo.png'
import logo4 from '@/assets/images/Student/Companies-logo/vimeo-logo.png'
import logo5 from '@/assets/images/Student/Companies-logo/vietcombank-logo.png'
import logo6 from '@/assets/images/Student/Companies-logo/volkswagen-logo.png'
import about from '@/assets/images/Student/Home/about-img.jpg'
import about1 from '@/assets/images/Student/Home/he-thong-elearning-2.jpg'
import user1 from '@/assets/images/Student/Users/female-orange.jpg'
import user2 from '@/assets/images/Student/Users/female-pink.jpg'
import user3 from '@/assets/images/Student/Users/male-white.jpg'
import CardProductCustom from '@/components/Common/CardProductCustom.vue';
import { getList } from '@/services/apiMiddlewareV2'
import { onMounted, ref, watch } from 'vue'

const API_Categories = '/api/course-type'
const categoriessss = ref([]);
const categoryId = ref(0);
const fetchCategories = async () =>{
    try {
        const response = await getList(API_Categories);
        categoriessss.value = response.data;
    } catch (error) {
        console.log(error);
        
    }
}
const API_Courses = "/api/course"
const coursessss = ref([]);
const fetchCourses = async () =>{
    try {
        const filter = {
            size: 3,
            courseTypeIds: categoryId.value
        }
        const response = await getList(API_Courses, filter);
        coursessss.value = response.data;
    } catch (error) {
        console.log(error);
    }
}
watch(categoryId, (newVal)=>{
    categoryId.value = newVal;
    fetchCourses();
})
const topRatingCourse = ref([]);
const fetchTopRatingCourse= async () =>{
    try {
        const filter = {
            size: 3,
            sort: 'averageRating,desc'
        }
        const response = await getList(API_Courses, filter);
        topRatingCourse.value = response.data;
    } catch (error) {
        console.log(error);
    }
}
onMounted(async() => {
    await fetchCategories();
    await fetchTopRatingCourse();
    categoryId.value = categoriessss.value[0].id;
    await fetchCourses();
});

const categories = [
    {
        title: "Lập trình Web",
    },
    {
        title: "Phân tích dữ liệu",
    },
    {
        title: "Kỹ năng mềm",
    },
    {
        title: "Python",
    },
    {
        title: "Machine Learning",
    },
    {
        title: "Giao tiếp",
    },
]

const courses = [
    {
        title: "Lập trình Vue 3 toàn tập",
        image: "https://s3-sgn09.fptcloud.com/codelearnstorage/files/thumbnails/cpp-cho-nguoi-moi-bat-dau_09e94813a177425db74fb7c23e65c859.png",
        nameType: "Lập trình web",
        nameTeacher: "Nguyễn Văn A",
        rating: 4.8,
        price: 499000,
        slug: "lap-trinh-vue-3-toan-tap"
    },
    {
        title: "ReactJS từ cơ bản đến nâng cao",
        image: "https://s3-sgn09.fptcloud.com/codelearnstorage/files/thumbnails/cpp-cho-nguoi-moi-bat-dau_09e94813a177425db74fb7c23e65c859.png",
        nameType: "Lập trình web",
        nameTeacher: "Trần Thị B",
        rating: 4.7,
        price: 599000,
        slug: "reactjs-co-ban-den-nang-cao"
    },
    {
        title: "Python cho người mới bắt đầu",
        image: "https://s3-sgn09.fptcloud.com/codelearnstorage/files/thumbnails/cpp-cho-nguoi-moi-bat-dau_09e94813a177425db74fb7c23e65c859.png",
        nameType: "Lập trình cơ bản",
        nameTeacher: "Lê Văn C",
        rating: 4.5,
        price: 399000,
        slug: "python-cho-nguoi-moi"
    }
]
</script>

<style scoped>
.banner {
    background-color: var(--blue-green);
    height: 80vh;
    overflow: hidden;
}

.companies {
    background-color: var(--light-gray);
    position: relative;
    top: 0;
}

.banner img {
    object-fit: cover;
    height: 100%;
}

.logo {
    height: 60px;
}

.content-banner {
    margin: 9rem 0 0 8rem;
}

.banner .wave {
    position: absolute;
    width: 100vw;
    top: -500px;
    overflow: hidden;
    line-height: 0;
    z-index: 100;
}

.banner .wave svg {
    display: block;
    width: 100vw;
    height: 100%;
    z-index: 100;
    top: -500px;
}

.banner .wave svg path{
    z-index: 100;
}

.title-banner h1 {
    font-size: 50px;
    font-weight: 600;
}

.title-banner span {
    color: white;
    font-weight: 600;
}

.icon-item span {
    font-size: 20px;
}

.icon-item i {
    color: var(--bare-orange);
    font-size: 24px;
}

.img1 {
    width: 350px;
}

.img2 {
    width: 350px;
}

.wrapper {
    position: relative;
}

.img-wrapper {
    position: absolute;
}

.wrapper2 {
    top: 150px;
    left: 180px;
}

.title-section {
    color: var(--green-text);
}

.nav-link {
    color: var(--bare-orange);
    font-weight: 600;
}

.nav-link:hover,
.nav-link .active {
    color: var(--green-text) !important;
}

#section-counter {
    background-color: var(--blue-green);
    padding: 30px;
}

.number {
    display: block;
    margin-inline: 10px;
    font-size: 30px;
}

.text {
    margin-top: 30px;
    color: var(--green-text);
}

.text span {
    font-size: 16px;
}

.icon img {
    width: 50px;
    height: 50px;

}

.btn-category {
    border: 0;
    background-color: rgba(255, 255, 255, 0);
    margin-inline: 10px;
    padding-bottom: 10px;
}

.active {
    color: var(--bare-orange);
}

.btn-category::after {
    content: '';
    display: block;
    height: 2px;
    width: 0%;
    background-color: var(--green-text);
    transition: 0.3s ease-in;
}

.btn-category:hover {
    color: var(--bare-orange);
}
</style>
