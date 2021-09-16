import {createRouter, createWebHistory} from 'vue-router'
import layout from "@/layout/layout";

const routes = [
    {
        path: '/',
        name: 'layout',
        component: layout,
        redirect: "/home",
        children: [
            {
                path: 'home',
                name: 'home',
                component: () => import("@/views/Home"),
            },
            {
                path: 'blog',
                name: 'blog',
                component: () => import("@/views/Blog"),
            },
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: () => import("@/views/Login"),
    }

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
