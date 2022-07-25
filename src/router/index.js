import Vue from 'vue'
import VueRouter from 'vue-router'
import ForumView from '../views/ForumView.vue'
import IndividualView from '../views/IndividualView.vue'
import MatchView from '../views/MatchView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'forum',
    component: ForumView
  },
  {
    path: '/match',
    name: 'match',
    component: MatchView
  },
  {
    path: '/individual',
    name: 'individual',
    component: IndividualView
  },
]

const router = new VueRouter({
  routes
})

export default router
