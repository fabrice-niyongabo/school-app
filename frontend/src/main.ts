import { createApp } from "vue";
import "./style.css";
import router from "./router";
import App from "./App.vue";

//@ts-ignore
import "vuetify/styles";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";
import { createVuetify } from "vuetify";

//toast
import ToastPlugin from "vue-toast-notification";
import "vue-toast-notification/dist/theme-bootstrap.css";

const vuetify = createVuetify({ components, directives });

createApp(App).use(router).use(vuetify).use(ToastPlugin).mount("#app");
