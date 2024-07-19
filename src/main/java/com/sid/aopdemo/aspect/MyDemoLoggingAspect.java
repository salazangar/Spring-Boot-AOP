package com.sid.aopdemo.aspect;

import com.sid.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Before("com.sid.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeTheAddAccount(JoinPoint theJoinPoint){
        System.out.println("\n=====>>> Executing @Before advice on method addAccount()");

        // display the method signature
        MethodSignature signature = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method : " + signature);

        // display the method arguments
        Object[] args = theJoinPoint.getArgs();

        for(Object arg : args){
            System.out.println(arg);

            if(arg instanceof Account){

                // downcast and print Account specific stuff
                Account theAccount = (Account) arg;

                System.out.println("Account Name: " + theAccount.getName());
                System.out.println("Account Level: " + theAccount.getLevel());
            }
        }

    }


}
