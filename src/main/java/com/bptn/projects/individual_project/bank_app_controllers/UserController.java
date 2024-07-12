package com.bptn.projects.individual_project.bank_app_controllers;

import com.bptn.projects.individual_project.bank_app.User;
import com.bptn.projects.individual_project.bank_app.Bank;
import com.bptn.projects.individual_project.bank_app.Customer;
import com.bptn.projects.individual_project.bank_app.Admin;
import com.bptn.projects.individual_project.bank_app.AccountBalance;
import com.bptn.projects.individual_project.bank_app.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class UserController {

	AccountBalanceController accountBalanceController;
	CustomerController customerController;
	LoginController loginController;
	TransactionController transactionController;
	Scanner scanner;
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public UserController(Bank bank) {
		// initialize the controllers with the Bank object
		accountBalanceController = new AccountBalanceController(bank);
		customerController = new CustomerController(bank);
		loginController = new LoginController(bank);
		transactionController = new TransactionController(bank);
		scanner = new Scanner(System.in);

		User user = null;
		int option = -1;

		System.out.println("** Welcome to MangoBank **");
		option = optionsLogin();
		System.out.println(option);
		switch (option) {
		case 1: {
			user = doLogin();
			break;
		}
		case 2: {
			user = doRegister();
			break;
		}
		case 3: {
			System.out.println("Thank you for banking with us");
			System.exit(0);
		}
		}
		if (user instanceof Customer) {
			option = -1;
			do {
				System.out.println("** Welcome to MangoBank **");
				option = optionsCustomer();
				switch (option) {
				case 1: {
					doDeposit((Customer) user);
					break;
				}
				case 2: {
					doWithdraw((Customer) user);
					break;
				}
				case 3: {
					doViewBalance((Customer) user);
					break;
				}
				case 4: {
					System.out.println("Thank you for banking with us");
					System.exit(0);
				}
				}
			} while (true);

		} else if (user instanceof Admin) {
			option = -1;
			do {
				System.out.println("** Welcome to MangoBank **");
				option = optionsAdmin();
				switch (option) {
				case 1: {
					viewCustomer();
					break;
				}
				case 2: {
					viewAllCustomers();
					break;
				}
				case 3: {
					viewAllTransactions();
					break;
				}
				case 4: {
					System.out.println("Thank you for banking with us");
					System.exit(0);
				}
				}
			} while (true);
		}
	}

	private User doRegister() {
		scanner.nextLine();
		System.out.print("Enter your first name: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter your last name: ");
		String lastName = scanner.nextLine();
		String emailAddress = "";
		// String input = scanner.nextLine();

		do {
			System.out.print("Enter your email address: ");
			emailAddress = scanner.nextLine();
		} while (doesCustomerWithSameEmailExist(emailAddress));

		System.out.print("Enter your password: ");
		String password = scanner.nextLine();
		Customer customer = new Customer(firstName, lastName, emailAddress, password);
		AccountBalance balance = new AccountBalance(accountBalanceController.getGenerateNewAccountNumber(), customer,
				0.0);
		accountBalanceController.createAccountBalance(balance);
		customer.setAccountBalance(balance);

		customerController.registerNewCustomer(customer);
		return customer;
	}

	private boolean doesCustomerWithSameEmailExist(String emailAddress) {
		Customer customer = null;
		try {
			customer = customerController.getCustomerByEmail(emailAddress);
			return customer != null;
		} catch (Exception e) {
			System.out.println("No Customer exists with this email.");
			return false;
		}

	}

	private User doLogin() {
		scanner.nextLine();
		User user = null;
		do {
			System.out.print("Enter your email address: ");
			String email = scanner.nextLine();
			System.out.print("Enter your password: ");
			String password = scanner.nextLine();

			try {
				user = loginController.login(email, password);
				if (user == null) {
					System.out.println("Invalid email or password");
				}
			} catch (Exception e) {
				System.out.println("Error logging in" + e.getMessage());
			}
		} while (user == null);

		return user;
	}

	final int optionsLogin() {
		// scanner.nextLine();
		int option = 0;
		do {
			System.out.println("1. Login");
			System.out.println("2. Register new Customer");
			System.out.println("3. Exit");
			System.out.print("Select an option: ");
			option = scanner.nextInt();
		} while (isNotValidOptionLogin(option));
		return option;
	}

	public boolean isNotValidOptionLogin(int option) {
		if (option < 1 || option > 3) {
			System.out.println("Invalid option. Select 1,2 or 3");
			return true;
		}
		return false;
	}

	private void doViewBalance(Customer user) {
		double balance = user.getAccountBalance().getBalance();
		System.out.println("Your balance is : $" + balance);
	}

	private void doWithdraw(Customer user) {
		double amount = -1;
		boolean validNumber = false;
		scanner.nextLine();

		while (!validNumber) {
			System.out.print("Enter your withdrawal amount (a non-negative number): ");
			amount = scanner.nextDouble();
			if (amount >= 0) {
				validNumber = true;
			} else {
				System.out.println("The amount must be positive. Please try again.");
			}
		}

		try {
			accountBalanceController.withdraw(user.getAccountBalance(), amount);
			System.out.println("Withdrawal of $" + amount + " was successful");
		} catch (Exception e) {
			System.out.println("Withdrawal cannot be processed" + e.getMessage());
		}
	}

	private void doDeposit(Customer customer) {
		double amount = -1;
		boolean validNumber = false;
		scanner.nextLine();

		while (!validNumber) {
			System.out.print("Enter your deposit amount (a non-negative number): ");
			amount = scanner.nextDouble();
			if (amount >= 0) {
				validNumber = true;
			} else {
				System.out.println("The amount must be positive. Please try again.");
			}

		}
		accountBalanceController.deposit(customer.getAccountBalance(), amount);
		System.out.println("Deposit of $" + amount + " was successful");
	}

	private int optionsCustomer() {
		int option = 0;
		do {
			System.out.println("1. Deposit");
			System.out.println("2. Withdraw");
			System.out.println("3. View balance");
			System.out.println("4. Exit");
			System.out.print("Select an option: ");
			option = scanner.nextInt();
		} while (isNotValidOptionCustomer(option));
		return option;
	}

	private boolean isNotValidOptionCustomer(int option) {
		if (option < 1 || option > 4) {
			System.out.println("Invalid option. Select 1,2.3 or 4");
			return true;
		}
		return false;
	}

	private void viewAllTransactions() {
		boolean validStartDate = false;
		boolean validEndDate = false;
		LocalDate startDate = null;
		LocalDate endDate = null;

		scanner.nextLine();
		while (!validStartDate) {
			System.out.print("Enter your start date (yyyy-MM-dd): ");
			String input = scanner.nextLine();
			try {
				startDate = LocalDate.parse(input, dateFormatter);
				validStartDate = true;
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
			}
		}

		while (!validEndDate) {
			System.out.print("Enter your start date (yyyy-MM-dd): ");
			String input = scanner.nextLine();
			try {
				endDate = LocalDate.parse(input, dateFormatter);
				validEndDate = true;
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
			}
		}

		List<Transaction> allTransactions = transactionController.viewAllAccountByPeriod(startDate, endDate);
		System.out.printf("%-20s%-30s%-20s%-20s%-20s%-20s%-20s%n", "Transaction ID", "Account name", "Operation",
				"Amount", "Operation", "Transaction Time", "Balance before", "Balance after");
		for (Transaction transaction : allTransactions) {
			System.out.printf("%-20d%-30s%-20s%-20.2f%-20s%-20.2f%-20.2f%n", transaction.getId(),
					transaction.getAccountBalance().getOwner().getEmailAddress(), transaction.getOperation(),
					transaction.getAmountInvolved(), transaction.getTimeOfTransaction().format(dateFormatter),
					transaction.getBalanceBefore(), transaction.getBalanceAfter());
		}
	}

	private void viewAllCustomers() {
		System.out.printf("%-20s%-20s%-30s%-20s%n", "First name", "Last name", "Email address", "Account balance");
		List<Customer> customers = customerController.getAllCustomers();
		for (Customer customer : customers) {
			System.out.printf("%-20s%-20s%-30s%-20.2f%n", customer.getFirstName(), customer.getLastName(),
					customer.getEmailAddress(), customer.getAccountBalance().getBalance());
		}
	}

	private void viewCustomer() {
		System.out.print("Enter the customer's email address: ");
		String email = scanner.next();
		try {
			Customer customer = customerController.getCustomerByEmail(email);
			System.out.printf("%-20s:%s%n", "First name", customer.getFirstName());
			System.out.printf("%-20s:%s%n", "Last name", customer.getLastName());
			System.out.printf("%-20s:%s%n", "Email address", customer.getEmailAddress());
			System.out.printf("%-20s:%.2f%n", "Account balance", customer.getAccountBalance().getBalance());
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private int optionsAdmin() {
		int option = 0;
		do {
			System.out.println("1. View Customer");
			System.out.println("2. View All Customers");
			System.out.println("3. View All Transactions by period");
			System.out.println("4. Exit");
			System.out.print("Select an option: ");
			option = scanner.nextInt();
		} while (isNotValidOptionAdmin(option));
		return option;
	}

	private boolean isNotValidOptionAdmin(int option) {
		if (option < 1 || option > 4) {
			System.out.println("Invalid option. Select 1,2.3 or 4");
			return true;
		}
		return false;
	}
}
