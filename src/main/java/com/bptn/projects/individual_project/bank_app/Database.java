package com.bptn.projects.individual_project.bank_app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;





public class Database {

    public static final String databaseFileName = "mangobank.txt";

    public static Bank readBankFromFile(){
    	Bank bank = null;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(databaseFileName))) {
            bank = (Bank)in.readObject();
            in.close();

        } catch (FileNotFoundException e) {
            System.out.println("File " + databaseFileName + " was not found");
        } catch (IOException e) {
            System.out.println("Error reading contents from "+ databaseFileName);
        } catch (ClassNotFoundException e) {
            System.out.println("Bank class not found");
        }
            return bank;
    }

    public static void saveBankToFile(Bank bank){
    	try(FileOutputStream fileout = new FileOutputStream(databaseFileName);
                ObjectOutputStream objout = new ObjectOutputStream(fileout)){
                objout.writeObject(bank);
                objout.flush();
                
                fileout.close();
                objout.close();
                
    	} catch (FileNotFoundException e) {
            System.out.println("File " + databaseFileName + " was not found");
        } catch (IOException e) {
            System.out.println("Error reading contents from "+ databaseFileName);
        }
                
    }
}

