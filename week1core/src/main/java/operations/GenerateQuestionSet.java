package operations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

//Do you like java files?
//would you like to make 20 at once with some garbage at the head of the file?
//this one does all of that work for you.  do not run this unless you wish to
//have the need to clean up the base directory
public class GenerateQuestionSet {
	public static void main(String[] args) {
		for (int i = 1; i < 20; i++) {
			createFile(i + 1);
		}
	}

	public static void createFile(int currentQ) {
		File file = new File(".\\src\\operations\\Q" + currentQ + "Solution.java");
		try {
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeChars("package operations;\r\n" + 
					"\r\n" + 
					"public class Q" + currentQ + "Solution implements Question {\r\n" + 
					"\r\n" + 
					"	@Override\r\n" + 
					"	public void perform(Object... args) {\r\n" + 
					"		\r\n" + 
					"	}\r\n" + 
					"}\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
