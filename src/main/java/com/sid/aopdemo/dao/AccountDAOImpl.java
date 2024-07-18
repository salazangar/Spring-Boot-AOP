package com.sid.aopdemo.dao;

import com.sid.aopdemo.Account;
import org.springframework.stereotype.Repository;

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
}
