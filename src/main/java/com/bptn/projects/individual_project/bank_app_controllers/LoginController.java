package com.bptn.projects.individual_project.bank_app_controllers;

import com.bptn.projects.individual_project.bank_app.Bank;
import com.bptn.projects.individual_project.bank_app.User;
import com.bptn.projects.individual_project.bank_app.Customer;
import com.bptn.projects.individual_project.bank_app.Admin;
import com.bptn.projects.individual_project.bank_app.AccountBalance;

import java.time.LocalDate;
import java.util.Scanner;

public class LoginController extends Controller {
	public LoginController(Bank bank) {
		super(bank);
	}

	public User login(String emailAddress, String password) throws Exception {
		// check Bank for Customer with email Address
		// if customer not fount, check for Admin with email address
		// if admin not found, throw Exception
		// If user is found, return user
		Customer foundCustomer = getBank().getAllCustomers().stream()
				.filter((customer) -> customer.getEmailAddress().equals(emailAddress)
						&& customer.getPassword().equals(password))
				.findFirst().orElse(null);
		if (foundCustomer == null) {
			Admin foundAdmin = getBank().getAllAdmin().stream().filter(
					(admin) -> admin.getEmailAddress().equals(emailAddress) && admin.getPassword().equals(password))
					.findFirst().orElse(null);
			if (foundAdmin == null) {
				throw new Exception();
			} else {
				return foundAdmin;
			}
		}
		return foundCustomer;
	}
	
	
}
