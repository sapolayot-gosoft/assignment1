import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
// import moment  from "moment"

import APIInstance from './plugins/APIInstance'

const vuetify = createVuetify({
  components,
  directives,
})

import VueCookies from 'vue-cookies'


const app = createApp(App)
// app.use(require('vue-moment'), {
//   moment
// })
app.use(store)
app.use(VueCookies)
app.use(router)
app.use(vuetify)
app.use(APIInstance)
app.mount("#app")

// createApp(App).use(store).use(router).use(vuetify).mount('#app')

