package com.ejzblog.shopping.exceptionhandler;

/**
 * <p>
 * Description：权限异常
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-03-28 10:23
 * @see com.ejzblog.shopping.exceptionhandler
 */
public class RightException extends DataException {

    private static final long serialVersionUID = 3494062174581005192L;

    public RightException() {
        super();
    }

    public RightException(String message, String savedMessage) {
        super(message, savedMessage);
    }

}
