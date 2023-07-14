<template>
  <div class="wrapper d-flex">
    <Navbar />
    <div class="avatar-wrapper"></div>
    <div class="profile-wrapper"></div>

  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import Navbar from "../components/layout/Navbar.vue";

export default defineComponent({
  name: "MyProfileView",
  components: {
    Navbar
  }
});
</script>
<style scoped>
</style>