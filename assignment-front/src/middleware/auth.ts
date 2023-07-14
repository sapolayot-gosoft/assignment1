// import { Middleware } from "types";

const pathWithOutAuth = ["", "/", "/login"];

const auth: any = ({ app, route, redirect }: any) => {
  const token = app.$cookies.get("accessToken");
  console.log("token", token)
  if (!token) {
    if (!pathWithOutAuth.includes(route.matched[0].path)) {
      return redirect("/login");
    }
  } else {
    if (pathWithOutAuth.includes(route.matched[0].path)) {
      return redirect("/manage-list");
    }
  }
};

export default auth;
