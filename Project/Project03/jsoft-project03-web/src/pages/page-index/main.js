import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import jsCookie from 'js-cookie'

Vue.prototype.$cookie = jsCookie
Vue.config.productionTip = false
Vue.prototype.axios = axios

new Vue({
  axios,
  router,
  render: h => h(App)
}).$mount('#app')
