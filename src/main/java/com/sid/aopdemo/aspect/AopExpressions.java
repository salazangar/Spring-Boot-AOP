package com.sid.aopdemo.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class AopExpressions {

    @Pointcut("execution(* com.sid.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}

    // create a pointcut for the getter methods
    @Pointcut("execution(* com.sid.aopdemo.dao.*.get*(..))")
    public void getter() {}

    // create a pointcut for the setter methods
    @Pointcut("execution(* com.sid.aopdemo.dao.*.set*(..))")
    public void setter() {}

    // create pointcut: include package .... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}

}
