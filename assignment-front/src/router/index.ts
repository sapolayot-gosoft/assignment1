import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import useAuth from "@/middleware/auth"
const { checkAuth } = useAuth()


const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/HomeView.vue'),
    meta: { requiredGuest: true }
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/LoginView.vue'),
    meta: { requiredGuest: true }
  },
  {
    path: '/manage-list',
    name: 'manage-list',
    component: () => import('../views/ManageListView.vue'),
    meta: { requiredAuth: true }
  },
  {
    path: '/my-profile',
    name: 'my-profile',
    component: () => import('../views/MyProfileView.vue'),
    meta: { requiredAuth: true }
  },
  {
    path: '/order-detail',
    name: 'order-detail',
    component: () => import('../views/OrderDetailView.vue'),
    meta: { requiredAuth: true }
  },
  {
    path: '/product-detail',
    name: 'product-detail',
    component: () => import('../views/ProductDetailView.vue'),
    meta: { requiredAuth: true }
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

router.beforeEach(async (to, from, next) => {
  if (to.meta.requiredAuth) {
    if (!(await checkAuth(to))) {
      return next({ name: 'login' })
    }
  } else if (to.meta.requiredGuest) {
    if (await checkAuth(to)) {
      return next({ name: 'manage-list' })
    }
  }
  next()
})

export default router
