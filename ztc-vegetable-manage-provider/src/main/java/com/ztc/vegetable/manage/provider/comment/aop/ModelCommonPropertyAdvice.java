package com.ztc.vegetable.manage.provider.comment.aop;

import com.ztc.vegetable.manage.provider.comment.util.DateUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class ModelCommonPropertyAdvice {
    private static final Log logger = LogFactory.getLog(ModelCommonPropertyAdvice.class);

    @Pointcut("execution(public * com.ztc.vegetable.manage.db.mapper.*.insert*(..))||execution(public * com.ztc.vegetable.manage.db.mapper.*.update*(..))")
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        if (args.length != 2) {
            Object model = args[0];
            if (StringUtils.isNotBlank(methodName) && methodName.startsWith("insert")) {
                // 插入方法
                setCreateField(model);
            }
            if (StringUtils.isNotBlank(methodName) && methodName.startsWith("update")) {
                // 修改方法
                setModifyField(model);
            }
        }
        return joinPoint.proceed();
    }

    /**
     * 设置数据模型的created_date、created_user、modified_user、modified_date、is_delete字段 （其中operator的值设置为全局session中的值） :用于插入方法
     *
     * @author quanjd_sinosoft
     * @date 2018年11月6日-上午11:39:15
     * @param object
     *            数据模型
     */
    public static void setCreateField(Object object) {
        // 获得当前系统时间
        Date currDate = DateUtil.getNowDate();
        //
        String defultUser = "SYSTEM";
        // 开始处理
        setCreatedDate(object, currDate);
        setModifiedDate(object, currDate);
        setCreatedUser(defultUser, object);
        setModifiedUser(defultUser, object);
        setIsDelete("0", object);
    }

    /**
     * 设置数据模型的modifydate、modifytime字段:用于修改方法
     * @date 2018年11月6日-上午11:39:15
     * @param object
     *            数据模型
     */
    public static void setModifyField(Object object) {
        // 获得当前系统时间
        Date currDate = DateUtil.getNowDate();
        // 默认操作用户
        String defultUser = "SYSTEM";
        // 开始设值
        setModifiedDate(object, currDate);
        setModifiedUser(defultUser, object);
    }

    /**
     * 设置createdDate属性值
     *
     * @date 2018年11月6日-上午11:39:15
     * @param object
     * @param date
     */
    private static void setCreatedDate(Object object, Date date) {
        Class<?> modelClass = object.getClass();
        // 设置CreatedDate字段
        try {
            Method getMakeDate = modelClass.getDeclaredMethod("getCreatedDate");
            Date makeDate = (Date) getMakeDate.invoke(object);
            if (makeDate == null) {
                Method setMakeDate = modelClass.getDeclaredMethod("setCreatedDate", Date.class);
                if (setMakeDate != null) {
                    setMakeDate.invoke(object, date);
                }
            }
        } catch (NoSuchMethodException e) {
            // 没有该字段不用处理
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("set makedate failed");
        }
    }

    /**
     * 设置modifiedDate属性值
     *
     * @date 2018年11月6日-上午11:39:15
     * @param object
     * @param date
     */
    private static void setModifiedDate(Object object, Date date) {
        Class<?> modelClass = object.getClass();
        // 更新modifiedDate字段
        try {
            Method setMakeDate = modelClass.getDeclaredMethod("setModifiedDate", Date.class);
            if (setMakeDate != null) {
                setMakeDate.invoke(object, date);
            }
        } catch (NoSuchMethodException e) {
            // 没有该字段不用处理
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("set modifiedDate failed");
        }
    }

    /**
     * 设置isDelete的值
     *
     * @date 2018年11月6日-上午11:39:15
     * @param object
     */
    private static void setIsDelete(String isDelete, Object object) {
        try {
            Class<?> modelClass = object.getClass();
            Method getOperator = modelClass.getDeclaredMethod("getIsDelete");
            String deletFlag = (String) getOperator.invoke(object);
            if (deletFlag == null) {
                Method setOperator = modelClass.getDeclaredMethod("setIsDelete", String.class);
                setOperator.invoke(object, isDelete);
            }
        } catch (NoSuchMethodException e) {
            // 没有该字段不用处理
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("set isDelete failed");
        }

    }

    /**
     * 设置创建者
     *
     * @date 2018年11月6日-上午11:39:15
     * @param object
     */
    private static void setCreatedUser(String defultUser, Object object) {
        try {
            Class<?> modelClass = object.getClass();
            Method getCreater = modelClass.getDeclaredMethod("getCreatedUser");
            String creater = (String) getCreater.invoke(object);
            if (StringUtils.isBlank(creater)) {
                Method setCreater = modelClass.getDeclaredMethod("setCreatedUser", String.class);
                if (setCreater != null) {
                    setCreater.invoke(object, defultUser);
                }
            }
        } catch (NoSuchMethodException e) {
            // 没有该字段不用处理
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("set createdUser failed");
        }
    }

    /**
     * setModifiedUser
     * @param defultUser
     * @param object
     */
    private static void setModifiedUser(String defultUser, Object object) {
        try {
            Class<?> modelClass = object.getClass();
            Method getModifier = modelClass.getDeclaredMethod("getModifiedUser");
            String modifier = (String) getModifier.invoke(object);
            if (StringUtils.isBlank(modifier)) {
                Method setModifier = modelClass.getDeclaredMethod("setModifiedUser", String.class);
                if (setModifier != null) {
                    setModifier.invoke(object, defultUser);
                }
            }
        } catch (NoSuchMethodException e) {
            // 没有该字段不用处理
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("set modifiedUser  failed");
        }
    }

}
