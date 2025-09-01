<template>
    <section class="container-lg container-fluid py-3">
        <div class="row">
            <div class="col-lg-9 pe-lg-5">
                <div class="row banner py-5 px-3 text-white border rounded overflow-hidden">
                    <div class="col-lg-5 position-relative">
                        <div class="w-100 h-100 rounded overflow-hidden">
                            <img :src="course.image" class="img-fluid rounded" alt="...">
                        </div>
                    </div>
                    <div class="col-lg-7">
                        <div class="px-1">
                            <h2 class="pt-3 p-lg-0">{{ course.title }}</h2>
                            <p class="description">{{ course.discription }}</p>
                            <div class="d-lg-none py-2">
                                <h4 class="p-2" :class="{ 'd-none': course.price }">Miễn phí</h4>
                                <h4 class="text-white" :class="{ 'd-none': !course.price }">{{
                                    formatPrice(course.discount) ? formatPrice(course.price - course.discount) :
                                    formatPrice(course.price) }}</h4>
                                <h6 v-if="course.discount" class="text-white" :class="{ 'd-none': !course.price }"><del>{{
                                        formatPrice(course.price) }}</del></h6>
                                <RouterLink :class="{ 'd-none': course.price }" class="btn btn-success" to="#">Đăng ký miễn
                                    phí</RouterLink>
                                <div class="d-flex py-2">
                                    <span class="me-3"><i class="bi bi-list-ul pe-1"></i>{{ course.lessonTotal }} bài
                                        giảng</span>
                                    <span><i class="bi bi-star pe-1"></i>{{ course.averageRating }} (đánh giá)</span>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex px-1 pe-5">
                            <span class="me-3 fs-7"><strong>Tác giả: </strong> {{ course.teacherName }}</span>
                            <span class="me-3 fs-7 d-none"><i class="bi bi-people-fill me-1"></i><strong>Học
                                    viên:</strong>49999</span>
                            <RatingCustom v-if="course.averageRating !== undefined" :rating="course.averageRating" />
                        </div>
                    </div>
                </div>
                <div class="row py-3">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs mb-3 rounded shadow-sm" role="tablist">
                        <li class="nav-item" role="presentation">
                            <a class="nav-link active" data-bs-toggle="tab" href="#home" role="tab">Giới thiệu</a>
                        </li>
                        <li class="nav-item" role="presentation">
                            <a class="nav-link" data-bs-toggle="tab" href="#menu1" role="tab">Bình luận</a>
                        </li>
                    </ul>
                    <!-- Tab panes -->
                    <div class="tab-content p-4 border rounded shadow-sm bg-white">
                        <div class="tab-pane fade show active" id="home" role="tabpanel">
                            <h4 class="p-2 fw-bold">Giới thiệu</h4>
                            <p class="p-2">{{ course.discription }}</p>
                            <h4 class="p-2 fw-bold">Nội dung khóa học</h4>
                            <ul class="d-flex px-3">
                                <li class="fs-7 d-none"><i class="bi bi-dot"></i></li>
                                <li class="fs-7"><i class="bi bi-dot"></i>{{ course.lessonTotal }} bài giảng</li>
                            </ul>
                            <ul class="list-group list-group-flush px-2 mb-2">
                                <li v-for="lesson in lessons" :key="lesson.id"
                                    class="list-group-item py-3 border-bottom">
                                    <p class="mb-0">
                                        <a class="text-decoration-none text-dark d-flex justify-content-between align-items-center fw-medium"
                                            data-bs-toggle="collapse" :href="'#collapse' + lesson.id" role="button"
                                            :aria-controls="'collapse' + lesson.id">
                                            <span class="text-truncate">{{ lesson.lessonOrder }}. {{ lesson.title
                                                }}</span>
                                            <i class="bi bi-chevron-down rotate-icon transition"
                                                :class="'icon-' + lesson.id"></i>
                                        </a>
                                    </p>
                                    <div class="collapse mt-2" :id="'collapse' + lesson.id"
                                        :class="'collapse-box-' + lesson.id">
                                        <div class="ps-4 pt-2 border-start border-2 border-success">
                                            <p class="fs-6 mb-1 text-muted">
                                                <i class="bi bi-play-btn pe-1"></i>{{ lesson.title }}
                                            </p>
                                            <p v-if="lesson.exerciseUrl" class="fs-6 mb-0 text-muted">
                                                <i class="bi bi-journal-bookmark pe-1"></i> Tài liệu
                                            </p>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                            <div class="p-4">
                                <h4 class=" fw-bold">Đánh giá</h4>
                                <h6 class="pb-3"><i class="bi bi-star-fill pe-1"></i>{{ course.averageRating }} xếp hạng
                                    khóa học</h6>
                                <div v-for="item in ratings" :key="item.id" class="mb-4">
                                    <div class="d-flex align-items-start">
                                        <div class="me-3">
                                            <img :src="item.studentAvatar || 'https://hoseiki.vn/wp-content/uploads/2025/03/avatar-mac-dinh-4.jpg'"
                                                alt="avatar" class="avatar rounded-circle" />
                                        </div>
                                        <div class="flex-grow-1">
                                            <div class="d-flex justify-content-between align-items-center">
                                                <h6 class="mb-0">{{ item.studentName || item.studentEmail }}</h6>
                                                <small class="text-muted">{{ formatDate(item.createdDate) }} · {{
                                                    timeAgo(item.createdDate) }}</small>
                                            </div>
                                            <div class="mb-1">
                                                <i v-for="index in item.rating" :key="index"
                                                    class="bi bi-star-fill fs-7"></i>
                                            </div>
                                            <p class="mb-0">{{ item.content }}</p>
                                        </div>
                                    </div>
                                    <hr class="my-3" />
                                </div>
                                <div class="d-flex justify-content-start">
                                    <a class="btn btn-link text-success" v-if="ratings.length < totalRating" @click="loadMoreRatings">
                                        Hiện thêm đánh giá
                                    </a>

                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="menu1" role="tabpanel">
                            <!-- Form bình luận chính -->
                            <div class="comment-box mb-4 d-flex">
                                <img src="https://hoseiki.vn/wp-content/uploads/2025/03/avatar-mac-dinh-4.jpg"
                                    alt="Avatar" class="rounded-circle me-3"
                                    style="width: 40px; height: 40px; object-fit: cover;" />
                                <div class="flex-grow-1">
                                    <textarea class="form-control" rows="3" placeholder="Viết bình luận..."></textarea>
                                    <div class="d-flex justify-content-end gap-2 mt-2">
                                        <button type="button" class="btn btn-light btn-sm">Hủy</button>
                                        <button type="button" class="btn btn-primary btn-sm">Bình luận</button>
                                    </div>
                                </div>
                            </div>
                            <!-- Danh sách bình luận -->
                            <!-- Bình luận cha -->
                            <div class="bg-light rounded p-3 mb-4">
                                <div v-for="item in comments" :key="item.id" class="d-flex align-items-start mb-4">
                                    <img :src="item.avatar || 'https://hoseiki.vn/wp-content/uploads/2025/03/avatar-mac-dinh-4.jpg'"
                                        class="me-3 rounded-circle avatar" alt="avatar" />
                                    <div class="flex-grow-1">
                                        <div class="fw-semibold fs-7">{{ item.email }}</div>
                                        <div class="text-muted small mb-2">{{ timeAgo(item.date) }}</div>
                                        <p class="mb-2 fs-6">{{ item.content }}</p>
                                        <div class="d-flex gap-3 text-muted small mb-2">
                                            <span><i class="bi bi-hand-thumbs-up me-1"></i>{{ item.likes }}</span>
                                            <span><i class="bi bi-hand-thumbs-down me-1"></i>{{ item.dislikes }}</span>
                                            <span class="cursor-pointer"><i class="bi bi-chat me-1"></i>Trả lời</span>
                                        </div>

                                        <!-- Nút hiển thị phản hồi -->
                                        <button class="btn p-0" @click="toggleReply(item.id)">
                                            {{ item.replies?.length || 0 }} phản hồi
                                        </button>

                                        <!-- Bình luận con -->
                                        <div v-if="visibleReplies.includes(item.id)" v-for="reply in item.replies"
                                            :key="reply.id" class="d-flex align-items-start mt-3 ms-5">
                                            <img :src="reply.avatar" class="me-3 rounded-circle"
                                                style="width: 36px; height: 36px; object-fit: cover;" alt="avatar" />
                                            <div class="flex-grow-1">
                                                <div class="fw-semibold fs-7">{{ reply.email }}</div>
                                                <div class="text-muted small mb-2">{{ timeAgo(reply.date) }}</div>
                                                <p class="mb-2 fs-6">{{ reply.content }}</p>
                                                <div class="d-flex gap-3 text-muted small mb-2">
                                                    <span><i class="bi bi-hand-thumbs-up me-1"></i>{{ reply.likes
                                                    }}</span>
                                                    <span><i class="bi bi-hand-thumbs-down me-1"></i>{{ reply.dislikes
                                                    }}</span>
                                                    <span class="cursor-pointer"><i class="bi bi-chat me-1"></i>Trả
                                                        lời</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 position-relative d-none d-lg-block">
                <div ref="stickyBox" class="banner position-sticky p-3 rounded d-flex flex-column text-white">
                    <h4 class="p-2" :class="{ 'd-none': course.price }">Miễn phí</h4>
                    <h4 class="text-white" :class="{ 'd-none': !course.price }">{{ formatPrice(course.price -
                        course.discount) }}</h4>
                    <h6 v-if="course.discount" class="text-white" :class="{ 'd-none': !course.price }"><del>{{
                            formatPrice(course.price) }}</del></h6>
                    <hr>
                    <div class="p-2">
                        <h5>Thông tin khóa học</h5>
                        <div class="d-flex flex-column me-auto">
                            <ul class="p-0">
                                <li class="fs-7"><i class="bi bi-book pe-2"></i>{{ course.lessonTotal }} bài học</li>
                                <li class="fs-7"><i class="bi bi-award pe-2"></i>Truy cập trọn đời</li>
                            </ul>
                        </div>
                    </div>
                    <RouterLink :to="`/payment/${course.slug}`" class="btn btn-success fw-semibold d-block mt-auto">Đăng ký ngay</RouterLink>
                </div>
            </div>
        </div>
    </section>

