<template>
    <div class="w-100 banner_product overflow-hidden">
      <img :src="banner_product" alt="" class="img-fluid">
    </div>
    <div class="container pt-5">

        <div class="position-relative">
            <Swiper
            :modules="modules"
            :slides-per-view="3"
            :centered-slides="true"
            :space-between="30"
            :loop="courseTops.length > 3"
            :navigation="false"
            :auto-height="true"
            :autoplay="{
                delay: 3000,   
                disableOnInteraction: false 
            }"
            :breakpoints="{
                0: {
                slidesPerView: 1
                },
                768: {
                slidesPerView: 2
                },
                1024: {
                slidesPerView: 3
                }
            }"
            class="mySwiper"
            @swiper="onSwiperInit">
                <SwiperSlide v-for="item in courseTops" :key="item.id">
                    <CardProductCustom :image="item.image" :name-teacher="item.teacherName" :name-type="item.courseTypeName" :price="item.price" :rating="item.averageRating" :slug="item.slug" :title="item.title" />
                </SwiperSlide>
            </Swiper>
            <button ref="prevEl" class="btn btn-light nav-btn prev-btn"><i class="bi bi-arrow-left-short"></i></button>
            <button ref="nextEl" class="btn btn-light nav-btn next-btn"><i class="bi bi-arrow-right-short"></i></button>
        </div>
        <div class="p-2 pt-3">
            <h5> <i class=" bi bi-funnel"></i>Bộ lọc tìm kiếm</h5>
            <div class="row">
                <div class="col-lg-3">
                  <div class="mb-2">
                        <form @submit.prevent="" class="search-form position-relative">
                            <div class="form-group">
                                <input v-model="keySearch" type="text" class="form-control pe-5" placeholder="Tìm kiếm..." />
                            </div>
                            <button class="position-absolute end-0 top-50 translate-middle-y btn">
                              <i class="bi bi-search"></i>
                            </button>
                        </form>
                    </div>
                  <div class="card rounded p-2">
                    <SlideBarBoxCustom :options="typeCourses" :title="'Thể loại'" v-model:model-value="searchTypes"/>
                  </div>
                </div>
                <div  class="col-lg-9">
                  <div class="row">
                    <div v-for="item in courses" :key="item.id" class="col-lg-4">
                      <CardProductCustom :image="item.image" :name-teacher="item.teacherName" :name-type="item.courseTypeName" :price="item.price" :rating="item.averageRating" :slug="item.slug" :title="item.title" />
                    </div>
                    <h5 class="text-muted mt-5 text-center" v-if="courses.length < 1">Không tìm thấy khóa học</h5>
                  </div>
                  <div class="d-flex justify-content-center py-2">
                   <PaginationCustom :total-page="totalPage" :current-page="currentPage" v-model:current-page="currentPage"/>
                  </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, nextTick, onMounted, watch, computed } from 'vue';
import { Swiper, SwiperSlide } from 'swiper/vue';
import { Pagination, Navigation, Autoplay } from 'swiper/modules';
import CardProductCustom from '@/components/Common/CardProductCustom.vue';
import SlideBarBoxCustom from '@/components/Common/SlideBarBoxCustom.vue';
import PaginationCustom from '@/components/Common/PaginationCustom.vue';
import banner_product from '@/assets/images/Student/Banner/banner_product.png'
import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';
import {getList, getById, createItem, deleteItem} from  '@/services/apiMiddlewareV2'

const modules = [Pagination, Navigation, Autoplay];
const nextEl = ref(null);
const prevEl = ref(null);

const onSwiperInit = (swiper)=> {
  nextTick(() => {
    swiper.params.navigation.prevEl = prevEl.value;
    swiper.params.navigation.nextEl = nextEl.value;
    swiper.navigation.init();
    swiper.navigation.update();
  });
}

const APITypeCourse = "/api/course-type";
const typeCourses = ref([]);
const searchTypes = ref([]);
const getListTypeCourse = async () =>{
  try {
    const response = await getList(APITypeCourse);
    typeCourses.value = response.data.filter(t => t.status == true);
  } catch (error) {
    console.log(error);
  }
}
const APICourses = "/api/course";
const courseTops = ref([]);
const getListCourseTop = async () =>{
  try {
    const filter = {
      status: true,
      page: 0,
      size: 10,
      sort: 'averageRating,desc'
    }
    const response = await getList(APICourses, filter);
    courseTops.value = response.data;
  } catch (error) {
    console.log(error);
  }
}
const currentPage = ref(0);
const totalPage = ref(0);
const size = ref(6);
const courses = ref([]);
const keySearch = ref('');
watch([searchTypes, currentPage, keySearch], () => {
  getListCourse();
});
watch([searchTypes], () => {
  currentPage.value = 0;
});
const getListCourse = async () =>{
  try {
    const filter = {
      status: true,
      courseTypeIds: searchTypes.value,
      keyword: keySearch.value,
      page: currentPage.value,
      size: size.value
    }
    const response = await getList(APICourses, filter);
    courses.value = response.data;
    totalPage.value = response.totalPages
  } catch (error) {
    console.log(error);
  }
}

onMounted(() => {
  getListTypeCourse();
  getListCourse();
  getListCourseTop();
});

</script>

<style scoped>
.mySwiper {
  width: 100%;
  height: 300px;
  position: relative;
}

.nav-btn {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  z-index: 2;
  padding: 10px 15px;
  font-size: 18px;
  border-radius: 50%;
}

.prev-btn {
  left: -20px;
}
.banner_product{
  max-height: 500px;
  -webkit-box-reflect: below -2px linear-gradient(transparent, transparent,rgba(0, 0, 0, 0.1), rgba(0, 0, 0, 0.8)30%);
}

.next-btn {
  right: -20px;
}
.mySwiper {
  height: auto !important;
}

.mySwiper .swiper-wrapper {
  height: auto !important;
  align-items: stretch; 
}

.mySwiper .swiper-slide {
  height: auto !important;
  display: flex;
  flex-direction: column; 
}

.form-control:focus {
    color: var(--bs-body-color);
    border-color: var(--green-primary);
    outline: 0;
    box-shadow: none;
}

</style>
