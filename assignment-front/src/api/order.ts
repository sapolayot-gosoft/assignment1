import { AxiosError, AxiosResponse } from "axios";
import { SearchInput } from "../types/Input";
import axios from "./instance";

export default {
  getOrders: (data?: SearchInput) => {
    return axios.api.get(`/orders`, data).then(
      (response: AxiosResponse) => {
        return response.data;
      },
      (error: AxiosError) => {
        return error?.response?.data;
      }
    );
  },
};