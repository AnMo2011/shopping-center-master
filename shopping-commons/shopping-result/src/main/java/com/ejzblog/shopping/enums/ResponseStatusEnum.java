package com.ejzblog.shopping.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * Description：错误信息 枚举
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-05 14:15
 * @see com.ejzblog.shopping.enums
 */
@SuppressWarnings("ALL")
@Getter
@AllArgsConstructor
public enum ResponseStatusEnum {


    UNKNOWN_ERROR_TYPE(-1, "未知错误", "未知错误"),

    SERVICE_TEMPORARILY_UNAVAILABLE(-2, "服务暂时不可用", "服务暂时不可用"),

    OK(HttpStatus.OK.value(), "操作成功", "操作成功"),

    NO_PERMISSION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "暂无权限", "暂无权限"),

    NULL_POINTER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请求参数不能为空", "请求参数不能为空"),
    NUMBER_FORMAT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "数字格式异常", "数字格式异常"),
    FILE_SIZE_LIMIT_EXCEEDED(HttpStatus.INTERNAL_SERVER_ERROR.value(), "上传文件超出限制", "上传文件超出限制"),
    WRONG_REQUEST_TYPE(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请求类型错误", "请求类型错误"),

    TOKEN_PARSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Token解析错误", "Token解析错误"),
    TOKEN_EXPIRED(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Token已过期", "Token已过期"),
    TOKEN_NULL(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Token不能为空", "Token不能为空"),

    INCORRECT_USERNAME_OR_PASSWORD(HttpStatus.INTERNAL_SERVER_ERROR.value(), "账号或密码错误", "账号或密码错误"),
    ACCOUNT_IS_DISABLED(HttpStatus.INTERNAL_SERVER_ERROR.value(), "帐户已被禁用", "帐户已被禁用"),


    ;

    /**
     * 状态值
     */
    private Integer code;

    /**
     * 谨慎定义,暴露给用户的错误提醒
     */
    private String message;

    /**
     * 给后端人员提示
     */
    public String details;

}
