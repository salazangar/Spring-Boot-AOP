package com.sid.aopdemo.dao;

import com.sid.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;

    private String serviceCode;

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {

        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork(){
        System.out.println(getClass() + "In doWork function");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + "In getName function");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + "In setName function");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + "In getServiceCode function");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + "In setServiceCode function");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        List<Account> myAccounts = new ArrayList<>();

        // create sample accounts
        Account temp1 = new Account("John", "Silver");
        Account temp2 = new Account("Maddy", "Gold");
        Account temp3 = new Account("Luca", "Diamond");

        // add them to accounts list
        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);

        return myAccounts;
    }
}
