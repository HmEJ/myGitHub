import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from './components/LoginView.vue'
import RegisterView from './components/Register.vue'
import ForgetPwdView from './components/ForgetPwdView.vue'
import ResumeView from './components/ResumeView.vue'
import UserAgreeView from './components/UserAgreeView.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/login/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/login/forget',
    name: 'forget',
    component: ForgetPwdView
  },
  {
    path: '/login/resume',
    name: 'resume',
    component: ResumeView
  },
  {
    path: '/login/user_agree',
    name: 'user_agree',
    component: UserAgreeView
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
