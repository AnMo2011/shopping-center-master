package com.ejzblog.shopping.handler;

import com.ejzblog.shopping.exceptionhandler.DataException;
import com.ejzblog.shopping.exceptionhandler.RightException;
import com.ejzblog.shopping.exceptionhandler.ServerException;
import com.ejzblog.shopping.result.ResponseResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.servlet.http.HttpServletRequest;

import java.security.SignatureException;

import static com.ejzblog.shopping.enums.ResponseStatusEnum.FILE_SIZE_LIMIT_EXCEEDED;
import static com.ejzblog.shopping.enums.ResponseStatusEnum.NO_PERMISSION;
import static com.ejzblog.shopping.enums.ResponseStatusEnum.NULL_POINTER_EXCEPTION;
import static com.ejzblog.shopping.enums.ResponseStatusEnum.NUMBER_FORMAT_EXCEPTION;
import static com.ejzblog.shopping.enums.ResponseStatusEnum.TOKEN_PARSING_ERROR;
import static com.ejzblog.shopping.enums.ResponseStatusEnum.WRONG_REQUEST_TYPE;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-03-28 10:24
 * @see com.ejzblog.shopping.handler
 */
@SuppressWarnings("ALL")
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseResultDTO<Void> handleException(HttpServletRequest req, Exception e) {
        log.error("Catch an Exception Handler---Host: {} invokes url: {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        log.error("error detail:{}", e.getStackTrace());
        return ResponseResultDTO.error(null, e.getMessage());
    }

    /**
     * 系统运行时异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResultDTO<Void> handleRuntimeException(HttpServletRequest req, RuntimeException e) {
        log.error("Catch an RuntimeException Handler---Host: {} invokes url: {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        log.error("error detail:{}", e.getStackTrace());
        return ResponseResultDTO.error(null, e.getMessage());
    }

    /**
     * 表示一般的数据异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(DataException.class)
    public ResponseResultDTO<Void> handleDataException(HttpServletRequest req, DataException e) {
        log.error("Catch an DataException Handler---Host: {} invokes url: {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        log.error("error detail:{}", e.getStackTrace());
        return ResponseResultDTO.error(null, e.getMessage());
    }

    /**
     * 权限异常 如用户的权限不足 不打印堆栈
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(RightException.class)
    public ResponseResultDTO<Void> handleRightException(HttpServletRequest req, RightException e) {
        log.error("Catch an RightException Handler---Host: {} invokes url: {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        log.error("error detail:{}", e.getStackTrace());
        return ResponseResultDTO.unSuccess(NO_PERMISSION);
    }

    /**
     * 表示服务器异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(ServerException.class)
    public ResponseResultDTO<Void> handleServerException(HttpServletRequest req, ServerException e) {
        log.error("Catch an ServerException Handler---Host: {} invokes url: {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        log.error("error detail:{}", e.getStackTrace());
        return ResponseResultDTO.error(null, e.getMessage());
    }

    /**
     * 空指针异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseResultDTO<Void> handleNullPointerException(HttpServletRequest req, NullPointerException e) {
        log.error("Catch an NullPointerException Handler---Host: {} invokes url: {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        log.error("error detail:{}", e.getStackTrace());
        return ResponseResultDTO.unSuccess(NULL_POINTER_EXCEPTION);
    }

    /**
     * 方法参数校验
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResultDTO<Void> handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
        log.error("Catch an MethodArgumentNotValidException Handler---Host: {} invokes url: {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        log.error("error detail:{}", e.getStackTrace());
        return ResponseResultDTO.error(null, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 方法参数校验
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseResultDTO<Void> handleWebExchangeBindException(HttpServletRequest req, WebExchangeBindException e) {
        log.error("Catch an WebExchangeBindException Handler---Host: {} invokes url: {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        log.error("error detail:{}", e.getStackTrace());
        return ResponseResultDTO.error(null, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 数字格式异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseResultDTO<Void> handleNumberFormatException(HttpServletRequest req, NumberFormatException e) {
        log.error("Catch an NumberFormatException Handler---Host: {} invokes url: {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        log.error("error detail:{}", e.getStackTrace());
        return ResponseResultDTO.unSuccess(NUMBER_FORMAT_EXCEPTION);
    }

    /**
     * FileSizeLimitExceededException
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseResultDTO<Void> handleFileSizeLimitExceededException(HttpServletRequest req, FileSizeLimitExceededException e) {
        log.error("Catch an Exception Handler---Host: {} invokes url: {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        log.error("error detail:{}", e.getStackTrace());
        return ResponseResultDTO.unSuccess(FILE_SIZE_LIMIT_EXCEEDED);
    }

    /**
     * FileSizeLimitExceededException
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(SignatureException.class)
    public ResponseResultDTO<Void> handleSignatureException(HttpServletRequest req, SignatureException e) {
        log.error("Catch an Exception Handler---Host: {} invokes url: {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        log.error("error detail:{}", e.getStackTrace());
        return ResponseResultDTO.unSuccess(TOKEN_PARSING_ERROR);
    }

    /**
     * 请求类型错误
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResultDTO<Void> handleHttpRequestMethodNotSupportedException(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        log.error("Catch an WebExchangeBindException Handler---Host: {} invokes url: {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        log.error("error detail:{}", e.getStackTrace());
        return ResponseResultDTO.unSuccess(WRONG_REQUEST_TYPE);
    }

}
