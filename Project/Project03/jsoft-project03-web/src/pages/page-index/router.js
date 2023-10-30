import Vue from 'vue'
import VueRouter from 'vue-router'
import ShowContentView from './components/ShowContentView.vue'
import SearchJobView from './components/SearchJobView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/index',
    name: 'showContent',
    component: ShowContentView
  },
  {
    path: '/index/search',
    name: 'searchJob',
    component: SearchJobView
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
