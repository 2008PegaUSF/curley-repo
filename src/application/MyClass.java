package application;

import java.io.Serializable;

public class MyClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String someString;
	private int someNum;
	
	public MyClass() {
		someString = "";
		someNum = -1;
	}
	
	public MyClass(String s, int n) {
		someString = s;
		someNum = n;
	}
	
	public String getSomeString() {
		return someString;
	}

	public int getSomeNum() {
		return someNum;
	}

	public void setSomeNum(int someNum) {
		this.someNum = someNum;
	}

	public void setSomeString(String someString) {
		this.someString = someString;
	}
}