</template>

<script setup>
import RatingCustom from '@/components/Common/RatingCustom.vue';
import formatPrice from '@/utils/formatPrice';
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { useRoute } from 'vue-router';
import { getList } from '@/services/apiMiddlewareV2';
import timeAgo from '@/utils/timeAgo';
import formatDate from '@/utils/formatDate';
const route = useRoute();
const slugCourse = ref(route.params.slug);
const APICourses = "/api/course";
const course = ref({});
const getCourse = async () => {
    try {
        const filter = {
            status: true,
            slug: slugCourse.value,
        }
        const response = await getList(APICourses, filter);
        course.value = response.data[0];
    } catch (error) {
        console.log(error);
    }
}
const lessons = ref([]);
const APILesson = "/api/lesson"
const getLesson = async () => {
    try {
        const filter = {
            status: true,
            courseIds: course.value.id,
            sort: 'lessonOrder,asc'
        }
        const response = await getList(APILesson, filter);
        lessons.value = response.data;
    } catch (error) {
        console.log(error);
    }
}
const ratings = ref([]);
const APIRating = "/api/ratings";
const sizeRating = ref(10);
const totalRating = ref(0);
const getListRaing = async () => {
    try {
        const filter = {
            courseIds: course.value.id,
            sort: 'createdDate,desc',
            size: sizeRating.value
        }
        const response = await getList(APIRating, filter);
        ratings.value = response.data;
        totalRating.value = response.totalItems;
    } catch (error) {
        console.log(error);
    }
}
const loadMoreRatings = async () => {
    sizeRating.value += 2; // mỗi lần load thêm 2 đánh giá
    await getListRaing();
};

