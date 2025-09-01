import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/js/bootstrap.bundle.js'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import 'quill/dist/quill.snow.css'
import './assets/main.css'
import { createPinia } from 'pinia'


const app = createApp(App)
const pinia = createPinia()

app.use(pinia) 
app.use(router)

app.mount('#app')
