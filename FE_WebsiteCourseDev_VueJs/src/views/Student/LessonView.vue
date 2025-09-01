<template>
    <div class="bg-dark d-flex py-3 container-fluid justify-content-between align-items-center">
        <RouterLink :to="`/course-detail/${course.slug}`" class="text-white" to="#"><i class="bi bi-chevron-left pe-1"></i>{{ course.title }}</RouterLink>
        <div class="text-white text-capitalize d-flex align-items-center">
            <ProgressbarCircle :progress="20" size="40" color="green" class="me-2" />
            <span class="fs-8">{{ course.lessonTotal }} bài học</span>
        </div>
    </div>
    <section class="container-fluid pt-3">
        <div class="row">
            <div class="col-lg-8 mb-4">
                <div class="">
                    <video :src="lesson.videoUrl"
                        controls autoplay muted controlsList="nodownload" disablePictureInPicture width="100%">
                        Trình duyệt của bạn không hỗ trợ thẻ video.
                    </video>
                </div>
                <div class="row">
                    <div class="col-lg-10 col-md-9">
                        <h5 class="py-2">{{ lesson.title }} </h5>
                    </div>
                    <div class="col mb-3 mb-lg-0">
                        <button class="btn me-3 border" type="button" data-bs-toggle="offcanvas"
                            data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">
                            <i class="fs-7 bi bi-chat pe-1"></i>Hỏi đáp
                        </button>
                        <RouterLink to="#" class=""><i class="bi bi-flag"></i></RouterLink>
                        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight"
                            aria-labelledby="offcanvasRightLabel">
                            <div class="offcanvas-header">
                                <button type="button" class="btn-close text-reset me-1" data-bs-dismiss="offcanvas"
                                    aria-label="Close"></button>
                            </div>
                            <div class="offcanvas-body">
                                <!-- Form bình luận chính -->
                                <div class="comment-box mb-4 d-flex">
                                    <img src="https://hoseiki.vn/wp-content/uploads/2025/03/avatar-mac-dinh-4.jpg"
                                        alt="Avatar" class="rounded-circle me-3" />
                                    <div class="flex-grow-1">
                                        <CommentFormCustom @submit-reply="handleComment"/>
                                    </div>
                                </div>
                                <div class="bg-light rounded p-3 mb-4">
                                        <CommentCustom
                                            v-for="comment in comments"
                                            :key="comment.id"
                                            :id="comment.id"
                                            :author="comment.email"
                                            :date="comment.createdDate"
                                            :content="comment.comment"
                                            :likes="comment.countLike"
                                            :dislikes="comment.countDislike"
                                            :avatar-url="comment.accountAvarta"
                                            :replies="collectAllChildren(comment.children)"
                                            @reply="handleReply"
                                            @update="handleUpdateReaction"
                                            />
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
                <p><i class="bi bi-fast-forward pe-1"></i>Tài liệu: {{ lesson.exerciseUrl }}</p>
                <p>{{ lesson.discription }}</p>
            </div>
            <div class="col-lg-4 mb-3 me-sm-0">
                <h6 class="fw-bold pb-2">Nội dung bài học</h6>
                <div class="slide-lesson overflow-y-auto">
                    <div class="accordion" id="accordionLessons">
                        <div class="accordion-item" v-for="(lesson, index) in lessons" :key="index">
                            <h2 class="accordion-header" :id="'heading' + index">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    :data-bs-target="'#collapse' + index" aria-expanded="false"
                                    :aria-controls="'collapse' + index">
                                    {{ lesson.title }}
                                </button>
                            </h2>
                            <div :id="'collapse' + index" class="accordion-collapse collapse"
                                :aria-labelledby="'heading' + index">
                                <div class="accordion-body d-flex">
                                    <i class="bi bi-check2-circle me-2"></i>
                                    <RouterLink :class="{'bg-light d-block border-bottom': lesson.id == route.params.id}" :to="`${lesson.id}`"><i class="bi bi-play-circle pe-1"></i>{{
                                        lesson.discription }}</RouterLink>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center light-gray position-fixed bottom-0 py-2">
                <ButtonCustom @click="prevLesson" class="btn-outline-success me-2" :type="'btn-bare'"><i
                        class="bi bi-arrow-left-short pe-1 my-auto"></i>Bài
                    trước</ButtonCustom>
                <ButtonCustom  @click="nextLesson" >Bài tiếp theo<i class="bi bi-arrow-right-short ps-1 my-auto"></i></ButtonCustom>
            </div>
        </div>
    </section>
</template>

<script setup>
import CommentFormCustom from "@/components/Common/CommentFormCustom.vue";
import ButtonCustom from "@/components/Common/ButtonCustom.vue";
import ProgressbarCircle from "@/components/Common/ProgressbarCircle.vue";
import { ref, onMounted, watch, reactive } from "vue";
import CommentCustom from "@/components/Common/CommentCustom.vue";
import { useRoute, useRouter } from "vue-router";
import { getById, getList, createItem, updateItem} from "@/services/apiMiddlewareV2";

