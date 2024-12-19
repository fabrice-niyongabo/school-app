import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home/Home.vue";
import AddStudent from "../views/AddStudent.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
    },
    {
      path: "/addStudent",
      name: "addStudent",
      component: AddStudent,
    },
  ],
});

export default router;
