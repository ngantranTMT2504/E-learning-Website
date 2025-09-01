<template>
  <div class="card mb-3 overflow-hidden">
    <RouterLink :to="`course-detail/${props.slug}`" class="img">
      <img class="img-fluid"
        :src="props.image"
        alt="img">
      <span class="price">
        <small>{{ props.nameType }}</small>
      </span>
    </RouterLink>
    <div class="text p-4">
      <h3 class="text-truncate" 
                                tabindex="0" 
                                data-bs-toggle="popover" 
                                :data-bs-content="props.title" >
      <RouterLink  :to="`course-detail/${props.slug}`" >{{ props.title }}</RouterLink>
      </h3>
      <p class="advisor text-truncate">{{ props.nameTeacher }}</p>
      <ul class="d-flex justify-content-between align-items-center">
        <li><RatingCustom :rating="props.rating"/></li>
        <li v-if="props.price" class="price">{{ formatPrice(props.price) }}</li>
        <li v-else><small>Miễn phí</small></li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import RatingCustom from './RatingCustom.vue';
import formatPrice from '@/utils/formatPrice';
import { Popover } from 'bootstrap/dist/js/bootstrap.bundle';
  const props = defineProps({
    title: {
        type: String,
        required: true,
        default: ''
    },
    image: {
        type: String,
        required: true
    },
    nameType: {
        type: String,
        required: true,

    },
    nameTeacher: {
        type: String,
        required: true
    },
    rating: {
        type: Number,
        default: 0
    },
    price: {
        type: Number,
        default: 0
    },
    slug: {
        type: String,
        required: true
    },
  });

//bật popover
// const titleEl = ref(null);
// onMounted(() => {
//   if(titleEl.value){
//     const isTruncated = titleEl.value.offsetWidth < titleEl.value.scrollWidth;
//     if (isTruncated) {
//       new Popover(titleEl.value, {
//         trigger: 'hover focus',
//         placement: 'auto'
//       });
//     }
//   }
// });

  
</script>

<style scoped>

.card{
  transition: all 0.3s ease;
  box-shadow: 0px 10px 23px -8px rgba(0, 0, 0, 0.11); 
  border: none;
}

.card:hover {
  transform: translateY(-5px) ;
  box-shadow: 0px 10px 23px -8px rgba(0, 0, 0, 0.5); 

}

.img {
  display: block;
  width: 100%;
  height: 200px;
  position: relative;
  z-index: 1;
  background-size: cover;
}

.img img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
  position: absolute;
  top: 0;
  left: 0;
}
.img .price {
  position: absolute;
  bottom: -8px;
  left: 20px;
  display: inline-block;
  background: var(--green-primary);
  color: var(--white-primary);
  padding: 2px 10px;
  font-size: 16px;
  font-weight: 500;
  -moz-transition: all 0.3s ease;
  -o-transition: all 0.3s ease;
  -webkit-transition: all 0.3s ease;
  -ms-transition: all 0.3s ease;
  transition: all 0.3s ease;
}

 .img .price small {
  display: -webkit-box;
  -webkit-line-clamp: 1;
  line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: 9rem;
}

 .img .price:after,
 .img .price:before {
  position: absolute;
  bottom: 0;
  content: '';
}

 .img .price:after {
  right: -8px;
  width: 0;
  height: 0;
  border-style: solid;
  border-width: 8px 8px 0 0;
  border-color: var(--green-primary) transparent transparent transparent;
}

 .img .price:before {
  width: 0;
  left: -8px;
  height: 0;
  border-style: solid;
  border-width: 0 8px 8px 0;
  border-color: transparent var(--green-primary) transparent transparent;
}
.text .advisor {
  font-size: 13px;
  text-transform: uppercase;
  font-weight: 500;
}

 .text .advisor span {
  color: var(--green-primary);
}
.text h3 {
  font-size: 20px;
  margin-bottom: 10px;
  font-weight: 500;
}
.text ul {
  margin: 0;
  padding: 10px 0 0 0;
  border-top: 1px solid rgba(0, 30, 29, 0.5);
}

.text ul li {
  display: inline-block;
  color: var(--green-text);
  font-weight: 600;
}

 .text ul li span {
  color: var(--green-primary);
  margin-right: 10px;
  font-size: 20px;
  line-height: 1;
}

 .text ul li.price {
  color: var(--pink-rose);
}
</style>