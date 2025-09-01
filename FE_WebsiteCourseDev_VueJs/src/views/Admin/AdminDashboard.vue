<template>
    <section class="container">
        <div class="row">
            <div class="col-xl-3 col-lg-6">
                <div class="card card-stats mb-4 mb-xl-0">
                    <div class="card-body">
                        <div class="row">
                            <div class="col">
                                <h6 class="card-title text-uppercase text-muted mb-0">Truy cập</h6>
                                <span class="h3 font-weight-bold mb-0">350,897</span>
                            </div>
                            <div class="col-auto">
                                <div class="icon px-2 py-1 bg-danger text-white rounded-circle shadow">
                                    <i class="bi bi-bar-chart-line"></i>
                                </div>
                            </div>
                        </div>
                        <p class="mt-3 mb-0 text-muted text-sm">
                            <span class="text-success me-2"><i class="bi bi-arrow-up-short"></i> 3.48%</span>
                            <span class="text-nowrap">Kể từ tháng trước</span>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-lg-6">
                <div class="card card-stats mb-4 mb-xl-0">
                    <div class="card-body">
                        <div class="row">
                            <div class="col">
                                <h6 class="card-title text-uppercase text-muted mb-0">Tài khoản mới</h6>
                                <span class="h3 font-weight-bold mb-0">3,897</span>
                            </div>
                            <div class="col-auto">
                                <div class="icon px-2 py-1 bg-info text-white rounded-circle shadow">
                                    <i class="bi bi-pie-chart-fill"></i>
                                </div>
                            </div>
                        </div>
                        <p class="mt-3 mb-0 text-muted text-sm">
                            <span class="text-success me-2"><i class="bi bi-arrow-up-short"></i> 3.48%</span>
                            <span class="text-nowrap">Kể từ tháng trước</span>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-lg-6">
                <div class="card card-stats mb-4 mb-xl-0">
                    <div class="card-body">
                        <div class="row">
                            <div class="col overflow-hidden">
                                <h6 class="card-title text-uppercase text-muted mb-0">Doanh thu</h6>
                                <span class="h3 font-weight-bold mb-0 text-truncate">{{ formatPrice(3339999) }}</span>
                            </div>
                            <div class="col-auto">
                                <div class="icon icon-shape bg-success px-2 py-1 text-white rounded-circle shadow">
                                    <i class="bi bi-coin"></i>
                                </div>
                            </div>
                        </div>
                        <p class="mt-3 mb-0 text-muted text-sm">
                            <span class="text-success me-2"><i class="bi bi-arrow-up-short"></i> 3.48%</span>
                            <span class="text-nowrap">Kể từ tháng trước</span>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-lg-6">
                <div class="card card-stats mb-4 mb-xl-0">
                    <div class="card-body">
                        <div class="row">
                            <div class="col">
                                <h6 class="card-title text-uppercase text-muted mb-0">Hiệu xuất</h6>
                                <span class="h3 font-weight-bold mb-0">30%</span>
                            </div>
                            <div class="col-auto">
                                <div class="icon icon-shape bg-primary px-2 py-1 text-white rounded-circle shadow">
                                    <i class="bi bi-percent"></i>
                                </div>
                            </div>
                        </div>
                        <p class="mt-3 mb-0 text-muted text-sm">
                            <span class="text-success me-2"><i class="bi bi-arrow-up-short"></i> 3.48%</span>
                            <span class="text-nowrap">Kể từ tháng trước</span>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section class="container pt-4">
        <div class="row">
            <div class="col-8  p-2 ">
                <div class="p-2 bg-chart-primary rounded">
                    <canvas ref="lineChartRef" class="w-100" style="min-height:300px;"></canvas>
                </div>
            </div>
            <div class="col p-2 d-flex flex-column justify-content-between">
                <div class="bg-chart rounded">
                    <canvas ref="barChartRef" class="h-100"></canvas>
                </div>
                <div class="bg-chart rounded">
                    <canvas ref="revenueChartRef"></canvas>
                </div>
            </div>
        </div>
    </section>
    <section class="p-2">
        <div class="card shadow">
            <div class="card-header border-0">
                <div class="row align-items-center">
                    <div class="col">
                        <h3 class="mb-0">Khóa học theo doanh thu</h3>
                    </div>
                </div>
            </div>
            <div class="table-responsive">
                <!-- Projects table -->
                <table class="table align-items-center table-flush">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">Tên khóa học</th>
                            <th scope="col">Giáo viên</th>
                            <th scope="col">Số học viên</th>
                            <th scope="col">Doanh thu</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">
                                Lap trinh
                            </th>
                            <td>
                                Van Teo
                            </td>
                            <td>
                                340
                            </td>
                            <td class="text-truncate">
                                {{ formatPrice(9338899) }}
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                Lap trinh
                            </th>
                            <td>
                                Van Teo
                            </td>
                            <td>
                                340
                            </td>
                            <td class="text-truncate">
                                {{ formatPrice(9338899) }}
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                Lap trinh
                            </th>
                            <td>
                                Van Teo
                            </td>
                            <td>
                                340
                            </td>
                            <td class="text-truncate">
                                {{ formatPrice(9338899) }}
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                Lap trinh
                            </th>
                            <td>
                                Van Teo
                            </td>
                            <td>
                                340
                            </td>
                            <td class="text-truncate">
                                {{ formatPrice(9338899) }}
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                Lap trinh
                            </th>
                            <td>
                                Van Teo
                            </td>
                            <td>
                                340
                            </td>
                            <td class="text-truncate">
                                {{ formatPrice(9338899) }}
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</template>