const route = useRoute();
const router = useRouter();
const course = ref({});
const APICourse = "/api/course";
const getCourse = async () =>{
    try {
        const filter = {
            slug: route.params.slug
        }
        const response = await getList(APICourse, filter);
        course.value = response.data[0];
    } catch (error) {
        console.log(error);
    }
}
const lessons = ref([]);
const APILesson = "/api/lesson"
const getLessons = async () => {
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
const lesson = ref({});
const getLesson = async () =>{
    try {
        const response = await getById(`${APILesson}/${route.params.id}`);
        lesson.value = response;
    } catch (error) {
        console.log(error);
    }
}
watch(
  () => route.params.id,
  () => {
    getLesson();
  }
);
const API_Comment = "/api/comments";
const comments = ref([]);
const fetchComments = async () =>{
    try {
        const filter = {
            lessonIds: route.params.id,
            sort: 'createdDate,desc'
        }
        const response = await getList(API_Comment, filter);
        comments.value = response.data;
    } catch (error) {
        console.log(error);
    }
}
const collectAllChildren = (comments = []) => {
  const result = [];

  const traverse = (list) => {
    list.forEach((comment) => {
      result.push(comment);
      if (comment.children && comment.children.length > 0) {
        traverse(comment.children); 
      }
    });
  };
  traverse(comments);
  return result;
};
const accountId = 1;
const comment = reactive({
    comment: '',
    parentId: null,
    countLike: 0,
    countDislike: 0,
    accountId: accountId,
    lessonId: route.params.id,
})
const handleComment = async (content) =>{
    try {
        comment.comment = content;
        const response = await createItem(API_Comment, comment);
        comment.comment = '';
        fetchComments();
    } catch (error) {
        console.log(error);
    }
}
const handleReply = async ({ parentId, content }) => {
    try {
        comment.parentId = parentId
        comment.comment = content
        const response = await createItem(API_Comment, comment);
        fetchComments();
        comment.parentId = null;
        comment.comment = '';
    } catch (error) {
        console.log(error);
    }
};
const fetchComment = async (id) => {
    try {
        console.log(id);
        
        return await getById(`${API_Comment}/${id}`);
    } catch (error) {
        console.log(error);
    }
};
const handleUpdateReaction = async ({ id, type }) => {
    const comment = await fetchComment(id);
    console.log(comment);
    
    switch (type) {
        case 'like':
            comment.countLike = comment.countLike + 1;
            break;
        case 'unlike':
            comment.countLike = comment.countLike - 1;
            break;
        case 'dislike':
            comment.countDislike = comment.countDislike + 1;
            break;
        case 'undislike':
            comment.countDislike = comment.countDislike - 1;
            break;
    }
    try {
        await updateItem(`${API_Comment}/${id}`, comment);
        fetchComments();
    } catch (error) {
        console.log(error);
    }

};

const getCurrentLesson = () => {
  return lessons.value.findIndex((t) => t.id == route.params.id);
};
const nextLesson = () => {
    const currentIndex = getCurrentLesson();
    const nextIndex = currentIndex + 1;
    lesson.value = lessons.value[nextIndex < lessons.value.length ? nextIndex : lessons.value.length - 1];
    router.push(`/lesson/${route.params.slug}/${lesson.value.id}`);
};
const prevLesson = () => {
    const currentIndex = getCurrentLesson();
     const prevIndex = currentIndex - 1;
    lesson.value = lessons.value[prevIndex >= 0 ? prevIndex : 0];
    router.push(`/lesson/${route.params.slug}/${lesson.value.id}`);
};


onMounted( async () => {
  await getCourse();
  await getLessons();
  await getLesson();
  await fetchComments();
});

const activeReplyId = ref(null);
</script>

<style scoped>
.accordion-button {
    font-size: 14px;
    padding: 8px 12px;
}

.accordion-body {
    font-size: 13px;
    padding: 10px;
}

.accordion-button:not(.collapsed) {
    background-color: white;
    box-shadow: none;
}

.accordion-button:focus {
    z-index: 3;
    outline: 0;
    box-shadow: none;
}

.slide-lesson {
    height: calc(100vh - 125px);
    z-index: 2;
}

.light-gray {
    background: var(--light-gray);
    z-index: 999;
    color: var(--light-gray);
}

#offcanvasRight {
    width: 500px !important;
}

.comment-avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    object-fit: cover;
}

.comment-box img {
    width: 40px;
    height: 40px;
    object-fit: cover;
}

.green-primary {
    background: var(--green-primary);
}
video{
    width: 100%;
    height: 494.925px;
}

</style>
