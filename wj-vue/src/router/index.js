import Vue from 'vue'
import Router from 'vue-router'
//使用懒加载的方式来引入,只有路由被访问的时候才加载
import home from '@/components/home'
const loginpage = resolve => require(['@/components/Login'],resolve)

Vue.use(Router)
    let router =  new Router({
  routes: [
        {
            path:'/',
            name :'login',
            component:loginpage
        },
        {
            path:'/login',
            name :'login',
            component:loginpage
        },
        {
            path:'/home',
            name :'home',
            component:home
        }
  ]
})

export default router