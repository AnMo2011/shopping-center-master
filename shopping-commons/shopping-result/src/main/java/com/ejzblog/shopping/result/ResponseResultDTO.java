package com.ejzblog.shopping.result;

import com.ejzblog.shopping.enums.ResponseStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Objects;

import static com.ejzblog.shopping.enums.ResponseStatusEnum.OK;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-03-28 10:24
 * @see com.ejzblog.shopping.result
 */
@SuppressWarnings("ALL")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResultDTO<T> implements Serializable {

    private static final long serialVersionUID = -2213100509067841157L;

    /**
     * 业务结果
     */
    @ApiModelProperty(value = "业务结果", position = 100)
    private T result;

    /**
     * 是否调用成功
     */
    @ApiModelProperty(value = "是否调用成功（true 成功 / false 失败）", position = 100)
    private Boolean success;

    /**
     * HTTP请求编码
     *
     * @see ResponseStatusEnum
     */
    @ApiModelProperty(value = "HTTP请求编码", position = 100)
    private Integer code;

    /**
     * 描述
     *
     * @see ResponseStatusEnum
     */
    @ApiModelProperty(value = "描述", position = 100)
    private String message;

    /**
     * 成功返回结果
     *
     * @param model T
     * @return json类型
     */
    public static <T> ResponseResultDTO<T> success() {
        return ResponseResultDTO.<T>builder()
                .success(true)
                .result(null)
                .code(OK.getCode())
                .message(OK.getMessage())
                .build();
    }

    /**
     * 成功返回结果
     *
     * @param model T
     * @return json类型
     */
    public static <T> ResponseResultDTO<T> success(T model) {
        return ResponseResultDTO.<T>builder()
                .success(true)
                .result(model)
                .code(OK.getCode())
                .message(OK.getMessage())
                .build();
    }

    /**
     * validator 自动验证参数返回message
     * 异常
     *
     * @param message 返回消息
     * @return json类型
     */
    public static <T> ResponseResultDTO<T> error(Integer code, String message) {
        return unSuccess(Objects.isNull(code) ? HttpStatus.INTERNAL_SERVER_ERROR.value() : code, message);
    }

    /**
     * 错误返回结果
     *
     * @param statusEnum 自定义消息 {@link ResponseStatusEnum}
     * @return json类型
     */
    public static <T> ResponseResultDTO<T> unSuccess(ResponseStatusEnum statusEnum) {
        return unSuccess(statusEnum.getCode(), statusEnum.getDetails());
    }

    private static <T> ResponseResultDTO<T> unSuccess(Integer code, String message) {
        return ResponseResultDTO.<T>builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }

}
