import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ShowInfo from '../components/ShowInfo.vue'
import AddCom from '../components/AddCom.vue'
import AddUser from '../components/AddUser.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      redirect: '/showInfo',
      children: [
        {
          path: '/showInfo',
          component: ShowInfo,
        },
        {
          path: '/addCom',
          component: AddCom
        },
        {
          path: '/addUser',
          component: AddUser
        }
      ],
    },
  ]
})

export default router
