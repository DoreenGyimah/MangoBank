package com.bptn.projects.individual_project.bank_app;

import java.io.Serializable;

public class AccountBalance implements Serializable {
    private int accountNumber;// 5 digit number
    private Customer owner;
    private double balance;

    public AccountBalance(int accountNumber, Customer owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public Customer getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
