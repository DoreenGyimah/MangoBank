package com.bptn.projects.individual_project.bank_app;

import java.io.Serializable;

// declare accountbalance class and implement serializable (converts object to a stream to save into a file)
public class AccountBalance implements Serializable {
	private int accountNumber;// 5 digit number
	private Customer owner;
	private double balance;

	// declare a constructor for accountbalance.
	public AccountBalance(int accountNumber, Customer owner, double balance) {
		this.accountNumber = accountNumber;
		this.owner = owner;
		this.balance = balance;
	}

	// getter method to retrieve the account number
	public int getAccountNumber() {
		return accountNumber;
	}

	// getter method to retrieve the customer and return value to owner variable
	public Customer getOwner() {
		return owner;
	}

	// getter method to retrieve the balance
	public double getBalance() {
		return balance;
	}

	// Set the value of balance to the instance variable for an object of the class
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
