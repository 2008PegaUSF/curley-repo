package operations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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
			oo.writeObject("package operations;\r\n" + 
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
