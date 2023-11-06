import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import Cookies from 'js-cookie'

import SliderVerificationCode from 'slider-verification-code';
import 'slider-verification-code/lib/slider-verification-code.css';

Vue.prototype.$cookie = Cookies;
Vue.use(SliderVerificationCode);

Vue.config.productionTip = false
Vue.prototype.axios = axios

new Vue({
  axios,
  router,
  render: h => h(App)
}).$mount('#app')
