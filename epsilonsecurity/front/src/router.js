import Vue from 'vue'
import VueRouter from 'vue-router'
import MyFeed from './components/MyFeed.vue';
import MySchedule from './components/MySchedule.vue';
import MyProfile from './components/MyProfile.vue';
// todo: import other components here
import NotFound from './components/NotFound.vue';
import store from './store/store'

Vue.use(VueRouter)

const router = new VueRouter({
	mode: 'history', // caveats. see https://router.vuejs.org/en/essentials/history-mode.html
  routes: [
		{ 
			path: '/', 
			component: MyFeed,
			meta: { 
				requiresAuth: true,
				adminOnly: false 
			}
		},
		{ 
			path: '/my-schedule', 
			component: MySchedule, 
			meta: { 
				requiresAuth: true,
				adminOnly: false 
			}
		},
		{ 
			path: '/manage-teams', 
			component: MySchedule, // todo: create component
			meta: { 
				requiresAuth: true,
				adminOnly: true 
			}
		},
		{ 
			path: '/my-profile', 
			component: MyProfile, // todo: create component
			meta: { 
				requiresAuth: true,
				adminOnly: false 
			}
		},
		// todo: other routes here
		{ 
			path: '*', 
			component: NotFound 
		},
	]
})

router.beforeEach((to, from, next) => {
	// if this particular route requires an authenticated user
	const pageRequiresAuth = to.matched.some(record => record.meta.requiresAuth)
	const isLoggedIn = store.getters.isLoggedIn
	
	if (pageRequiresAuth && !isLoggedIn) {
			next("/login")
	} else {
		next()
	}
})

export default router
