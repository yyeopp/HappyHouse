<template>
  <div
    class="md-layout-item md-size-40 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
    style="height: 2000px"
  ></div>
</template>

<script>
import { mapMutations, mapActions } from "vuex";

const accountStore = "accountStore";

export default {
  methods: {
    ...mapMutations(accountStore, ["SET_IS_LOGIN", "SET_IS_LOGIN_ERROR"]),
    ...mapActions(accountStore, ["getNaverUserInfo"]),
  },
  computed: {},
  async created() {
    let access_token = this.$route.query.access_token;
    let refresh_token = this.$route.query.access_token;
    this.SET_IS_LOGIN(true);
    this.SET_IS_LOGIN_ERROR(false);
    sessionStorage.setItem("access-token", access_token);
    sessionStorage.setItem("refresh-token", refresh_token);

    await this.getNaverUserInfo();
    this.$router.push("/");
  },
};
</script>

<style lang="css"></style>
