package com.ztc.vegetable.manage.provider.comment.exception;

/**
 * 调用保险公司异常
 * 
 * @author: wangyw_sinosoft 2016年3月3日-下午4:07:27
 * 
 */
public class CallRemoteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CallRemoteException() {
        super();
    }

    public CallRemoteException(String msg) {
        super(msg);
    }

    /**
     * if(expression) throw new InsurerCallException(message);
     * 
     * @param expression
     * @param message
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new CallRemoteException(message);
        }
    }

    /**
     * if(!expression) throw new InsurerCallException(message);
     * 
     * @param expression
     * @param message
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new CallRemoteException(message);
        }
    }
}
