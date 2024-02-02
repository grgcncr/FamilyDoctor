import { createRouter, createWebHistory } from 'vue-router';
// import HomeView from '../views/HomeView.vue';
import { useApplicationStore } from '@/stores/application.js';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            // component: HomeView,
            component: () => import('../views/HomeView.vue'),
            meta: { requiresAuth: true }
        },
        {
            path: '/citizen',
            name: 'citizen',
            component: () => import('../views/CitizensView.vue'),
            meta: { requiresAuth: true }
        },
        {
            path: '/citizen/new',
            name: 'citizen-new',
            component: () => import('../views/CreateCitizenView.vue'),
            meta: { requiresAuth: true }
        },
        {
            path: '/citizen/:id',
            name: 'citizen',
            component: () => import('../views/CitizenView.vue'),
            meta: { requiresAuth: true },
            children: [
                {
                    path: '',
                    name: 'citizen-details',
                    component: () => import('../views/CitizenDetailsView.vue'),
                    meta: { requiresAuth: true }
                },
                {
                    path: 'requests',
                    name: 'citizen-requests',
                    component: () => import('../views/CitizenRequestsView.vue'),
                    meta: { requiresAuth: true }
                }
            ]
        },
        {
            path: '/doctor',
            name: 'doctor',
            component: () => import('../views/DoctorsView.vue'),
            meta: { requiresAuth: true }
        },
        {
            path: '/doctor/new',
            name: 'doctor-new',
            component: () => import('../views/CreateDoctorView.vue'),
            meta: { requiresAuth: true }
        },
        {
            path: '/doctor/:id',
            name: 'doctor',
            component: () => import('../views/DoctorView.vue'),
            meta: { requiresAuth: true },
            children: [
                {
                    path: '',
                    name: 'doctor-details',
                    component: () => import('../views/DoctorDetailsView.vue'),
                    meta: { requiresAuth: true }
                },
                {
                    path: 'requests',
                    name: 'doctor-requests',
                    component: () => import('../views/DoctorRequestsView.vue'),
                    meta: { requiresAuth: true }
                }
            ]
        },
        {
            path: '/request/:id',
            name: 'request',
            component: () => import('../views/RequestView.vue'),
            meta: { requiresAuth: true },
            children: [
                {
                    path: '',
                    name: 'request-details',
                    component: () => import("../views/RequestDetailsView.vue"),
                    meta: { requiresAuth: true },
                },
                {
                    path: 'citizen',
                    name: 'request-citizen',
                    component: () => import("../views/RequestDoctorsView.vue"),
                    meta: { requiresAuth: true },
                }
            ]
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('../views/LoginView.vue')
        },
        {
            path: '/logout',
            name: 'logout',
            component: () => import('../views/LogoutView.vue.vue'),
            meta: { requiresAuth: true }
        }
    ]
});

router.beforeEach((to, from, next) => {
    const { isAuthenticated } = useApplicationStore();
    const requiresAuth = to.matched.some((record) => record.meta.requiresAuth);

    if (requiresAuth && !isAuthenticated) {
        console.log('user not authenticated. redirecting to /login');
        next('/login');
    } else {
        next();
    }
});

export default router;