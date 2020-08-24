package questionset;

import externalfloatpackage.ExternalFloats;
import operations.Question;

public class Q11Solution implements Question {

	// Q11 Write a program that would access two floats in a different package
	public float accessTwoFloats() {
		class InnerClass extends ExternalFloats {

		}

		InnerClass newClass = new InnerClass();
		System.out.println(
				"Accessing a float in package externalFloatPackage named anotherFloat : " + newClass.externalFloat);
		System.out
				.println("Accessing a float in package externalFloatPackage named anotherFloatAgain via class method : "
						+ externalfloatpackage.ExternalFloats.getExternalFloat());
		
		return externalfloatpackage.ExternalFloats.getExternalFloat();
	}

	@Override
	public void performTask() {

		System.out.println("Accessing floats in another package...");
		accessTwoFloats();
		
	}

}