<script setup>
import formatPrice from '@/utils/formatPrice';
import { onMounted, ref } from 'vue';
import { Chart } from 'chart.js/auto'

const lineChartRef = ref(null)
const barChartRef = ref(null)
const revenueChartRef = ref(null)
onMounted(() => {
    if (!lineChartRef.value) return

    new Chart(lineChartRef.value.getContext('2d'), {
        type: 'line',
        data: {
            labels: [50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150],
            datasets: [{
                label: 'Lượng truy cập',
                data: [7, 8, 8, 9, 9, 9, 10, 11, 14, 14, 15],
                borderColor: '#ffffff',
                backgroundColor: '#ffffff',
                tension: 0.4,
                fill: false
            }]
        },
        options: {
            responsive: true,
            animation: { duration: 1000 },
            scales: {
                x: {
                    ticks: {
                        color: '#ffffff'           // màu chữ trục x
                    },
                    grid: {
                        color: 'rgba(255, 255, 255, 0.1)' // màu lưới mờ trắng
                    }
                },
                y: {
                    beginAtZero: true,
                    ticks: {
                        color: '#ffffff'           // màu chữ trục y
                    },
                    grid: {
                        color: 'rgba(255, 255, 255, 0.1)' // màu lưới mờ trắng
                    }
                }
            },
            plugins: {
                legend: {
                    labels: {
                        color: '#ffffff'           // màu chú thích
                    }
                }
            }
        }
    }),
        new Chart(barChartRef.value.getContext('2d'), {
            type: 'bar',
            data: {
                labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5'],
                datasets: [{
                    label: 'Doanh thu (triệu VNĐ)',
                    data: [12, 19, 8, 17, 25],
                    backgroundColor: '#ffffff', // màu trắng
                    borderRadius: 8,
                }]
            },
            options: {
                responsive: true,
                scales: {
                    x: {
                        ticks: { color: '#ffffff' },
                        grid: { color: 'rgba(255, 255, 255, 0.1)' }
                    },
                    y: {
                        beginAtZero: true,
                        ticks: { color: '#ffffff' },
                        grid: { color: 'rgba(255, 255, 255, 0.1)' }
                    }
                },
                plugins: {
                    legend: {
                        labels: { color: '#ffffff' }
                    }
                }
            }
        }),
        new Chart(revenueChartRef.value.getContext('2d'), {
            type: 'bar',
            data: {
                labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5'],
                datasets: [{
                    label: 'Lợi nhuận (triệu VNĐ)',
                    data: [12, 19, 8, 17, 25],
                    backgroundColor: '#ffffff', // màu trắng
                    borderRadius: 8,
                }]
            },
            options: {
                responsive: true,
                scales: {
                    x: {
                        ticks: { color: '#ffffff' },
                        grid: { color: 'rgba(255, 255, 255, 0.1)' }
                    },
                    y: {
                        beginAtZero: true,
                        ticks: { color: '#ffffff' },
                        grid: { color: 'rgba(255, 255, 255, 0.1)' }
                    }
                },
                plugins: {
                    legend: {
                        labels: { color: '#ffffff' }
                    }
                }
            }
        })
})
</script>

<style scoped>
.green-primary {
    background: var(--green-primary);
}

.bg-chart {
    background: linear-gradient(135deg, #004643, #00B99B);

}

.bg-chart-primary {
    background: linear-gradient(135deg, #001F1F 0%, #004643 50%, #00EAD3 100%);
}
</style>