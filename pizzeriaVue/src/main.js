import { createApp } from 'vue'
import App from './App.vue'
import './style.css'
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import { router } from "./router.js"

createApp(App).use(router).mount('#app')
