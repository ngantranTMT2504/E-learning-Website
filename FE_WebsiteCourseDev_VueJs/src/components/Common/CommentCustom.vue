<template>
    <div class="d-flex align-items-start mb-4">
        <!-- Avatar -->
        <img :src="avatarUrl" class="me-3 rounded-circle avatar" alt="avatar" />
        <div class="flex-grow-1">
            <!-- Tên & thời gian -->
            <div class="fw-semibold fs-7">{{ author }}</div>
            <div class="text-muted small mb-2">{{ timeAgo(date) }}</div>
            <p class="mb-2 fs-6">{{ content }}</p>

            <!-- Like/Dislike/Reply -->
            <div class="d-flex gap-3 text-muted small mb-2">
                <span @click="handleLike">
                    <i :class="['me-1', 'bi', userLiked ? 'bi-hand-thumbs-up-fill text-primary' : 'bi-hand-thumbs-up']"></i>
                    {{ likesCount }}
                </span>
                <span @click="handleDislike">
                    <i :class="['me-1', 'bi', userDisliked ? 'bi-hand-thumbs-down-fill text-danger' : 'bi-hand-thumbs-down']"></i>
                    {{ dislikesCount }}
                </span>
                <button class="btn p-0 fs-7" @click="toggleFormReply">
                    <i class="bi bi-chat me-1"></i>Trả lời
                </button>
            </div>

            <!-- Form trả lời -->
            <CommentFormCustom
                v-if="showFormReply"
                @close="showFormReply = false"
                @submit-reply="handleReply"
            />

            <!-- Nút hiển thị phản hồi -->
            <button
                v-if="replies.length > 0"
                class="btn p-0"
                @click="toggleReply"
            >
                {{ showReply ? 'Ẩn phản hồi' : `${replies.length} phản hồi` }}
                <i :class="showReply ? 'bi bi-chevron-up' : 'bi bi-chevron-down'"></i>
            </button>

            <!-- Danh sách phản hồi lồng nhau -->
            <div v-if="showReply" class="replies mt-3">
                <CommentCustom
                    v-for="comment in visibleReplies"
                    :key="comment.id"
                    :id="comment.id"
                    :author="comment.email"
                    :date="comment.createdDate"
                    :content="comment.comment"
                    :likes="comment.countLike"
                    :dislikes="comment.countDislike"
                    :avatarUrl="comment.accountAvarta"
                    @reply="emit('reply', $event)"
                    @update="emit('update', $event)"
                />
                <!-- Nút xem thêm / ẩn bớt -->
                <button
                    v-if="replies.length > maxVisible"
                    class="btn btn-link p-0 mt-2"
                    @click="toggleExpand"
                >
                    {{ expanded ? 'Ẩn bớt' : 'Xem thêm phản hồi' }}
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from "vue";
import CommentFormCustom from "./CommentFormCustom.vue";
import timeAgo from "@/utils/timeAgo";

const props = defineProps({
    id: { type: Number, required: true },
    author: { type: String, required: true },
    date: { type: String, required: true },
    content: { type: String, required: true },
    likes: { type: Number, default: 0 },
    dislikes: { type: Number, default: 0 },
    replies: { type: Array, default: () => [] },
    avatarUrl: {
        type: String,
        default: "https://hoseiki.vn/wp-content/uploads/2025/03/avatar-mac-dinh-4.jpg"
    }
});

const emit = defineEmits(["update", "reply"]);

const showFormReply = ref(false);
const showReply = ref(false);
const expanded = ref(false);
const maxVisible = 3;

const visibleReplies = computed(() =>
    expanded.value ? props.replies : props.replies.slice(0, maxVisible)
);

// Toggle like/dislike state
const likesCount = ref(props.likes);
const dislikesCount = ref(props.dislikes);
const userLiked = ref(false);
const userDisliked = ref(false);

const toggleFormReply = () => {
    showFormReply.value = !showFormReply.value;
};

const toggleReply = () => {
    showReply.value = !showReply.value;
};

const toggleExpand = () => {
    expanded.value = !expanded.value;
};

const handleReply = (content) => {
    emit("reply", { parentId: props.id, content });
    showFormReply.value = false;
};

const handleLike = () => {
    if (userLiked.value) {
        likesCount.value--;
        userLiked.value = false;
        emit("update", { id: props.id, type: "unlike" });
    } else {
        likesCount.value++;
        if (userDisliked.value) {
            dislikesCount.value--;
            userDisliked.value = false;
        }
        userLiked.value = true;
        emit("update", { id: props.id, type: "like" });
    }
};

const handleDislike = () => {
    if (userDisliked.value) {
        dislikesCount.value--;
        userDisliked.value = false;
        emit("update", { id: props.id, type: "undislike" });
    } else {
        dislikesCount.value++;
        if (userLiked.value) {
            likesCount.value--;
            userLiked.value = false;
        }
        userDisliked.value = true;
        emit("update", { id: props.id, type: "dislike" });
    }
};
</script>

<style scoped>
.avatar {
    max-width: 35px;
}
span {
    cursor: pointer;
}
.replies {
    margin-left: 45px;
    border-left: 2px solid #ddd;
    padding-left: 10px;
}
</style>