onMounted(async () => {
    await getCourse();
    await getLesson();
    await getListRaing();
});


const comments = ref([
    {
        id: 1,
        avatar: 'https://hoseiki.vn/wp-content/uploads/2025/03/avatar-mac-dinh-4.jpg',
        email: 'tuandoan@gmail.com',
        date: '2025-07-17T20:05:00',
        content: 'quá tuyệt vời',
        likes: 1,
        dislikes: 0,
        replies: [
            {
                id: 11,
                avatar: 'https://hoseiki.vn/wp-content/uploads/2025/03/avatar-mac-dinh-4.jpg',
                email: 'nguyenvana***@gmail.com',
                date: '2025-07-18T10:30:00',
                content: 'Mình cũng thấy vậy, cảm ơn bạn đã chia sẻ.',
                likes: 1,
                dislikes: 0
            }
        ]
    },
    {
        id: 2,
        avatar: 'https://hoseiki.vn/wp-content/uploads/2025/03/avatar-mac-dinh-4.jpg',
        email: 'tuandoan@gmail.com',
        date: '2025-07-17T20:05:00',
        content: 'quá tuyệt vời',
        likes: 1,
        dislikes: 0,
        replies: [
            {
                id: 11,
                avatar: 'https://hoseiki.vn/wp-content/uploads/2025/03/avatar-mac-dinh-4.jpg',
                email: 'nguyenvana***@gmail.com',
                date: '2025-07-18T10:30:00',
                content: 'Mình cũng thấy vậy, cảm ơn bạn đã chia sẻ.',
                likes: 1,
                dislikes: 0
            }
        ]
    }
])
//an hien them comment con
const visibleReplies = ref([])
const toggleReply = (id) => {
    const index = visibleReplies.value.indexOf(id)
    if (index === -1) {
        visibleReplies.value.push(id)
    } else {
        visibleReplies.value.splice(index, 1)
    }
}

