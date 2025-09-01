<template>
    <div class="star-display d-flex justify-content-center align-items-center">
        <div class="position-relative">
            <div class="stars gray d-flex align-content-center">
                <i v-for="n in 5" :key="n" class="bi bi-star-fill"></i>
            </div>
            <div class="stars gold d-flex align-content-center" :style="{ width: ratingWidth }">
                <i v-for="n in 5" :key="n" class="bi bi-star-fill"></i>
            </div>
        </div>
        <div class="rating ms-1 d-flex align-content-center">
            <span class="fs-8">{{ props.rating }}</span>
        </div>
    </div>
</template>

<script setup>
const props = defineProps({
    rating: {
        type: Number,
        required: true,
        default: 0
    }
})
//tinh so sao to mau
const ratingWidth = `${Math.min(Math.max(props.rating, 0), 5) * 20}%`
</script>

<style scoped>
.star-display {
    position: relative;
    display: inline-block;
    font-size: 1.8rem;
    line-height: 1;
}

.stars {
    white-space: nowrap;
    user-select: none;
}

.stars i {
    display: inline-block;
    font-size: 0.8rem;
}

.stars.gray {
    color: var(--light-gray);
}

.stars.gold {
    color: var(--bare-orange);
    position: absolute;
    top: 0;
    left: 0;
    overflow: hidden;
    pointer-events: none;
}
.span{
    color: var(--green-primary);
}
</style>
