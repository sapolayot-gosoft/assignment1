<template>
  <v-app fixed app class="app-bar px-4">
    <v-toolbar-title class="">{{ title }}</v-toolbar-title>
    <v-spacer />
    <v-btn icon @click.prevent="logout">
      <v-icon>mdi-logout</v-icon>
    </v-btn>
  </v-app>
</template>
<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "HelloWorld",
  props: {
    msg: String
  },
  data() {
    return {
      title: "Assignment"
    };
  },
  methods: {
    logout() {
      this.$root.$emit("logout");
    }
  }
});
</script>
<style>
.app-bar {
  height: 50px;
  background-color: red;
  text-align: right;
}
</style>

  