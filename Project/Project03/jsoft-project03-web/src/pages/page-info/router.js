import Vue from 'vue'
import VueRouter from 'vue-router'
import CompanyView from './components/CompanyView.vue'
import JobInfoView from './components/JobInfoView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/info',
    name: 'info',
    component: JobInfoView
  },
  {
    path: '/info/company',
    name: 'company',
    component: CompanyView
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
