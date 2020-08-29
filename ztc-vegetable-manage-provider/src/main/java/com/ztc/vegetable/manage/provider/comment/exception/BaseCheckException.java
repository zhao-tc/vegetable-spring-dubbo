package com.ztc.vegetable.manage.provider.comment.exception;

import io.micrometer.core.instrument.util.StringUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * 基础效验异常
 *
 * @author: fengxiao_sinosoft
 * @date: 2018年9月21日-下午1:38:24
 * @version:
 */
public class BaseCheckException extends TradeException {

    private static final long serialVersionUID = 1L;

    public BaseCheckException() {
        super();
    }

    public BaseCheckException(String msg) {
        super(msg);
    }

    /**
     * if(expression) throw new BaseCheckedException(message);
     * 
     * @param expression
     * @param message
     * @throws BaseCheckException
     */
    public static void isFalse(boolean expression, String message) throws BaseCheckException {
        if (expression) {
            throw new BaseCheckException(message);
        }
    }

    /**
     * if(!expression) throw new BaseCheckedException(message);
     * 
     * @param expression
     * @param message
     * @throws BaseCheckException
     */
    public static void isTrue(boolean expression, String message) throws BaseCheckException {
        if (!expression) {
            throw new BaseCheckException(message);
        }
    }
    
    /**
     * Description: 校验参数是否为null
     * 
     * @author zhangjj_sinosoft
     * @Date 2016年10月22日 上午9:57:22
     * @Version 1.0.0
     * @param reference
     * @param message
     */

    public static void checkNotNull(Object reference, String message) {
        if (reference == null) {
            throw new BaseCheckException(message);
        }
    }


    /**
     * Description: 校验参数是否为null
     *
     * @author zhangjj_sinosoft
     * @Date 2016年10月22日 上午9:57:22
     * @Version 1.0.0
     * @param reference
     * @param message
     */

    public static void checkNull(Object reference, String message) {
        if (reference != null) {
            throw new BaseCheckException(message);
        }
    }
    /**
     * Description: 校验参数是否为空
     * 
     * @author zhangjj_sinosoft
     * @Date 2016年10月22日 上午9:57:38
     * @Version 1.0.0
     * @param reference
     * @param message
     */
    public static void checkNotEmpty(String reference, String message) {
        if (StringUtils.isBlank(reference)) {
            throw new BaseCheckException(message);
        }
    }

    /**
     * Description: 校验参数是否为空
     * 
     * @author zhangjj_sinosoft
     * @Date 2016年10月22日 上午9:57:38
     * @Version 1.0.0
     * @param reference
     * @param message
     */
    public static void collectionNotEmpty(Collection<?> reference, String message) {
        if (CollectionUtils.isEmpty(reference)) {
            throw new BaseCheckException(message);
        }
    }

}
