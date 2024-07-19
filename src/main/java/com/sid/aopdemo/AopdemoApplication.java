package com.sid.aopdemo;

import com.sid.aopdemo.dao.AccountDAO;
import com.sid.aopdemo.dao.MembershipDAO;
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
	CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO){
		return runner ->{

//			demoTheBeforeAdvice(accountDAO, membershipDAO);
			demoTheAfterReturningAdvice(accountDAO);

		};
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
