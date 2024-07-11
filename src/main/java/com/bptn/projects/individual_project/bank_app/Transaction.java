package com.bptn.projects.individual_project.bank_app;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

	private int id;
	private double amountInvolved;
	private AccountBalance accountBalance;
	private LocalDateTime timeOfTransaction;
	private double balanceBefore;
	private double balanceAfter;

	public Transaction(int id, double amountInvolved, AccountBalance accountBalance, LocalDateTime timeOfTransaction,
			double balanceBefore, double balanceAfter) {
		this.setId(id);
		this.setAmountInvolved(amountInvolved);
		this.setAccountBalance(accountBalance);
		this.setTimeOfTransaction(timeOfTransaction);
		this.setBalanceBefore(balanceBefore);
		this.setBalanceAfter(balanceAfter);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmountInvolved() {
		return amountInvolved;
	}

	public void setAmountInvolved(double amountInvolved) {
		this.amountInvolved = amountInvolved;
	}

	public AccountBalance getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(AccountBalance accountBalance) {
		this.accountBalance = accountBalance;
	}

	public LocalDateTime getTimeOfTransaction() {
		return timeOfTransaction;
	}

	public void setTimeOfTransaction(LocalDateTime timeOfTransaction) {
		this.timeOfTransaction = timeOfTransaction;
	}

	public double getBalanceAfter() {
		return balanceAfter;
	}

	public void setBalanceAfter(double balanceAfter) {
		this.balanceAfter = balanceAfter;
	}

	public double getBalanceBefore() {
		return balanceBefore;
	}

	public void setBalanceBefore(double balanceBefore) {
		this.balanceBefore = balanceBefore;
	}

}