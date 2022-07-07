import request from '@/utils/request'

export function login(data) {
  return request({
    // url: '/vue-admin-template/user/login',
    url: '/admin-account-do/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    // url: '/vue-admin-template/user/info',
    url: '/admin-account-do/get-account-info-by-token',
    method: 'get'
  })
}

export function logout(token) {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post',
    params: { access_token: token }
  })
}
