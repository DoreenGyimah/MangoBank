package com.bptn.projects.individual_project.bank_app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Database {

    public static final String databaseFileName = "mangobank.txt";

    public static Bank readBankFromFile(){
    	Bank bank = null;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(databaseFileName))) {
            bank = (Bank)in.readObject();

        } catch (Exception e) {
            System.err.println("File " + databaseFileName + " was not found");
        }    
            return bank;
    }

    public static void saveBankToFile(Bank bank){
    	try(FileOutputStream fileout = new FileOutputStream(databaseFileName);
                ObjectOutputStream objout = new ObjectOutputStream(fileout)){
                objout.writeObject(bank);
    	} catch (Exception e) {
            System.err.println("File " + databaseFileName + " was not found");
                
    }
}
}
