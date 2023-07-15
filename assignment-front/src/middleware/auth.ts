import { ref } from 'vue'
import UserAPI from "@/api/user"
import { RouteLocationNormalized } from 'vue-router'

const user = ref(null)

export default () => {

  const checkAuth = async (to: RouteLocationNormalized) => {
    if (user.value)
      if (to.name === "login") {
        user.value = null;
        return false
      }

    try {
      const res = await UserAPI.getProfile(0);

      // if user is returned, it means that the cookie is valid,
      // and we are logged in
      if (res.data) {
        // set the user for later use
        user.value = res.data
        return true
      }
    } catch (err) {
      // do nothing
    }

    // if we get here, it means we are not logged in
    user.value = null;
    return false;
  }

  return {
    user,
    checkAuth
  }
}