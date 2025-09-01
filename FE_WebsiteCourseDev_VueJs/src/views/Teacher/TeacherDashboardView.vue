<template>
  <div class="container-fluid py-2 px-2">
    <!-- <h2 class="mb-5 text-uppercase fw-bold text-center">Dashboard Giảng Viên</h2> -->

    <!-- Thống kê tổng quan -->
    <div class="row g-4 mb-5">
      <!-- <div class="col-12 col-sm-6 col-lg-3" v-for="(stat, index) in stats" :key="index">
        <div :class="['card shadow text-white h-100', stat.bgClass]">
          <div class="card-body text-center">
            <i :class="['fs-1', stat.icon]"></i>
            <h5 class="card-title m-0">{{ stat.title }}</h5>
            <p class="fs-2 fw-bold m-0">{{ stat.value }}</p>
            <small>{{ stat.description }}</small>
          </div>
        </div>
      </div> -->
      <div class="col-12 col-sm-6 col-lg-3">
        <div class="card card-stats mb-4 mb-xl-0">
          <div class="card-body">
            <div class="row">
              <div class="col">
                <h6 class="card-title text-uppercase text-muted mb-0">Tổng khóa học</h6>
                <span class="h3 font-weight-bold mb-0">35</span>
              </div>
              <div class="col-auto">
                <div class="icon px-2 py-1 bg-danger text-white rounded-circle shadow">
                  <i class="bi bi-journal-bookmark"></i>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-12 col-sm-6 col-lg-3">
        <div class="card card-stats mb-4 mb-xl-0">
          <div class="card-body">
            <div class="row">
              <div class="col">
                <h6 class="card-title text-uppercase text-muted mb-0">Tổng học viên</h6>
                <span class="h3 font-weight-bold mb-0">35</span>
              </div>
              <div class="col-auto">
                <div class="icon px-2 py-1 bg-danger text-white rounded-circle shadow">
                  <i class="bi bi-people"></i>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-12 col-sm-6 col-lg-3">
        <div class="card card-stats mb-4 mb-xl-0">
          <div class="card-body">
            <div class="row">
              <div class="col">
                <h6 class="card-title text-uppercase text-muted mb-0">Bài tập hôm nay</h6>
                <span class="h3 font-weight-bold mb-0">35</span>
              </div>
              <div class="col-auto">
                <div class="icon px-2 py-1 bg-danger text-white rounded-circle shadow">
                  <i class="bi bi-clipboard-check"></i>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-12 col-sm-6 col-lg-3">
        <div class="card card-stats mb-4 mb-xl-0">
          <div class="card-body">
            <div class="row">
              <div class="col">
                <h6 class="card-title text-uppercase text-muted mb-0">Việc cần làm</h6>
                <span class="h3 font-weight-bold mb-0">35</span>
              </div>
              <div class="col-auto">
                <div class="icon px-2 py-1 bg-danger text-white rounded-circle shadow">
                  <i class="bi bi-list-task"></i>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Biểu đồ thống kê -->
    <div class="row g-4">
      <div class="col-12 col-lg-6">
        <div class="card shadow h-100">
          <div class="card-header bg-success text-white fw-bold bg-chart-primary">Xu hướng học viên</div>
          <div class="card-body bg-chart-primary ">
            <canvas id="lineChart" class="w-100" style="min-height:300px;"></canvas>
          </div>
        </div>
      </div>
      <div class="col-12 col-lg-6">
        <div class="card shadow h-100 rounded">
          <div class="card-header bg-info text-white fw-bold bg-chart">Học viên theo khoá</div>
          <div class="card-body p-0">
            <canvas id="barChart" class="w-100 bg-chart h-100"></canvas>
          </div>
        </div>
      </div>
    </div>

    <!-- Việc cần làm -->
    <div class="card mt-5 shadow border-0">
      <div class="card-header bg-warning text-dark fw-bold">Việc cần làm</div>
      <ul class="list-group list-group-flush">
        <li class="list-group-item" v-for="task in tasks" :key="task.id">{{ task.name }}</li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import Chart from 'chart.js/auto'

