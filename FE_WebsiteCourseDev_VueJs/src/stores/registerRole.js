import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useRegisterRole = defineStore('registerRole', () => {
  const role = ref('') 

  function setRole(newRole) {
    role.value = newRole
    sessionStorage.setItem('user_role', newRole) 
  }

  function loadRole() {
    role.value = sessionStorage.getItem('user_role') || ''
  }

  return {
    role,
    setRole,
    loadRole
  }
})
