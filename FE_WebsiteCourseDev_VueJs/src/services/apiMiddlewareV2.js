import axios from 'axios'
// import jwtDecode from 'jwt-decode' // Bật lên nếu dùng phân quyền

const getToken = () => sessionStorage.getItem('token')

// Biến kiểm soát bật/tắt token
let authEnabled = true

// Tạo Axios instance dùng chung
const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 10000,
})

// Gắn token nếu tồn tại và được bật
apiClient.interceptors.request.use(config => {
  if (authEnabled) {
    const token = getToken()
    if (token) {
      config.headers.Authorization = `Bearer ${token}`

      // PHÂN QUYỀN THEO TIỀN TỐ API — BẬT LÊN KHI MUỐN KIỂM SOÁT QUYỀN
      /*
      const { role } = jwtDecode(token)
      const urlPath = config.url || ''
      const prefix = urlPath.split('/')[2] // ví dụ: 'admin', 'teacher', 'student', 'public'

      const roleMap = {
        admin: ['admin'],
        teacher: ['teacher'],
        student: ['student'],
        public: ['admin', 'teacher', 'student'] // ai cũng được gọi
      }

      const allowedRoles = roleMap[prefix] || []

      if (!allowedRoles.includes(role)) {
        console.warn(`Quyền "${role}" không được phép gọi API "${urlPath}"`)
        throw new Error('Không có quyền truy cập API này')
      }
      */
    }
  }
  return config
})

// Xoá param null, undefined, rỗng chuỗi
const cleanParams = (params = {}) => {
  return Object.fromEntries(
    Object.entries(params).filter(
      ([_, v]) =>
        v !== null &&
        v !== undefined &&
        !(typeof v === 'string' && v.trim() === '')
    )
  )
}

// GET: dùng cho query param
export const getList = async (endpoint, queryParams = {}) => {
  try {
    const response = await apiClient.get(endpoint, {
      params: cleanParams(queryParams),
      paramsSerializer: {
        indexes: null 
      }
    })
    return response.data
  } catch (error) {
    console.error(`GET ${endpoint} failed:`, error)
    throw error
  }
}

// GET: dùng cho endpoint chi tiết theo id
export const getById = async endpoint => {
  try {
    const response = await apiClient.get(endpoint)
    return response.data
  } catch (error) {
    console.error(`GET ${endpoint} failed:`, error)
    throw error
  }
}

// POST
export const createItem = async (endpoint, data = {}) => {
  try {
    const response = await apiClient.post(endpoint, data)
    return response.data
  } catch (error) {
    console.error(`POST ${endpoint} failed:`, error)
    throw error
  }
}

// PUT
export const updateItem = async (endpoint, data = {}) => {
  try {
    const response = await apiClient.put(endpoint, data)
    return response.data
  } catch (error) {
    console.error(`PUT ${endpoint} failed:`, error)
    throw error
  }
}

// DELETE
export const deleteItem = async endpoint => {
  try {
    const response = await apiClient.delete(endpoint)
    return response.data
  } catch (error) {
    console.error(`DELETE ${endpoint} failed:`, error)
    throw error
  }
}

// Tùy chọn bật/tắt token (giữ nguyên tên hàm cũ)
export const setAuthorization = enabled => {
  authEnabled = enabled
}

// POST: nhận blob
export const postWithBlob = async (endpoint, data = {}) => {
  try {
    const response = await apiClient.post(endpoint, data, {
      responseType: 'blob'
    })
    return response.data
  } catch (error) {
    console.error(`POST (blob) ${endpoint} failed:`, error)
    throw error
  }
}
