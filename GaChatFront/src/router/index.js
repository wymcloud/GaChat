import Vue from 'vue'
import Router from 'vue-router'
import head from '@/components/head/head'
import foot from '@/components/foot/foot'



Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'head',
      component: head
    }
  ]
})
