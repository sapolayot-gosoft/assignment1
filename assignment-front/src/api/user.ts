import { AxiosError, AxiosResponse } from "axios";
import { CreateOrUpdateUserInput, LoginUserInput } from "../types/Input";
import axios from "./instance";

export default {
  register: (data: CreateOrUpdateUserInput) => {
    return axios.api.post(`/auth/register`, data).then(
      (response: AxiosResponse) => {
        return response.data;
      },
      (error: AxiosError) => {
        return error?.response?.data;
      }
    );
  },
  login: (data: LoginUserInput) => {
    return axios?.api.post(`/auth/login`, data).then(
      (response: AxiosResponse) => {
        return response.data;
      },
      (error: AxiosError) => {
        return error?.response?.data;
      }
    );
  },
  getProfile: (id: number) => {
    return axios?.api.get(`/user/${id}`).then(
      (response: AxiosResponse) => {
        return response.data;
      },
      (error: AxiosError) => {
        return error?.response?.data;
      }
    );
  },
  editProfile: (data: CreateOrUpdateUserInput) => {
    return axios?.api.put(`/user`,data).then(
      (response: AxiosResponse) => {
        return response.data;
      },
      (error: AxiosError) => {
        return error?.response?.data;
      }
    );
  },
  // uploadImage: (data: FormData) => {
  //   return axios.api
  //     .post(`/auth/user/uploadImage`, data, {
  //       headers: {
  //         "Content-Type": "multipart/form-data",
  //       },
  //     })
  //     .then(
  //       (response) => {
  //         return response.data;
  //       },
  //       (error) => {
  //         return error.response.data;
  //       }
  //     );
  // },
  // refreshToken: () => {
  //   return axios.api.post(`/auth/refreshToken`).then(
  //     (response) => {
  //       return response.data;
  //     },
  //     (error) => {
  //       return error.response.data;
  //     }
  //   );
  // },
  logout: () => {
    return axios.api.post(`/auth/logout`).then(
      (response: AxiosResponse) => {
        return response.data;
      },
      (error: AxiosError) => {
        return error?.response?.data;
      }
    );
  },
};