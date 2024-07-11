package com.bptn.projects.individual_project;


import com.bptn.projects.individual_project.bank_app.Bank;
import com.bptn.projects.individual_project.bank_app.AccountBalance;
import com.bptn.projects.individual_project.bank_app.Transaction;
import com.bptn.projects.individual_project.bank_app_controllers.UserController;
import com.bptn.projects.individual_project.bank_app.Database;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* Bank bank = Database.readBankFromFile();*/
	     UserController gui = new UserController(null);
	}

}
