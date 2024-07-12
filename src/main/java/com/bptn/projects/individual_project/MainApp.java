package com.bptn.projects.individual_project;

import com.bptn.projects.individual_project.bank_app.Bank;
import com.bptn.projects.individual_project.bank_app.Database;
import com.bptn.projects.individual_project.bank_app.Admin;
import com.bptn.projects.individual_project.bank_app_controllers.UserController;

public class MainApp {

	public static void main(String[] args) {

		/*
		 * Bank bank = new Bank(); Admin admin = new Admin("Admin", "Security",
		 * "admin@mangobank.com", "admin"); bank.getAllAdmin().add(admin);
		 */

		Bank bank = Database.readBankFromFile();
		UserController gui = new UserController(bank);
	}

}
