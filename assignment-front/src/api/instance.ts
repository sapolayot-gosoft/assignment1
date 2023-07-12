import axios from "axios";
import apiConfig from "../config/endpoint";

let apiInstance = {
  api: null as any,
  context: null as any,
};

export const createInstance = (context: any) => {
  const { $cookies } = context;
  apiInstance.context = context;

  const type = $cookies.get("type") ? $cookies.get("type") : "";
  const accessToken = $cookies.get("accessToken")
    ? $cookies.get("accessToken")
    : "";
  const refreshToken = $cookies.get("refreshToken")
    ? $cookies.get("refreshToken")
    : "";

  const instance = axios.create({
    baseURL: apiConfig.ENDPOINT_URL,
    headers: {
      Authorization: `${accessToken ? `${type} ${accessToken}` : ""}`,
      refreshToken: refreshToken,
    },
  });

  instance.interceptors.request.use((config) => {
    const type = $cookies.get("type") ? $cookies.get("type") : "";
    const accessToken = $cookies.get("accessToken")
      ? $cookies.get("accessToken")
      : "";
    const refreshToken = $cookies.get("refreshToken")
      ? $cookies.get("refreshToken")
      : "";
    config.headers.Authorization = `${type} ${accessToken}`
    config.headers.refreshToken = refreshToken
    // config?.headers = {
    //   Authorization: `${type} ${accessToken}`,
    //   refreshToken: refreshToken,
    // };
    return config;
  });

  let refresh = false;
  instance.interceptors.response.use(
    (response) => {
      return response;
    },
    async (error) => {
      if (error.response.status === 401 && !refresh) {
        refresh = true;
        const { status, data } = await instance.post("/auth/refreshToken");
        if (status === 200) {
          if (data.accessToken) {
            $cookies.set("accessToken", data.accessToken);
            $cookies.set("refreshToken", data.refreshToken);
            $cookies.set("type", data.type);
            console.log("update token success");
          } else if (data.code === 401 && data.subject) {
            $cookies.remove("accessToken", data.accessToken);
            $cookies.remove("refreshToken", data.refreshToken);
            $cookies.remove("type", data.type);
            $cookies.set("errorMessage", data.message);
            console.log("Please login again");
          }
        }
      }
      refresh = false;
      return Promise.reject(error);
    }
  );
  apiInstance.api = instance;
};

export default apiInstance;