const stats = ref([
  {
    title: 'Tổng khoá học',
    value: 5,
    description: 'Khoá học đang giảng dạy',
    icon: 'bi bi-journal-bookmark',
    bgClass: 'bg-primary'
  },
  {
    title: 'Tổng học viên',
    value: 128,
    description: 'Học viên đăng ký',
    icon: 'bi bi-people',
    bgClass: 'bg-success'
  },
  {
    title: 'Bài kiểm tra hôm nay',
    value: 2,
    description: 'Số bài kiểm tra đang diễn ra',
    icon: 'bi bi-clipboard-check',
    bgClass: 'bg-danger'
  },
  {
    title: 'Việc cần làm',
    value: 3,
    description: 'Tác vụ giảng viên đang chờ xử lý',
    icon: 'bi bi-list-task',
    bgClass: 'bg-warning text-dark'
  }
])

const tasks = ref([
  { id: 1, name: 'Chấm bài Laravel' },
  { id: 2, name: 'Tạo nội dung GraphQL' },
  { id: 3, name: 'Phản hồi học viên NodeJS' }
])

onMounted(() => {
  // Biểu đồ line
  new Chart(document.getElementById('lineChart'), {
    type: 'line',
    data: {
      labels: [50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150],
      datasets: [{
        label: 'Số học viên theo thời gian',
        data: [7, 8, 8, 9, 9, 9, 10, 11, 14, 14, 15],
        borderColor: '#ffffff',       // Đường trắng
        backgroundColor: '#ffffff',   // Màu điểm trắng
        tension: 0.4,
        fill: false
      }]
    },
    options: {
      responsive: true,
      animation: { duration: 1000 },
      plugins: {
        legend: {
          labels: {
            color: '#ffffff' // Màu chữ trong legend (chú thích)
          }
        },
        tooltip: {
          bodyColor: '#ffffff', // Màu chữ trong tooltip
          titleColor: '#ffffff'
        }
      },
      scales: {
        x: {
          ticks: {
            color: '#ffffff' // Màu chữ trục X
          }
        },
        y: {
          beginAtZero: true,
          ticks: {
            color: '#ffffff' // Màu chữ trục Y
          }
        }
      }
    }
  });
  new Chart(document.getElementById('barChart'), {
    type: 'bar',
    data: {
      labels: ['VueJS', 'NodeJS', 'React', 'Laravel', 'Docker'],
      datasets: [{
        label: 'Số học viên',
        data: [20, 35, 25, 30, 18],
        backgroundColor: ['#ffffff', '#ffffff', '#ffffff', '#ffffff', '#ffffff'],
        borderColor: ['#ffffff', '#ffffff', '#ffffff', '#ffffff', '#ffffff'],
        borderWidth: 1,
        borderRadius: 6 // làm tròn góc cột
      }]
    },
    options: {
      responsive: true,
      animation: { duration: 800 },
      plugins: {
        legend: {
          labels: {
            color: '#ffffff' // nếu dùng nền tối
          }
        },
        tooltip: {
          bodyColor: '#ffffff',
          titleColor: '#ffffff'
        }
      },
      scales: {
        x: {
          ticks: { color: '#ffffff' } // nếu nền tối
        },
        y: {
          beginAtZero: true,
          ticks: { color: '#ffffff' } // nếu nền tối
        }
      }
    }
  });

})
</script>

<style scoped>
.card {
  transition: transform 0.2s ease-in-out;
}

.card:hover {
  transform: scale(1.02);
}

.bg-chart {
  background: linear-gradient(135deg, #004643, #00B99B);
}

.bg-chart-primary {
  background: linear-gradient(135deg, #001F1F 0%, #004643 50%, #00EAD3 100%);
}
</style>
