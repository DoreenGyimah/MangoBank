package com.bptn.projects.individual_project.bank_app_controllers;

import com.bptn.projects.individual_project.bank_app.Bank;
import com.bptn.projects.individual_project.bank_app.AccountBalance;
import com.bptn.projects.individual_project.bank_app.Transaction;
import com.bptn.projects.individual_project.bank_app.Database;

import java.time.LocalDateTime;
import java.util.Random;

public class AccountBalanceController extends Controller {
	private final String DEPOSIT = "DEPOSIT";
    private final String WITHDRAW = "WITHDRAW";
	public AccountBalanceController(Bank bank) {
		super(bank);
	}

	public void deposit(AccountBalance account, double amountInvolved) {
		// if account is not null, update balance with amount given
		if (account != null) {
			double balanceBefore = account.getBalance();
			// update balance
			account.setBalance(account.getBalance() + amountInvolved);
			// create a Transaction for this operation and save in Bank
			int id = TransactionController.generateNewTransactionID(getBank());
			//
			LocalDateTime timeOfTransaction = LocalDateTime.now();
			//
			double balanceAfter = account.getBalance();
			//
			String operation = DEPOSIT;
			// create a new Transaction and save it to database
			Transaction transaction = new Transaction(id, amountInvolved, account, timeOfTransaction, balanceBefore,
					balanceAfter, operation);
			getBank().getAllTransactions().add(transaction);
			Database.saveBankToFile(getBank());
		} else
			throw new NullPointerException("Account balance is null");

	}

	public void withdraw(AccountBalance account, double amountInvolved) throws Exception {
		// if account is not null, check if balance is greater or equal to amount
		// if not funded, throw Exception
		// if funded, update balance with debit amount
		// save bank to file
		// if account is not null, update balance with amount given
		if (account != null) {
			double balanceBefore = account.getBalance();
			// check account is funded
			if ((account.getBalance() - amountInvolved) < 0) {
				throw new Exception("Your balance is inadeuate for this withdrawal of " + amountInvolved);
			}
			int id = TransactionController.generateNewTransactionID(getBank());
			// update balance
			account.setBalance(account.getBalance() - amountInvolved);
			// create a Transaction for this operation and save in Bank
			LocalDateTime timeOfTransaction = LocalDateTime.now();
			//
			double balanceAfter = account.getBalance();
			//
			String operation = WITHDRAW;
			// create a new Transaction and save it to database
			Transaction transaction = new Transaction(id, amountInvolved, account, timeOfTransaction, balanceBefore,
					balanceAfter, operation);
			getBank().getAllTransactions().add(transaction);
			Database.saveBankToFile(getBank());
		} else
			throw new NullPointerException("Account balance is null");

	}
	
	public void createAccountBalance(AccountBalance balance) {
		getBank().getAllBalances().add(balance);
	}

	public double viewBalance(AccountBalance account) {
		// if account is not null, return the balance from it
		if (account != null) {
			return account.getBalance();
		} else
			throw new NullPointerException("Account balance is null");
	}

	public static int generateNewAccountNumber(Bank bank) {
		Random random = new Random();
		int accountNumber = 0;
		do {
			accountNumber = random.nextInt(100000, 1000000);
		} while (accountNumberExists(accountNumber, bank));
		return accountNumber;
	}

	public int getGenerateNewAccountNumber() {
		return generateNewAccountNumber(getBank());
	}

	private static boolean accountNumberExists(int accountNumber, Bank bank) {
		return bank.getAllBalances().stream().anyMatch((balance) -> balance.getAccountNumber() == accountNumber);
	}
}
