<template>
  <div class="wrapper">
    <Navbar />
    <div class="d-flex">
      <div class="avatar-wrapper ma-6">
        <v-avatar size="200px">
          <v-img alt="Avatar" :src="profileImage"></v-img>
        </v-avatar>
      </div>
      <div class="profile-wrapper pa-4">
        <v-form ref="form" lazy-validation @submit.prevent="submitEditProfile">
          <v-text-field
            label="ชื่อผู้ใช้ (Username)"
            v-model="form.username"
            hide-details="auto"
            disabled
          ></v-text-field>
          <v-text-field type="text" label="เบอร์โทร" v-model="form.phone" hide-details="auto"></v-text-field>
          <v-text-field
            type="text"
            label="ขื่อ-นามสกุล"
            v-model="form.fullName"
            hide-details="auto"
          ></v-text-field>
          <v-text-field
            type="password"
            label="รหัสผ่านเดิม"
            v-model="form.oldPassword"
            hide-details="auto"
          ></v-text-field>
          <v-text-field
            type="password"
            label="รหัสผ่านใหม่"
            v-model="form.newPassword"
            hide-details="auto"
          ></v-text-field>
          <v-text-field
            type="password"
            label="ยืนยันรหัสผ่านใหม่"
            v-model="form.renewPassword"
            hide-details="auto"
          ></v-text-field>
          <div class="btn-group mt-4">
            <v-btn type="submit" text="บันทึก" color="green" class="text-white" />
            <v-spacer></v-spacer>
            <v-btn text="ค่าเดิม" color="red" class="text-white" @click="fetchProfile()" />
          </div>
        </v-form>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import Navbar from "../components/layout/Navbar.vue";
// import UserAPI from "@/api/user";
import useAuth from "@/middleware/auth";
const { user } = useAuth();

export default defineComponent({
  name: "MyProfileView",
  components: {
    Navbar
  },
  data() {
    return {
      form: {
        username: "",
        phone: "",
        fullName: "",
        oldPassword: "",
        newPassword: "",
        renewPassword: ""
      },
      profileImage: ""
    };
  },
  mounted() {
    this.fetchProfile();
  },
  methods: {
    async fetchProfile() {
      // const id = this.$cookies.get("id");
      const data = user.value ? user.value as any : "";
      // const { data } = await UserAPI.getProfile(id);
      this.form = {
        username: data.username,
        phone: data.phone,
        fullName: data.fullName,
        newPassword: "",
        oldPassword: "",
        renewPassword: ""
      };
      this.profileImage = data.profileImage;
    },
    async submitEditProfile() {
      // const { data } = await UserAPI.editProfile({ fullName: });
    }
  }
});
</script>
<style scoped>
.profile-wrapper {
  width: 100%;
}
</style>