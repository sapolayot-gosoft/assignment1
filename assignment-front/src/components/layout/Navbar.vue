<template>
  <v-app fixed class="app-bar px-4">
    <div class="d-flex justify-end">
      <!-- <v-toolbar-title class>{{ title }}</v-toolbar-title> -->
      <v-btn class="mr-4" width="120px" @click.prevent="$router.push('/manage-list')"> Manage List</v-btn>
      <v-btn class="mr-4" width="120px" @click.prevent="$router.push('/my-profile')">My Profile</v-btn>
      <v-btn width="100px" @click.prevent="logout">Logout</v-btn>
    </div>
  </v-app>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import UserAPI from "@/api/user";

export default defineComponent({
  name: "HelloWorld",
  props: {
    msg: String
  },
  data() {
    return {
      title: "Assignment",
    };
  },
  methods: {
    async logout() {
      try {
        await UserAPI.logout();
      } catch (e) {
        console.log(e);
      } finally {
        this.$cookies.remove("accessToken");
        this.$cookies.remove("type");
        this.$cookies.remove("refreshToken");
        this.$router.push("/login");
      }
    }
  }
});
</script>
<style>
.app-bar {
  height: 50px;
  /* top: 0; */
  background-color: black;
  /* text-align: right; */
}
</style>

  