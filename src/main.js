import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
Vue.config.productionTip = false
// 防抖函数
Vue.prototype.delay= (function () {
  let timer = 0
  return function (callback, ms) {
  clearTimeout(timer)
  timer = setTimeout(callback, ms)
  }
})()
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
