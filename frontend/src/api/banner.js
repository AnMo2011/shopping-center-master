import request from '@/utils/request'

/**
 * PC端-查询平台Banner图列表
 * @param data 标题
 */
export function bannerListApi(data) {
    return request({
      url: '/banner-do',
      method: 'get',
      params: data
    })
}

/**
 * 创建
 * @param username 用户名
 */
export function createApi(data) {
  return request({
    url: '/banner-do',
    method: 'post',
    data: data
  })
}

/**
 * 按照ID查询详情
 * @param username 用户名
 */
export function getdetailsByIdApi(id) {
  return request({
    url: '/banner-do/' + id,
    method: 'get'
  })
}

/**
 * 修改
 * @param username 用户名
 */
 export function updateByIdApi(data) {
  return request({
    url: '/banner-do',
    method: 'put',
    data: data
  })
}

/**
 * 按照ID查询详情
 * @param username 用户名
 */
export function removeByIdApi(id) {
  return request({
    url: '/banner-do/' + id,
    method: 'delete'
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
