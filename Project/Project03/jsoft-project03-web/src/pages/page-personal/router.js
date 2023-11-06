import Vue from 'vue'
import VueRouter from 'vue-router'
import ResumeView from './components/ResumeView.vue'
import SelectView from './components/SelectView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/personal/resume',
    name: 'resume',
    component: ResumeView
  },
  {
    path: '/personal/select',
    name: 'select',
    component: SelectView
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
