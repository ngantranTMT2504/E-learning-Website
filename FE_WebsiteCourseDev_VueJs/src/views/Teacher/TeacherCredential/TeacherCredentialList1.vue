<template>
  <div class="">
    <h2 class="fw-bold text-uppercase mb-4">Bằng cấp & Chứng chỉ</h2>
    <div class="row">
      <!-- Form thêm/sửa nằm sticky bên phải -->
      <div class="col-xl-4 order-xl-2 mb-4">
        <div class="card shadow position-sticky" style="top: 80px;">
          <div class="card-body">
            <h5 class="card-title text-center fw-bold mb-3 text-uppercase">{{ isEditing ? 'Cập nhật Bằng cấp' : 'Thêm Bằng cấp' }}</h5>
            <form @submit.prevent="isEditing ? updateItem() : addQualification()">
              <div class="mb-2">
                <input v-model="form.title" type="text" class="form-control" placeholder="Tên Bằng cấp" required />
              </div>
              <div class="mb-2">
                <input v-model="form.issuer" type="text" class="form-control" placeholder="Tổ chức cấp" required />
              </div>
              <div class="mb-2">
                <input v-model="form.year" type="number" class="form-control" placeholder="Năm cấp" required />
              </div>
              <div class="mb-3">
                <label class="form-label">Hình ảnh</label>
                <input type="file" class="form-control" accept="image/*" @change="handleImageUpload" />
                <img v-if="previewImage" :src="previewImage" class="img-fluid rounded mt-2" />
              </div>
              <ButtonCustom class="w-100">
                {{ isEditing ? 'Cập nhật' : 'Thêm mới' }}
              </ButtonCustom>
            </form>
          </div>
        </div>
      </div>

      <!-- Danh sách bằng cấp -->
      <div class="col-xl-8 order-xl-1">
        <div v-for="(item, index) in qualifications"
            :key="index"
            class="card mb-4 position-relative"
            >
            <!-- Nút sửa & xóa -->
            <div class="position-absolute top-0 end-0 p-2 z-2">
              <div class="d-flex gap-2">
                <a @click="editItem(index)"><ButtonCustom>Sửa</ButtonCustom></a>
                <a @click="deleteItem(index)"><ButtonCustom>Xoá</ButtonCustom></a>
              </div>
            </div>

            <!-- Nội dung hiển thị vẫn nằm như bình thường -->
            <div class="card-body">
                <h5 class="card-title">{{ item.title }}</h5>
                <p class="card-text">Tổ chức cấp: {{ item.issuer }}</p>
                <p class="card-text">
                <small class="text-muted">Năm cấp: {{ item.year }}</small>
                </p>
                <img
                v-if="item.image"
                :src="item.image"
                class="img-fluid rounded mt-2 mb-2"
                alt="Ảnh minh chứng"
                />
            </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref } from 'vue';
import ButtonCustom from '@/components/Common/ButtonCustom.vue';

const qualifications = ref([
  {
    title: 'Thạc sĩ CNTT',
    issuer: 'ĐH Cần Thơ',
    year: 2020,
    image: ''
  }
]);

const form = ref({ title: '', issuer: '', year: '', image: '' });
const previewImage = ref(null);
const isEditing = ref(false);
const editingIndex = ref(null);

function handleImageUpload(e) {
  const file = e.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = () => {
      form.value.image = reader.result;
      previewImage.value = reader.result;
    };
    reader.readAsDataURL(file);
  }
}

function addQualification() {
  qualifications.value.push({ ...form.value });
  resetForm();
}

function editItem(index) {
  const item = qualifications.value[index];
  form.value = { ...item };
  previewImage.value = item.image;
  isEditing.value = true;
  editingIndex.value = index;
}

function updateItem() {
  qualifications.value[editingIndex.value] = { ...form.value };
  resetForm();
}

function deleteItem(index) {
  qualifications.value.splice(index, 1);
}

function resetForm() {
  form.value = { title: '', issuer: '', year: '', image: '' };
  previewImage.value = null;
  isEditing.value = false;
  editingIndex.value = null;
}
</script>
<style scoped>
.card {
  box-shadow: 0 4px 10px rgba(0,0,0,0.08);
}
img {
  max-height: 200px;
  object-fit: contain;
}


/* 
import { ref, onMounted } from 'vue';
import axios from 'axios';

const qualifications = ref([]);
const isEditing = ref(false);
const editingId = ref(null);
const previewImage = ref(null);
const selectedFile = ref(null);

const form = ref({
  certificateName: '',
  certificateImage: '',
  issuedYear: '',
  uploadedDate: '',
  issuingOrganization: '',
  accountId: 1,
  teacherId:null
});

const fetchQualifications = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/teacher-certificate', {
      params: { accountId: form.value.accountId }
    });
    console.log('👉 Dữ liệu API trả về:', res.data);
    qualifications.value = res.data.data; // ✅ Gán đúng danh sách
  } catch (err) {
    console.error('❌ Lỗi tải danh sách:', err);
  }
};

const handleImageUpload = (event) => {
  const file = event.target.files[0];
  if (file) {
    selectedFile.value = file;
    previewImage.value = URL.createObjectURL(file);
  }
};

const addQualification = async () => {
  const fd = new FormData();
  fd.append('certificateName', form.value.certificateName);
  fd.append('issuedYear', form.value.issuedYear);
  fd.append('uploadedDate', form.value.uploadedDate);
  fd.append('issuingOrganization', form.value.issuingOrganization);
  if (selectedFile.value) {
    fd.append('certificateImage', selectedFile.value);
  }

  try {
    await axios.post('/api/teacher-certificate', fd);
    resetForm();
    await fetchQualifications();
  } catch (err) {
    console.error('Lỗi thêm:', err);
  }
};

const updateItem = async () => {
  const fd = new FormData();
  fd.append('certificateName', form.value.certificateName);
  fd.append('issuedYear', form.value.issuedYear);
  fd.append('uploadedDate', form.value.uploadedDate);
  if (selectedFile.value) {
    fd.append('image', selectedFile.value);
  }

  try {
    await axios.put(`/api/teacher-certificate/${editingId.value}`, fd);
    resetForm();
    await fetchQualifications();
  } catch (err) {
    console.error('Lỗi cập nhật:', err);
  }
};

const deleteItem = async (id) => {
  try {
    await axios.delete(`/api/teacher-certificate/${id}`);
    await fetchQualifications();
  } catch (err) {
    console.error('Lỗi xoá:', err);
  }
};

const editItem = (item) => {
  form.value = {
    title: item.title,
    issuer: item.issuer,
    year: item.year,
    imageUrl: item.imageUrl || '',
    accountId: item.accountId,
  };
  editingId.value = item.id;
  previewImage.value = item.imageUrl || null;
  selectedFile.value = null;
  isEditing.value = true;
};

const resetForm = () => {
  form.value = {
    title: '',
    issuer: '',
    year: '',
    imageUrl: '',
    accountId: 1,
  };
  previewImage.value = null;
  selectedFile.value = null;
  isEditing.value = false;
  editingId.value = null;
};

onMounted(fetchQualifications); */
</style>
