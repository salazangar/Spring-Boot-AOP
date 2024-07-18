package com.sid.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Before("com.sid.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeTheAddAccount(){
        System.out.println("\n=====>>> Executing @Before advice on method addAccount()");
    }
}
