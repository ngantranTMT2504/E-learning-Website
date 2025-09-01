<template>
  <div class="circular-progress" :style="{ width: size + 'px', height: size + 'px' }">
    <svg :width="size" :height="size">
      <circle
        class="bg"
        :r="radius"
        :cx="center"
        :cy="center"
        stroke="#e9ecef"
        :stroke-width="stroke"
        fill="none"
      />
      <circle
        class="progress"
        :r="radius"
        :cx="center"
        :cy="center"
        :stroke="color"
        :stroke-width="stroke"
        fill="none"
        :stroke-dasharray="circumference"
        :stroke-dashoffset="offset"
        stroke-linecap="round"
        :transform="`rotate(-90 ${center} ${center})`"
      />
      <text
        :x="center"
        :y="center"
        text-anchor="middle"
        dy=".3em"
        class="progress-text"
      >{{ progress }}%</text>
    </svg>
  </div>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  progress: { type: Number, default: 75 },
  color: { type: String, default: '#0d6efd' },
  size: { type: Number, default: 50 },
  stroke: { type: Number, default: 4 }
})

const center = computed(() => props.size / 2)
const radius = computed(() => center.value - props.stroke / 2)
const circumference = computed(() => 2 * Math.PI * radius.value)
const offset = computed(() => circumference.value * (1 - props.progress / 100))
</script>

<style scoped>
.circular-progress {
  display: inline-block;
}
.progress {
  transition: stroke-dashoffset 0.4s ease-in-out;
}
.progress-text {
  font-size: calc(0.25 * var(--size, 50px));
  font-weight: bold;
  fill: #fff /* màu chữ */
}
</style>
