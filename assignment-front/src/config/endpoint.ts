import { API } from "../types/API";

const mode = process.env.API_MODE || "local";
let config = {} as API;

if (mode === "local") {
  config = {
    ENDPOINT_URL: "http://localhost:8080",
  };
}

export default config;