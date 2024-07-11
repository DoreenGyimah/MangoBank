package com.bptn.projects.individual_project.bank_app_controllers;

import com.bptn.projects.individual_project.bank_app.Bank;

public abstract class Controller {
	private Bank bank;

	public Controller(Bank bank) {
		this.bank = bank;
	}

	public Bank getBank() {
		return bank;
	}
}
