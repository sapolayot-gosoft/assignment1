<template>
  <div class="wrapper d-flex">
    <div class="background" />
    <div class="input-wrapper centered px-4">
      <h2>ระบบ Simple E-commerce</h2>
      <div class="input-container mt-6 mb-6">
        <v-form ref="form" lazy-validation @submit.prevent="submitLogin">
          <v-text-field label="ชื่อผู้ใช้ (Username)" v-model="form.username" hide-details="auto"></v-text-field>
          <v-text-field
            type="password"
            label="รหัสผ่าน (Password)"
            v-model="form.password"
            hide-details="auto"
          ></v-text-field>
          <div class="btn-group mt-4">
            <v-btn class="mr-2" variant="outlined" color="green" type="submit">Login</v-btn>
            <v-btn variant="outlined" color="blue" @click="dialog = true">Register</v-btn>
          </div>
        </v-form>
        <v-dialog v-model="dialog" width="50%">
          <v-card>
            <div class="text-center pa-6">
              <h1 class="mb-8">ลงทะเบียน</h1>
              <v-form ref="form" lazy-validation @submit.prevent="submitRegister">
                <v-text-field
                  type="text"
                  label="ชื่อผู้ใช้"
                  placeholder="ชื่อผู้ใช้"
                  :required="true"
                  v-model="formRegister.username"
                />
                <v-text-field
                  type="text"
                  label="เบอร์โทร"
                  placeholder="เบอร์โทร"
                  :required="true"
                  v-model="formRegister.phone"
                />
                <v-text-field
                  type="password"
                  label="รหัสผ่าน"
                  placeholder="รหัสผ่าน"
                  :required="true"
                  v-model="formRegister.password"
                />
                <v-text-field
                  type="password"
                  label="ยืนยันรหัสผ่าน"
                  placeholder="ยืนยันรหัสผ่าน"
                  :required="true"
                  v-model="formRegister.rePassword"
                />
                <div class="d-flex text-center">
                  <v-btn type="submit" text="ตกลง" color="green" class="text-white" width="50%" />
                  <v-spacer></v-spacer>
                  <v-btn
                    text="ยกเลิก"
                    color="red"
                    class="text-white"
                    @click="dialog = false"
                    width="50%"
                  />
                </div>
              </v-form>
            </div>
          </v-card>
        </v-dialog>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import UserAPI from "@/api/user";
import { LoginUserInput, CreateOrUpdateUserInput } from "@/types/Input";

export default defineComponent({
  name: "HomeView",
  data() {
    return {
      form: {
        username: "",
        password: ""
      } as LoginUserInput,
      formRegister: {
        username: "",
        phone: "",
        password: "",
        rePassword: ""
      } as CreateOrUpdateUserInput,
      dialog: false
    };
  },
  methods: {
    async submitLogin() {
      // const validate = (this.$refs.form as Vue & {
      //   validate: () => boolean;
      // }).validate();
      // if(validate){

      // }

      const resp = await UserAPI.login(this.form);
      if (resp) {
        if (!resp.subject) {
          this.$cookies.set("accessToken", resp.accessToken);
          this.$cookies.set("refreshToken", resp.refreshToken);
          this.$cookies.set("type", resp.type);
          this.$router.push("/manage-list");
        } else {
          alert(resp.message)
          // this.errorServerMessage = resp.message;
          // setTimeout(() => {
          //   this.errorServerMessage = "";
          // }, 10000);
        }
      }
    },
    async submitRegister() {
      // alert("register")
      const resp = await UserAPI.register(this.formRegister);
      alert(`resp${resp.data}`);
    }
  }
});
</script>
<style scoped>
.background {
  background-color: black;
  height: 100vh;
  width: 60%;
}
.input-wrapper {
  width: 40%;
}
.centered {
  position: fixed; /* or absolute */
  top: 30%;
  left: 60%;
}
</style>
