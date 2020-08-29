package com.ztc.vegetable.manage.restful.comment.exception;

/**
 * 自定义交易异常
 *
 * @author: fengxiao_sinosoft
 * @date: 2018年9月29日-下午2:11:14
 * @version:
 */
public class TradeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TradeException() {
        super();
    }

    public TradeException(String msg) {
        super(msg);
    }

    /**
     * if(expression) throw new TradeException(message);
     * 
     * @param expression
     * @param message
     * @throws TradeException
     */
    public static void isFalse(boolean expression, String message) throws TradeException {
        if (expression) {
            throw new TradeException(message);
        }
    }

    /**
     * if(!expression) throw new TradeException(message);
     * 
     * @param expression
     * @param message
     * @throws TradeException
     */
    public static void isTrue(boolean expression, String message) throws TradeException {
        if (!expression) {
            throw new TradeException(message);
        }
    }

}
