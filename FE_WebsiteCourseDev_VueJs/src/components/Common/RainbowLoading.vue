<template>
  <div class="d-flex justify-content-center align-items-end gap-2 rainbow-spinner-wrapper">
    <div class="spinner-grow text-loading bounce-spinner bounce-delay-1" role="status"></div>
    <div class="spinner-grow text-loading bounce-spinner bounce-delay-2" role="status"></div>
    <div class="spinner-grow text-loading bounce-spinner bounce-delay-3" role="status"></div>
    <div class="spinner-grow text-loading bounce-spinner bounce-delay-4" role="status"></div>
    <div class="spinner-grow text-loading bounce-spinner bounce-delay-5" role="status"></div>
    <div class="spinner-grow text-loading bounce-spinner bounce-delay-6" role="status"></div>
    <div class="spinner-grow text-loading bounce-spinner bounce-delay-7" role="status"></div>
  </div>
</template>

<style scoped>
@keyframes bounceInOrder {
  0%, 100% { transform: translateY(0); opacity: 0.5; }
  50% { transform: translateY(-10px); opacity: 1; }
}

.bounce-spinner {
  animation: bounceInOrder 1.2s cubic-bezier(0.42, 0.0, 0.58, 1.0) infinite;
}

.bounce-delay-1 { animation-delay: 0s; }
.bounce-delay-2 { animation-delay: 0.15s; }
.bounce-delay-3 { animation-delay: 0.3s; }
.bounce-delay-4 { animation-delay: 0.45s; }
.bounce-delay-5 { animation-delay: 0.6s; }
.bounce-delay-6 { animation-delay: 0.75s; }
.bounce-delay-7 { animation-delay: 0.9s; }

.rainbow-spinner-wrapper {
  min-height: 50px;
}

.spinner-grow {
  width: 1.5rem;
  height: 1.5rem;
}

.text-loading {
  background: linear-gradient(135deg, #004643, #00B99B);
  border-radius: 50%;
}


</style>
