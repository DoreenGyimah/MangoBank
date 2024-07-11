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

public class UserController {

	AccountBalanceController accountBalanceController;
	CustomerController customerController;
	LoginController loginController;
	TransactionController transactionController;
	Scanner scanner;

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
        switch (option ) {
            case 1 : {
                user = doLogin();
                break;
            }
            case 2 : {
                user = doRegister();
                break;
            }
            case 3 : {
                System.out.println("Thank you for banking with us");
                System.exit(0);
            }
        }
	}

	private User doRegister() {
		scanner.nextLine();
		System.out.print("Enter your first name: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter your last name: ");
		String lastName = scanner.nextLine();
		String emailAddress = "";
		String input = scanner.nextLine();

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
		scanner.nextLine();
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

}
