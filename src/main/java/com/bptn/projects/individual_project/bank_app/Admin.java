package com.bptn.projects.individual_project.bank_app;

// declare admin class and inherit properties from user
public class Admin extends User {
	public Admin(String firstName, String lastName, String emailAddress, String password) {
		super(firstName, lastName, emailAddress, password);
	}
}
