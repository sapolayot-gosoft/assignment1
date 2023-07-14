<template>
  <div class="wrapper">
    <Navbar />
    <div class="d-flex mt-6">
      <div class="flex-1-0">
        <ManageList
          title="Product"
          title-btn="เพิ่มสินค้า"
          placeholder="SKU หรือ ชื่อสินค้า"
          :items="productList"
          :search="productSearch"
          @editItem="editProduct"
          @deleteItem="deleteItem"
        />
      </div>
      <div class="divider"></div>
      <div class="flex-1-0">
        <ManageList
          title="Order"
          title-btn="สร้างคำสั่งซื้อ"
          placeholder="เลขที่คำสั่งซ์้อ"
          :items="orderList"
          :search="orderSearch"
        />
      </div>
    </div>
    <!-- <v-dialog v-model="productDialog" width="50%">
      <v-card>
        <div class="text-center pa-6">
          <h1 class="mb-8">เพิ่มสินค้า</h1>
          <v-form ref="form" lazy-validation @submit.prevent="submitRegister">
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
                @click="productDialog = false"
                width="50%"
              />
            </div>
          </v-form>
        </div>
      </v-card>
    </v-dialog> -->
    <v-dialog v-model="orderDialog" width="50%">
      <v-card>
        <div class="text-center pa-6">
          <h1 class="mb-8">สร้างคำสั่งซื้อ</h1>
          <!-- <v-form ref="form" lazy-validation @submit.prevent="submitRegister">
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
          </v-form>-->
        </div>
      </v-card>
    </v-dialog>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import ManageList from "@/components/ManageList.vue";
import Navbar from "@/components/layout/Navbar.vue";
import ProductAPI from "@/api/product";
import OrderAPI from "@/api/order";
import { ProductUpdateInput } from "../types/Input";

export default defineComponent({
  name: "ManageListView",
  components: {
    ManageList,
    Navbar
  },
  data() {
    return {
      productList: [],
      orderList: [],
      productSearch: "",
      orderSearch: "",
    };
  },
  mounted() {
    this.fetchProduct();
    this.fetchOrder();
  },
  methods: {
    async fetchProduct() {
      const resp = await ProductAPI.getProducts();
      this.productList = resp.data;
    },
    async fetchOrder() {
      const resp = await OrderAPI.getOrders();
      this.orderList = resp.data;
    },
    async editProduct(formProduct: ProductUpdateInput) {
      const resp = await ProductAPI.editProduct(formProduct);
    },
    async deleteItem(id: any) {
      const resp = await ProductAPI.deleteProduct({ id: id });
      if (resp.code === 200) this.fetchProduct();
    }
  }
});
</script>
<style scoped>
.divider {
  border: 1px solid black;
}
</style>