import { defineStore } from 'pinia'
import { ref } from 'vue'
import { jwtDecode } from 'jwt-decode'

export const useAuthStore = defineStore('auth', () => {
  const token = ref(sessionStorage.getItem('TOKEN') || null)
  const user = ref(null)
  const role = ref(null)
  const userId = ref(null)
  const userName = ref(null)
  const isLoggedIn = ref(!!token.value)

  function loginSuccess(jwt) {
    token.value = jwt
    sessionStorage.setItem('TOKEN', jwt)

    const decoded = jwtDecode(jwt)
    userId.value = decoded.userId || null;
    user.value = decoded.sub || decoded.email
    role.value = decoded.role[0].authority || null
    userName.value = decoded.userName || null
    isLoggedIn.value = true
  }

  function logout() {
    token.value = null
    user.value = null
    role.value = null
    userId.value = null
    userName.value = null
    isLoggedIn.value = false
    sessionStorage.removeItem('TOKEN')
  }

  return {
    token,
    user,
    role,
    userId,
    userName,
    isLoggedIn,
    loginSuccess,
    logout,
  }
})
