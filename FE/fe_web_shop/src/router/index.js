
import { createRouter, createWebHistory } from 'vue-router'
import AdminLayout from '@/layout/AdminLayout.vue'

import Dashboard from '@/views/Dashboard.vue'
import Product from '@/views/Product/Product.vue'
import Order from '@/views/Order.vue'
import Customer from '@/views/Customer.vue'
import Setting from '@/views/Setting.vue'
import AddProduct from "@/views/Product/AddProduct.vue";

const routes = [
    {
        path: "/admin",
        component: AdminLayout,
        redirect: "/admin/dashboard",
        children: [
            { path: "dashboard", component: Dashboard },

            { path: "products", component: Product },

            {
                path: "products/add",
                component: AddProduct,
            },

            { path: "orders", component: Order },
            { path: "customers", component: Customer },
            { path: "settings", component: Setting },
        ],
    },
];

export default createRouter({
    history: createWebHistory(),
    routes
})
