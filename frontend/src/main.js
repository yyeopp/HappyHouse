// =========================================================
// * Vue Material Kit - v1.2.2
// =========================================================
//
// * Product Page: https://www.creative-tim.com/product/vue-material-kit
// * Copyright 2019 Creative Tim (https://www.creative-tim.com)
// * Licensed under MIT (https://github.com/creativetimofficial/vue-material-kit/blob/master/LICENSE.md)
//
// * Coded by Creative Tim
//
// =========================================================
//
// * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

import Vue from "vue";
import App from "./App.vue";
import router from "./router";

import MaterialKit from "./plugins/material-kit";
import store from "./store";

import Vuetify from "vuetify";
import "vuetify/dist/vuetify.min.css";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

// Import Bootstrap and BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import VueKakaoSdk from "vue-kakao-sdk";

Vue.config.productionTip = false;
Vue.use(Vuetify);

Vue.use(MaterialKit);
// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

const apiKey = "53e7214792a38308832d05f2b2981bdf";
Vue.use(VueKakaoSdk, { apiKey }); // apiKey 를 반드시 입력해줘야한다

const NavbarStore = {
  showNavbar: false,
};

Vue.mixin({
  data() {
    return {
      NavbarStore,
    };
  },
});

Vue.filter("money", function(value) {
  if (value.length < 6) return value;
  let tenMillion = value.slice(-5);
  let hundredMillion = value.slice(0, value.length - 5);
  return hundredMillion + "억" + tenMillion;
});

new Vue({
  router,
  store,
  vuetify: new Vuetify(),
  render: (h) => h(App),
}).$mount("#app");
