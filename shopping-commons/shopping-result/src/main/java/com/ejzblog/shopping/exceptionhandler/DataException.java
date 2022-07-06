package com.ejzblog.shopping.exceptionhandler;

/**
 * <p>
 * Description：表示一般的数据异常 比如账号不存在 密码错误之类的异常 这个异常类不需要打印堆栈 但可以视情况记录日志
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-03-28 10:23
 * @see com.ejzblog.shopping.exceptionhandler
 */
public class DataException extends RuntimeException {

    private static final long serialVersionUID = 7971133975151357940L;

    private String debugMsg;

    public DataException() {
        super();
    }

    /**
     * 数据异常
     *
     * @param message  用于描述异常的信息 这个信息最后会返回到用户的前端
     * @param debugMsg 用于记录日志时候所需的信息 这个信息会添加到日志文件中
     */
    public DataException(String message, String debugMsg) {
        super(message);
        this.debugMsg = debugMsg;
    }

    public String getDebugMsg() {
        return debugMsg;
    }

}
