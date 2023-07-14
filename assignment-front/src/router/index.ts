import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import auth from "./../middleware/auth"

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/HomeView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/LoginView.vue')
  },
  {
    path: '/manage-list',
    name: 'manage-list',
    component: () => import('../views/ManageListView.vue'),
    meta: {
      middleware: [auth],
    },
  },
  {
    path: '/my-profile',
    name: 'my-profile',
    component: () => import('../views/MyProfileView.vue')
  },
  {
    path: '/order-detail',
    name: 'order-detail',
    component: () => import('../views/OrderDetailView.vue')
  },
  {
    path: '/product-detail',
    name: 'product-detail',
    component: () => import('../views/ProductDetailView.vue'),
    meta: {
      middleware: [auth],
    },

  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
