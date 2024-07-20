package com.sid.aopdemo.aspect;

import com.sid.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.sid.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now execute the method
        Object result = null;

        try {
            result = theProceedingJoinPoint.proceed();
        }catch(Exception e){
            // log the exception
            System.out.println(e.getMessage());

            // give user a custom message
            result = "Major Accident! But don't worry, proceed as scheduled";
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n=====>>>> Duration : " + duration/1000.0 + " seconds");

        return result;
    }


    @After("execution(* com.sid.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint){

        // print out the method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.sid.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theException"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theException){

        // print out the method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("\n=====>>> The exception is : " + theException);
    }

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

        // let's post-process the data ... modify it

        // convert the account name to all upper case
        convertTheAccountNameToUpperCase(result);

        System.out.println("\n======>>> result is : " + result);
    }

    private void convertTheAccountNameToUpperCase(List<Account> result) {

        // loop through the accounts
        for(Account temp : result){

            // get uppercase version of account name
            String theUpperName = temp.getName().toUpperCase();

            // update the name on the account object
            temp.setName(theUpperName);
        }


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
