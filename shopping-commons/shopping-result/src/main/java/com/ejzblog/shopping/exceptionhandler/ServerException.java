package com.ejzblog.shopping.exceptionhandler;

/**
 * <p>
 * Description：表示服务器异常 这个异常类应当记录日志 或者打印异常堆栈
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-03-28 10:23
 * @see com.ejzblog.shopping.exceptionhandler
 */
@SuppressWarnings("ALL")
public class ServerException extends RuntimeException {

    private static final long serialVersionUID = 3057035610156638419L;

    private String detailMessage;

    /**
     * 当发生异常时，需要记录异常堆栈，因此需要传入cause
     *
     * @param message 这个信息会返回到用户界面
     * @param cause   导致错误的Exception
     */
    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 当项目出现的一些异常需要记录上下文变量时，用detailMessage记录
     *
     * @param message       错误信息，这个信息会发送给用户看到
     * @param detailMessage 记录上下文详细信息使用
     */
    public ServerException(String message, String detailMessage) {
        super(message);
        this.detailMessage = detailMessage;
    }

    /**
     * 当异常需要详细信息同时也需要堆栈时候，用这个方法
     *
     * @param message       错误信息，这个信息会发送给用户看到
     * @param detailMessage 记录上下文详细信息使用
     * @param cause         Throwable错误
     */
    public ServerException(String message, String detailMessage, Throwable cause) {
        super(message, cause);
        this.detailMessage = detailMessage;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

}
