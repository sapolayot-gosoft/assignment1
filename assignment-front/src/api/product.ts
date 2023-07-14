import { AxiosError, AxiosResponse } from "axios";
import { ProductDeleteInput, ProductUpdateInput, SearchInput } from "../types/Input";
import axios from "./instance";

export default {
  getProducts: (data?: SearchInput) => {
    return axios.api.get(`/products`, data).then(
      (response: AxiosResponse) => {
        return response.data;
      },
      (error: AxiosError) => {
        return error?.response?.data;
      }
    );
  },
  editProduct: (data?: ProductUpdateInput) => {
    return axios.api.put(`/product`, data).then(
      (response: AxiosResponse) => {
        return response.data;
      },
      (error: AxiosError) => {
        return error?.response?.data;
      }
    );
  },
  deleteProduct: (data: ProductDeleteInput) => {
    return axios.api.delete(`/product/${data.id}`).then(
      (response: AxiosResponse) => {
        return response.data;
      },
      (error: AxiosError) => {
        return error?.response?.data;
      }
    );
  },
};