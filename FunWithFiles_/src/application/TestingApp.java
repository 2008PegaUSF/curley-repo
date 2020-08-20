package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class TestingApp {

	private static MyClass myMember = new MyClass();
	
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		File file = new File("out2.txt");
		try {
			if(file.exists()) {
				ReadFromFile(file);
				System.out.println("A String: " + myMember.getSomeString());
				System.out.println("An Integer: " + myMember.getSomeNum());
				System.out.println("A Character: " + myMember.getSomeChar());
			}
			else {
				System.out.print("Please enter a String: ");
				String aString = console.nextLine();
				System.out.print("Please enter an integer: ");
				int anInt = Integer.parseInt(console.next());
				System.out.print("Please enter a letter: ");
				char aChar = console.next().charAt(0);
				myMember.setSomeString(aString);
				myMember.setSomeNum(anInt);
				myMember.setSomeChar(aChar);
				WriteToFile(file);
			}
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		console.close();
	}
	
	public static boolean WriteToFile(File file) throws IOException{
		FileOutputStream fout = new FileOutputStream(file);
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		
		oout.writeObject(myMember.getSomeString());
		oout.writeInt(myMember.getSomeNum());
		oout.writeChar(myMember.getSomeChar());
		
		System.out.println("Successfully wrote to file: " + file.getName());
		
		oout.close();
		fout.close();
		
		return true;
	}
	
	public static boolean ReadFromFile(File file) throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream(file);
		ObjectInputStream oin = new ObjectInputStream(fin);
		
		myMember.setSomeString((String)oin.readObject());
		myMember.setSomeNum(oin.readInt());
		myMember.setSomeChar(oin.readChar());
		
		oin.close();
		fin.close();
		
		System.out.println("Successfully read from file: " + file.getName());
		
		return true;
	}

}
