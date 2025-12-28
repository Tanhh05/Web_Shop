import { createRouter, createWebHistory } from 'vue-router';
import AdminLayout from '@/layout/AdminLayout.vue';
import Dashboard from '@/views/Dashboard.vue';
import Product from '@/views/Product/Product.vue';
import AddProduct from "@/views/Product/AddProduct.vue";
import CategoryList from "@/views/Category/CategoryList.vue";
import AddCategory from "@/views/Category/AddCategory.vue";
import EditCategory from "@/views/Category/EditCategory.vue";

const routes = [
    {
        path: "/admin",
        component: AdminLayout,
        redirect: "/admin/dashboard",
        children: [
            { path: "dashboard", component: Dashboard },
            { path: "products", component: Product },
            { path: "products/add", component: AddProduct },
            {
                path: "categories",
                name: 'Categories',
                component: CategoryList,
            },
            {
                path: "categories/add",  // Ensure this is correctly under "/admin"
                name: 'AddCategory',
                component: AddCategory,
            },
            {
                path: "categories/edit/:id",
                name: 'EditCategory',
                component: EditCategory,
                props: true,
            },
        ],
    },
];

export default createRouter({
    history: createWebHistory(),
    routes,
});
