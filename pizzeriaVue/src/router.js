import { createRouter, createWebHistory } from 'vue-router';

import List from './components/pizza/List.vue';
import create from './components/pizza/create.vue';

// import order from './pages/order.vue'
// import check from './pages/check.vue'


const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'List',
            component: List
        },
        {
            path: '/create',
            name: 'create',
            component: create
        }
    ]
});
export { router };