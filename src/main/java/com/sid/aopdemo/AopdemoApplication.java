package com.sid.aopdemo;

import com.sid.aopdemo.dao.AccountDAO;
import com.sid.aopdemo.dao.MembershipDAO;
import com.sid.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AccountDAO accountDAO,
										MembershipDAO membershipDAO,
										TrafficFortuneService theTrafficFortuneService ){
		return runner ->{

//			demoTheBeforeAdvice(accountDAO, membershipDAO);
//			demoTheAfterReturningAdvice(accountDAO);
//			demoTheAfterThrowingAdvice(accountDAO);
//			demoTheAfterAdvice(accountDAO);
//			demoTheAroundAdvice(theTrafficFortuneService);
			demoTheAroundAdviceHandleException(theTrafficFortuneService);

		};
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdviceHandleException");

		System.out.println("Calling getFortune()");

		boolean tripWire = true;

		String data  = theTrafficFortuneService.getFortune(tripWire);

		System.out.println("\nThe fortune is: " + data);

		System.out.println("Finished");


	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdvice");

		System.out.println("Calling getFortune()");

		String data  = theTrafficFortuneService.getFortune();

		System.out.println("\nThe fortune is: " + data);

		System.out.println("Finished");

	}

	private void demoTheAfterAdvice(AccountDAO accountDAO) {
		// call method to find the accounts
		List<Account> theAccounts = null;

		try {
			// add a boolean flag to simulate exception
			boolean tripWire = true;
			theAccounts = accountDAO.findAccounts(tripWire);
		}
		catch (Exception e){
			System.out.println("\n\nMain Program: ... caught exception : " + e);
		}

		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterAdvice" );
		System.out.println("----");

		System.out.println(theAccounts);

		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {
		// call method to find the accounts
		List<Account> theAccounts = null;

		try {
			// add a boolean flag to simulate exception
			boolean tripWire = true;
			theAccounts = accountDAO.findAccounts(tripWire);
		}
		catch (Exception e){
			System.out.println("\n\nMain Program: ... caught exception : " + e);
		}

		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice" );
		System.out.println("----");

		System.out.println(theAccounts);

		System.out.println("\n");
	}

	private void demoTheAfterReturningAdvice(AccountDAO accountDAO) {

		// call method to find the accounts
		List<Account> theAccounts = accountDAO.findAccounts();

		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterReturningAdvice" );
		System.out.println("----");

		System.out.println(theAccounts);

		System.out.println("\n");
	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {

		Account theAccount = new Account();
		theAccount.setName("MAdgy");
		theAccount.setLevel("GOld");

		// call the business method
		accountDAO.addAccount(theAccount, true);
		accountDAO.doWork();

		// call the AccountDAO getter/setter methods
		accountDAO.setName("foobar");
		accountDAO.setServiceCode("silver");

		String name = accountDAO.getName();
		String code = accountDAO.getServiceCode();

		// call the membership business method
		membershipDAO.addSillyMember();
		membershipDAO.goToSleep();

	}
}
