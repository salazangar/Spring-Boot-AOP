package com.sid.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with a @Before advice

//    @Before("execution(public void add*())")

//    @Before("execution(* add*())")

    @Before("execution(* add*(com.sid.aopdemo.Account, ..))")
    public void beforeTheAddAccount(){

        System.out.println("\n=====>>> Executing @Before advice on method addAccount()");
    }

}
