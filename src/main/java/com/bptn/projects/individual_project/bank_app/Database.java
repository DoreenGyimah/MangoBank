package com.bptn.projects.individual_project.bank_app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

public class Database {

	// declaring databaseFileName to be a constant that cannot be changed and
	// assigns mangobank.txt to databaseFileName.
	public static final String databaseFileName = "mangobank.txt";

	public static Bank readBankFromFile() {
		Bank bank = null;
		// nested try-catch block with a resource statement which closes resources when
		// no longer needed
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(databaseFileName))) {
			bank = (Bank) in.readObject();
			in.close();

		} catch (FileNotFoundException e) {
			System.out.println("File " + databaseFileName + " was not found");
		} catch (IOException e) {
			System.out.println("Error reading contents from " + databaseFileName);
		} catch (ClassNotFoundException e) {
			System.out.println("Bank class not found");
		}
		return bank;
	}

	// nested try-catch block with a resource statement which closes resources when
	// no longer needed. FileOutStream write the databaseFileName file.
	// ObjectOutputStream writes serialized objects to output stream.
	public static void saveBankToFile(Bank bank) {
		try (FileOutputStream fileout = new FileOutputStream(databaseFileName);
				ObjectOutputStream objout = new ObjectOutputStream(fileout)) {
			objout.writeObject(bank);
			objout.flush();

			fileout.close();
			objout.close();

		} catch (FileNotFoundException e) {
			System.out.println("File " + databaseFileName + " was not found");
		} catch (IOException e) {
			System.out.println("Error reading contents from " + databaseFileName);
		}

	}
}
