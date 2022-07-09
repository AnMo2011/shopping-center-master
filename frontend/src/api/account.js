import request from '@/utils/request'

/**
 * PC端-查询平台账户信息列表
 * @param username 用户名
 */
export function accountListApi(data) {
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
export function updateAccountUseStatusApi(id) {
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

/**
 * 上传图片
 * @param {图片} data 
 * @returns 
 */
export function uploadImgApi(data) {
  return request({
    url: '/oss-do',
    method: 'post',
    data
  })
}

/**
 * 删除图片
 * @param {图片地址} data 
 * @returns 
 */
export function delImgApi(data) {
  return request({
    url: '/oss-do?url=' + data,
    method: 'delete',

  })
}