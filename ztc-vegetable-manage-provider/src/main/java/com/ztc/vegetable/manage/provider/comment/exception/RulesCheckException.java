package com.ztc.vegetable.manage.provider.comment.exception;



import io.micrometer.core.instrument.util.StringUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * 规则效验异常
 *
 * @author: ztc
 * @date: 2018年9月21日-下午1:40:09
 * @version:
 */
public class RulesCheckException extends TradeException {

    private static final long serialVersionUID = 1L;

    public RulesCheckException() {
        super();
    }

    public RulesCheckException(String msg) {
        super(msg);
    }

    /**
     * if(expression) throw new RulesCheckException(message);
     * 
     * @param expression
     * @param message
     * @throws RulesCheckException
     */
    public static void isFalse(boolean expression, String message) throws RulesCheckException {
        if (expression) {
            throw new RulesCheckException(message);
        }
    }

    /**
     * if(!expression) throw new RulesCheckException(message);
     * 
     * @param expression
     * @param message
     * @throws RulesCheckException
     */
    public static void isTrue(boolean expression, String message) throws RulesCheckException {
        if (!expression) {
            throw new RulesCheckException(message);
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
            throw new RulesCheckException(message);
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
            throw new RulesCheckException(message);
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
            throw new RulesCheckException(message);
        }
    }
}
