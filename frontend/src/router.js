import Vue from "vue";
import Router from "vue-router";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import store from "@/store/index.js";

Vue.use(Router);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["accountStore/checkUserInfo"];

  let token = sessionStorage.getItem("access-token");

  if (token) {
    if (checkUserInfo.age != 99) {
      await store.dispatch("accountStore/getUserInfo", token);
    } else {
      await store.dispatch("accountStore/getNaverUserInfo");
    }
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");

    next({ name: "login", params: { from: from } });
  } else {
    next();
  }
};
const onlyAdmin = async (to, from, next) => {
  const checkUserInfo = store.getters["accountStore/checkUserInfo"];
  let token = sessionStorage.getItem("access-token");

  if (token) {
    if (checkUserInfo.age != 99) {
      await store.dispatch("accountStore/getUserInfo", token);
    } else {
      await store.dispatch("accountStore/getNaverUserInfo");
    }
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "login" });
  } else if (checkUserInfo.id != "admin") {
    alert("권한이 없습니다.");
    next({ name: "notice" });
  } else {
    next();
  }
};

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "index",
      components: {
        default: () => import("@/views/Index.vue"),
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400, defaultWhite: false },
        footer: { backgroundColor: "black" },
      },
    },
    {
      path: "/aptView",
      name: "aptView",
      redirect: "/aptView/aptdeal",
      components: {
        default: () => import("@/views/AptView.vue"),
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 150, defaultWhite: false },
        footer: { backgroundColor: "black" },
      },
      children: [
        {
          path: "aptdeal",
          name: "aptdeal",
          component: () =>
            import("@/views/components/apartment/AptDealMain.vue"),
        },
        {
          path: "interests",
          name: "interests",
          beforeEnter: onlyAuthUser,
          component: () =>
            import("@/views/components/apartment/AptInterestsMain.vue"),
        },
      ],
    },
    {
      path: "/account",
      name: "account",
      components: {
        default: () => import("@/views/AccountView.vue"),
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black", defaultWhite: false },
      },

      children: [
        {
          path: "login",
          name: "login",
          component: () => import("@/views/components/account/LoginView.vue"),
        },
        {
          path: "profile",
          name: "profile",
          beforeEnter: onlyAuthUser,
          component: () => import("@/views/components/account/ProfileView.vue"),
        },
        {
          path: "register",
          name: "register",
          component: () =>
            import("@/views/components/account/RegisterView.vue"),
        },
        {
          path: "password",
          name: "password",
          component: () =>
            import("@/views/components/account/PasswordView.vue"),
        },
        {
          path: "waiting",
          name: "waiting",
          component: () => import("@/views/components/account/Waiting.vue"),
        },
      ],
    },
    {
      path: "/board",
      name: "board",
      components: {
        default: () => import("@/views/BoardView.vue"),
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black", defaultWhite: false },
      },
      children: [
        {
          path: "question",
          name: "question",
          component: () => import("@/views/components/board/QuestionBoard.vue"),
        },
        {
          path: "questionWrite",
          name: "questionWrite",
          beforeEnter: onlyAuthUser,
          component: () => import("@/views/components/board/QuestionWrite.vue"),
        },
        {
          path: "notice",
          name: "notice",
          component: () => import("@/views/components/board/NoticeBoard.vue"),
        },
        {
          path: "notice/:boardId",
          name: "noticeDetail",
          component: () => import("@/views/components/board/NoticeDetail.vue"),
        },
        {
          path: "noticeWrite",
          name: "noticeWrite",
          beforeEnter: onlyAdmin,
          component: () => import("@/views/components/board/NoticeWrite.vue"),
        },
      ],
    },
  ],
  scrollBehavior: (to) => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  },
});
