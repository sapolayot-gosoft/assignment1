<template>
  <div class="wrapper">
    <v-row no-gutters>
      <v-col>
        <v-text-field
          class="ml-10"
          type="text"
          :label="label"
          :placeholder="placeholder"
          v-model="search"
        />
      </v-col>
      <v-col>
        <v-btn type="submit" text="ค้นหา" color="blue" class="text-black" width="100px" />
      </v-col>
    </v-row>
    <v-row class="mb-6" no-gutters>
      <v-col>{{ title }}</v-col>
      <v-col>
        <v-btn
          type="button"
          :text="titleBtn"
          @click="dialog = true"
          color="orange"
          class="text-black"
          width="100px"
        />
      </v-col>
    </v-row>
    <v-row no-gutters v-for="(item, index) of items" :key="index">
      <template v-if="title==='Product'">
        <v-col>{{ item.sku + " | " + item.name }}</v-col>
        <v-col>
          <v-btn
            variant="text"
            type="button"
            @click="editItem(item.id)"
            text="แก้ไข"
            class="text-black"
            width="100px"
          />
          <v-btn
            variant="text"
            type="button"
            @click="deleteItem(item.id)"
            text="ลบ"
            class="text-black"
            width="100px"
          />
        </v-col>
      </template>
      <template v-else>
        <v-col>{{ item.orderNo + " | " + moment(item.orderDate).format("DD-MM-YYYY") }}</v-col>
        <v-col>
          <p>{{ statusEnum.getOrderStatus(item.orderStatus) }}</p>
        </v-col>
      </template>
    </v-row>
    <template v-if="title === 'Product'">
      <v-dialog v-model="dialog" width="700px">
        <v-card>
          <div class="text-center pa-6">
            <h1 class="mb-8">เพิ่มสินค้า</h1>
            <v-form ref="form" lazy-validation @submit.prevent>
              <v-text-field
                type="text"
                label="SKU"
                placeholder="SKU"
                :required="true"
                v-model="formProduct.SKU"
              />
              <v-text-field
                type="number"
                label="ราคาสินค้า"
                placeholder="ราคาสินค้า"
                :required="true"
                v-model="formProduct.price"
              />
              <v-text-field
                type="text"
                label="ชื่อสินค้า"
                placeholder="ชื่อสินค้า"
                :required="true"
                v-model="formProduct.name"
              />
              <v-textarea
                label="รายละเอียดสินค้า"
                placeholder="รายละเอียดสินค้า"
                :required="true"
                v-model="formProduct.detail"
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
    </template>
    <template v-else>
      <v-dialog v-model="dialog" width="700px">
        <v-card>
          <div class="text-center pa-6">
            <h1 class="mb-8">สร้างคำสั่งซื้อ</h1>
            <v-form ref="form" lazy-validation @submit.prevent>
              <v-select
                label="ผู้ขาย"
                :items="['California', 'Colorado', 'Florida', 'Georgia', 'Texas', 'Wyoming']"
              ></v-select>
              <v-select
                label="สินค้า"
                :items="['California', 'Colorado', 'Florida', 'Georgia', 'Texas', 'Wyoming']"
              ></v-select>
              
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
    </template>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import moment from "moment";
import statusEnum from "@/utils/enum";
import { ProductUpdateInput } from "../types/Input";

export default defineComponent({
  name: "ManageList",
  props: {
    title: String,
    titleBtn: String,
    titleFunc: Function,
    label: String,
    placeholder: String,
    search: String,
    searchFunc: Function,
    items: Object
  },
  data() {
    return {
      statusEnum: statusEnum,
      moment: moment,
      search: "",
      formProduct: {
        id: null,
        SKU: "",
        price: 0,
        name: "",
        detail: ""
      } as ProductUpdateInput,
      dialog: false
    };
  },
  methods: {
    editItem(id: any) {
      if (this.title === "Product") {
        this.$emit("editProduct", this.formProduct);
      }
    },
    deleteItem(id: any) {
      this.$emit("deleteItem", id);
    }
  }
});
</script>
<style scoped>
.wrapper {
  height: 100vh;
}
</style>