package com.sid.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.sid.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    // create a pointcut for the getter methods
    @Pointcut("execution(* com.sid.aopdemo.dao.*.get*(..))")
    private void getter() {}

    // create a pointcut for the setter methods
    @Pointcut("execution(* com.sid.aopdemo.dao.*.set*(..))")
    private void setter() {}

    // create pointcut: include package .... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {}


    @Before("forDaoPackageNoGetterSetter()")
    public void beforeTheAddAccount(){
        System.out.println("\n=====>>> Executing @Before advice on method addAccount()");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics(){
        System.out.println("\n========>>> Performing API analytics");
    }

}
