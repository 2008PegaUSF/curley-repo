package questionset;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import app.week1core.App;
import operations.Question;

// Q20 read a file and then neatly format its contents to the console
public class Q20Solution implements Question {

	public boolean readAFile(File file) {

		//does the file exist? if not, just exit out and save us time and exceptions
		if (!file.exists()) {
			System.out.println("File not found: " + file.getName());
			return false;
		}
		
		//try to do thing: read a file
		try {
			App.con = new Scanner(file);
			// Scanner fin = new Scanner(fsin);

			System.out.println("Reading file...");

			//while there is data
			while (App.con.hasNext()) {
				//print a neatly formatted string after splitting based on a token
				String t[] = App.con.next().split(":");
				System.out.println("Name: " + t[0] + " " + t[1]);
				System.out.println("Age: " + t[2]);
				System.out.println("State: " + t[3]);
			}

			return true;
		} catch (IOException e) {
			//we won't actually reach here if there is an exception, but Java demanded it so here we are.
			System.out.println(e.getMessage());
			return false;
		} finally {
			App.con.close();
		}
	}

	@Override
	public void performTask() {
		System.out.println("Question 20");
		System.out.println("Gathering a delimited file and displaying the contents in a neat fashion");
		readAFile(new File("Data.txt"));

	}
}
