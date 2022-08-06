import Vue from 'vue'
import VueRouter from 'vue-router'
import ForumView from '../views/ForumView.vue'
import IndividualView from '../views/IndividualView.vue'
import MatchView from '../views/MatchView.vue'
import LoginView from "@/views/LoginView"
import MessageView from '../views/MessageView.vue'
import SetupView from '../views/Setup.vue'
import user from '../store/user'

Vue.use(VueRouter)

const routes = [
  {
    path: '/forum',
    name: 'forum',
    component: ForumView
  },
  {
    path: '/',
    name: 'match',
    component: MatchView
  },
  {
    path: '/individual',
    name: 'individual',
    component: IndividualView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/message',
    name: 'message',
    component: MessageView
  },
  {
    path: '/setup',
    name: 'setup',
    component: SetupView
  }
]

const router = new VueRouter({
  routes
})

//路由守卫
router.beforeEach((to, from, next) => {
  if (to.path==='/login') {
    next()
  }else{
    console.log(user.state.uInfo);
    if (user.state.uInfo) {
      next()
    }
    else{
      router.push({path:'/login'})
    }
  }
})


export default router
