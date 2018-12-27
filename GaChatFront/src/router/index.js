import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import head from '@/components/head/head'


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