//sticky col-lg-3 
const stickyBox = ref(null);
const handleScroll = () => {
    if (!stickyBox.value) return;
    const scrollTop = window.scrollY;
    stickyBox.value.style.top = scrollTop > 0 ? '90px' : '0px';
};

onMounted(() => {
    window.addEventListener('scroll', handleScroll);
});

onBeforeUnmount(() => {
    window.removeEventListener('scroll', handleScroll);
});
</script>

<style scoped>
.banner {
    /* height: 300px; */
    background: var(--green);
}

.description {
    display: -webkit-box;
    -webkit-line-clamp: 3;
    line-clamp: 3;
    /* Giới hạn 3 dòng */
    -webkit-box-orient: vertical;
    overflow: hidden;
    text-overflow: ellipsis;
}

.banner.position-sticky {
    top: 0;
    width: 306px;
}

.rotate-icon {
    transition: transform 0.3s ease;
}

a[aria-expanded="true"] .rotate-icon {
    transform: rotate(180deg);
}

.bi.bi-star-fill {
    color: var(--bare-orange);
}

.avatar {
    max-width: 35px;
}

.nav-tabs .nav-link {
    border: none;
    border-bottom: 3px solid transparent;
    color: #555;
}

.nav-tabs .nav-link.active {
    border-bottom: 3px solid var(--green-primary);
    /* Màu xanh Bootstrap */
    background-color: transparent;
    color: var(--green-primary);
    font-weight: 500;
}

.nav-tabs .nav-link:hover {
    color: var(--green-primary);
}

.comment-box {
    display: flex;
    gap: 12px;
    align-items: flex-start;
}

.comment-avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    object-fit: cover;
}

.comment-form {
    flex-grow: 1;
}

.comment-actions {
    display: none;
    margin-top: 8px;
}

.comment-textarea:focus+.comment-actions,
.comment-textarea:not(:placeholder-shown)+.comment-actions {
    display: flex;
    justify-content: end;
    gap: 8px;
}

.form-control:focus {
    color: var(--bs-body-color);
    background-color: var(--bs-body-bg);
    border-color: var(--green-primary);
    outline: 0;
    box-shadow: none;
}
</style>