package com.bptn.projects.individual_project.bank_app_controllers;

import com.bptn.projects.individual_project.bank_app.Bank;
import com.bptn.projects.individual_project.bank_app.Customer;
import com.bptn.projects.individual_project.bank_app.Database;

import java.util.List;
import java.util.Random;

public class CustomerController extends Controller {

	public CustomerController(Bank bank) {
		super(bank);
	}

	public void registerNewCustomer(Customer customer) {
		if (customer != null) {
			// add customer to bank object
			getBank().getAllCustomers().add(customer);
			// save bank to file
			Database.saveBankToFile(getBank());
		}

	}

	public Customer getCustomerByEmail(String email) throws Exception {
		// loop bank's customer for anyone with same email
		// if found, return customer
		// if not found, throw CustomerNotFoundException
		Customer matchedCustomer = getAllCustomers().stream()
				.filter((customer) -> customer.getEmailAddress().equals(email)).findFirst().orElse(null);
		if (matchedCustomer == null) {
			throw new Exception("No customer found with email: " + email);
		}
		return matchedCustomer;
	}

	public static String generateNewPassword() {
		// use Random object to generate a number between 10,000 and 99,999
		// return generated number
		Random random = new Random();
		return String.valueOf(random.nextInt(10000, 100000));
	}

	public List<Customer> getAllCustomers() {
		// return all bank's customers
		return getBank().getAllCustomers();
	}
}
