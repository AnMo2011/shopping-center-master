import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'el-icon-s-home' }
    }]
  },

  {
    path: '/ams',
    component: Layout,
    redirect: '/ams/account',
    name: 'Ams',
    meta: { title: '基础管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'account',
        name: 'Account',
        component: () => import('@/views/ams/account/index'),
        meta: { title: '平台账户管理', icon: 'dashboard' }
      },
      {
        path: 'banner',
        name: 'Banner',
        component: () => import('@/views/ams/banner/index'),
        meta: { title: '轮播图管理', icon: 'dashboard' }
      },
      {
        path: 'brand',
        name: 'Brand',
        component: () => import('@/views/ams/brand/index'),
        meta: { title: '品牌管理', icon: 'tree' }
      },
      {
        path: 'category',
        name: 'Category',
        component: () => import('@/views/ams/category/index'),
        meta: { title: '商品分类', icon: 'tree' }
      }
    ]
  },

  // {
  //   path: '/ims',
  //   component: Layout,
  //   redirect: '/ims/menus',
  //   name: 'Ims',
  //   meta: { title: '基础管理', icon: 'el-icon-setting' },
  //   children: [
  //     {
  //       path: 'menus',
  //       name: 'Menus',
  //       component: () => import('@/views/ims/menus/index'),
  //       meta: { title: '菜单管理', icon: 'el-icon-menu' }
  //     },
  //     {
  //       path: 'permission',
  //       name: 'Permission',
  //       component: () => import('@/views/ims/permission/index'),
  //       meta: { title: '权限管理', icon: 'tree' }
  //     },
  //     {
  //       path: 'role',
  //       name: 'Role',
  //       component: () => import('@/views/ims/role/index'),
  //       meta: { title: '角色管理', icon: 'el-icon-s-custom' }
  //     },
  //     {
  //       path: 'rolePermission',
  //       name: 'RolePermission',
  //       component: () => import('@/views/ims/role-permission/index'),
  //       meta: { title: '角色权限管理', icon: 'el-icon-s-custom' }
  //     },
  //     {
  //       path: 'userRole',
  //       name: 'UserRole',
  //       component: () => import('@/views/ims/user-role/index'),
  //       meta: { title: '用户角色', icon: 'el-icon-s-custom' }
  //     }
  //   ]
  // },

  // {
  //   path: '/ums',
  //   component: Layout,
  //   redirect: '/ums/user',
  //   name: 'Ums',
  //   meta: { title: '用户管理', icon: 'el-icon-user-solid' },
  //   children: [
  //     {
  //       path: 'user',
  //       name: 'User',
  //       component: () => import('@/views/ums/user/index'),
  //       meta: { title: '平台用户信息', icon: 'el-icon-user' }
  //     },
  //     {
  //       path: 'userMini',
  //       name: 'UserMini',
  //       component: () => import('@/views/ums/user-mini/index'),
  //       meta: { title: '第三方用户信息', icon: 'el-icon-user' }
  //     }
  //   ]
  // },

  // {
  //   path: '/sms',
  //   component: Layout,
  //   redirect: '/sms/shop',
  //   name: 'Sms',
  //   meta: { title: '店铺管理', icon: 'el-icon-s-shop' },
  //   children: [
  //     {
  //       path: 'shop',
  //       name: 'Shop',
  //       component: () => import('@/views/sms/shop/index'),
  //       meta: { title: '店铺信息', icon: 'el-icon-user' }
  //     },
  //     {
  //       path: 'shopAudit',
  //       name: 'ShopAudit',
  //       component: () => import('@/views/sms/shop-audit/index'),
  //       meta: { title: '店铺审核', icon: 'el-icon-edit' }
  //     }
  //   ]
  // },
  // {
  //   path: '/example',
  //   component: Layout,
  //   redirect: '/example/table',
  //   name: 'Example',
  //   meta: { title: 'Example', icon: 'el-icon-s-help' },
  //   children: [
  //     {
  //       path: 'table',
  //       name: 'Table',
  //       component: () => import('@/views/table/index'),
  //       meta: { title: 'Table', icon: 'table' }
  //     },
  //     {
  //       path: 'tree',
  //       name: 'Tree',
  //       component: () => import('@/views/tree/index'),
  //       meta: { title: 'Tree', icon: 'tree' }
  //     }
  //   ]
  // },

  // {
  //   path: '/form',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'Form',
  //       component: () => import('@/views/form/index'),
  //       meta: { title: 'Form', icon: 'form' }
  //     }
  //   ]
  // },

  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'Nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: () => import('@/views/nested/menu1/index'), // Parent router-view
  //       name: 'Menu1',
  //       meta: { title: 'Menu1' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       component: () => import('@/views/nested/menu2/index'),
  //       name: 'Menu2',
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },

  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
  //       meta: { title: 'External Link', icon: 'link' }
  //     }
  //   ]
  // },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
