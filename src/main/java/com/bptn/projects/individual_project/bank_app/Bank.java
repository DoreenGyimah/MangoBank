package com.bptn.projects.individual_project.bank_app;

import java.util.ArrayList;
import java.io.Serializable;

//declare bank class and implement serializable (converts object to a stream to save into a file)
public class Bank implements Serializable {
	private ArrayList<Customer> allCustomers;
	private ArrayList<Admin> allAdmin;
	private ArrayList<Transaction> allTransactions;
	private ArrayList<AccountBalance> allBalances;

	// arraylist instances are created ready to store information from customers,
	// admin, transaction, and balances
	public Bank() {
		this.allCustomers = new ArrayList<>();
		this.allAdmin = new ArrayList<>();
		this.allTransactions = new ArrayList<>();
		this.allBalances = new ArrayList<>();
	}

	// getter method of customer arraylist to get allcustomers information
	public ArrayList<Customer> getAllCustomers() {
		return allCustomers;
	}

	// getter method of admin arraylist to get alladmin information
	public ArrayList<Admin> getAllAdmin() {
		return allAdmin;
	}

	// getter method of transaction arraylist to get alltransactions information
	public ArrayList<Transaction> getAllTransactions() {
		return allTransactions;
	}

	// getter method of accountbalance arraylist to get allaccountbalance
	// information
	public ArrayList<AccountBalance> getAllBalances() {
		return allBalances;
	}
}