<template>
  <v-app id="material-kit">
    <div :class="{ 'nav-open': NavbarStore.showNavbar }">
      <router-view name="header" />
      <div>
        <router-view />
      </div>
      <router-view name="footer" />
    </div>
  </v-app>
</template>

<style>
.v-application {
  font-family: "SCDream4", sans-serif !important;
}

@font-face {
  font-family: "SCDream4";
  src: url("assets/fonts/SCDream4.otf") format("opentype");
  font-weight: 400;
}
</style>
