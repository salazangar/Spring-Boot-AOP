package com.sid.aopdemo.aspect;

import com.sid.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    // add a new advice for @AfterReturning on findAccounts() method

    @AfterReturning(
            pointcut = "execution(* com.sid.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the result of method call
        System.out.println("\n======>>> result is : " + result);

    }


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
