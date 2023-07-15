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
          @createProduct="createProduct"
          @editProduct="editProduct"
          @deleteItem="deleteItem"
        />
      </div>
      <div class="divider"></div>
      <div class="flex-1-0">
        <ManageList
          title="Order"
          title-btn="สร้างคำสั่งซื้อ"
          placeholder="เลขที่คำสั่งซื้อ"
          :items="orderList"
          :search="orderSearch"
        />
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import ManageList from "@/components/ManageList.vue";
import Navbar from "@/components/layout/Navbar.vue";
import ProductAPI from "@/api/product";
import OrderAPI from "@/api/order";
import { ProductCreateOrUpdateInput } from "../types/Input";

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
      orderSearch: ""
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
    async createProduct(formProduct: ProductCreateOrUpdateInput) {
      const resp = await ProductAPI.createProduct(formProduct);
      if (resp.code === 200) this.fetchProduct();
    },
    async editProduct(formProduct: ProductCreateOrUpdateInput) {
      const resp = await ProductAPI.editProduct(formProduct);
      if (resp.code === 200) this.fetchProduct();
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