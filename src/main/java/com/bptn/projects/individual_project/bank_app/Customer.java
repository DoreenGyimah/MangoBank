package com.bptn.projects.individual_project.bank_app;

public class Customer extends User {
	private AccountBalance balance;

	public Customer(String firstName, String lastName, String emailAddress, String password) {
		super(firstName, lastName, emailAddress, password);

	}

	public AccountBalance getAccountBalance() {
		return balance;
	}

	public void setAccountBalance(AccountBalance balance) {
		this.balance = balance;
	}

}
