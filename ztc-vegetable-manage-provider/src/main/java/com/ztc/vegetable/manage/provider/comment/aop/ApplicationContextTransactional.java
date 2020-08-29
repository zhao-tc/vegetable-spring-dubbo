package com.ztc.vegetable.manage.provider.comment.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * springboot 之 注解式声明事务,使用声明式事务配置
 *
 * @author Administrator
 */
@Configuration
public class ApplicationContextTransactional {

    //事务方法超时时间设置
    private static final int TX_METHOD_TIMEOUT = 10;

    //AOP切面的切点表达式
    private static final String AOP_POINTCUT_EXPRESSION = "execution(public * com.ztc.vegetable.manage.db.mapper.*.*(..)) or execution(public * com.ztc.vegetable.manage.provider.*.dao.*(..))";

    //注入事务管理器
    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * 增强(事务)的属性的配置
     * isolation:DEFAULT  :事务的隔离级别.
     * propagation              :事务的传播行为.
     * read-only                        :false.不是只读
     * timeout                          :-1
     * no-rollback-for         :发生哪些异常不回滚
     * rollback-for                    :发生哪些异常回滚事务
     *
     * @return
     */
    @Bean
    public TransactionInterceptor txAdvice() {
        /*增强(事务)的属性的配置
         * <tx:attributes>
         * */
        NameMatchTransactionAttributeSource txAttributeS = new NameMatchTransactionAttributeSource();
        /*propagation="REQUIRED" , timeout=5 ;rollback-for=".. , .."配置*/
        RuleBasedTransactionAttribute requiredAttr = new RuleBasedTransactionAttribute();
        requiredAttr.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredAttr.setTimeout(TX_METHOD_TIMEOUT);
        requiredAttr.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(RuntimeException.class)));

        /*propagation="SUPPORTS" , readOnly="true"配置*/
        RuleBasedTransactionAttribute supportsAttr = new RuleBasedTransactionAttribute();
        supportsAttr.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
        supportsAttr.setReadOnly(true);
           /*
            注意：方法名称来自类匹配的到方法【save*, “*”表示匹配任意個字符】
             <tx:method .../>
            */
        Map<String, TransactionAttribute> txMethod = new HashMap<String, TransactionAttribute>();
        txMethod.put("add*", requiredAttr);
        txMethod.put("save*", requiredAttr);
        txMethod.put("insert*", requiredAttr);
        txMethod.put("del*", requiredAttr);
        txMethod.put("mod*", requiredAttr);
        txMethod.put("update*", requiredAttr);
        txMethod.put("up*", requiredAttr);
        txMethod.put("remove*", requiredAttr);
        txMethod.put("set*", requiredAttr);
        txMethod.put("change*", requiredAttr);
        txMethod.put("logout*", requiredAttr);
        txMethod.put("login*", requiredAttr);
        txMethod.put("check*", requiredAttr);
        txMethod.put("tiJiao*", requiredAttr);
        txMethod.put("get*", supportsAttr);
        txMethod.put("find*", supportsAttr);
        txMethod.put("query*", supportsAttr);
        txMethod.put("select*", supportsAttr);
        txMethod.put("init*", supportsAttr);
        txMethod.put("timing*", requiredAttr);
        txMethod.put("execute*", requiredAttr);
        txMethod.put("send*", requiredAttr);
        txMethod.put("batch*", requiredAttr);
        txAttributeS.setNameMap(txMethod);
        return new TransactionInterceptor(transactionManager, txAttributeS);
    }

    /**
     * AOP配置定义切面和切点的信息
     *
     * @return
     */
    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
