package com.bptn.projects.individual_project.bank_app;

import java.util.ArrayList;
import java.io.Serializable;


public class Bank implements Serializable {
    private ArrayList<Customer> allCustomers;
    private ArrayList<Admin> allAdmin;
    private ArrayList<Transaction> allTransactions;
    private ArrayList<AccountBalance> allBalances;

    public Bank(){
        this.allCustomers = new ArrayList<>();
        this.allAdmin = new ArrayList<>();
        this.allTransactions = new ArrayList<>();
        this.allBalances = new ArrayList<>();
    }

    public ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public ArrayList<Admin> getAllAdmin() {
        return allAdmin;
    }

    public ArrayList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public ArrayList<AccountBalance> getAllBalances() {
        return allBalances;
    }
}