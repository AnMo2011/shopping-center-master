import request from '@/utils/request'

/**
 * PC端-查询平台账户信息列表
 * @param username 用户名
 */
export function accountList(data) {
  return request({
    url: '/admin-account-do',
    method: 'get',
    params: data
  })
}

/**
 * 获取个人信息
 * @param username 用户名
 */
export function updateAccountUseStatus(id) {
  return request({
    url: '/admin-account-do/update-account-use-status/' + id,
    method: 'put'
  })
}

/**
 * 创建新的账户
 * @param username 用户名
 */
export function createAccountApi(data) {
  return request({
    url: '/admin-account-do/create',
    method: 'post',
    data: data
  })
}
