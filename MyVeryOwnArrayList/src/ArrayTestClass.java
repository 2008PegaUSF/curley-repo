
public class ArrayTestClass {

	public static void main(String[] args) {
		MyVeryOwnArrayList<String> arrayList = new MyVeryOwnArrayList<String>();
		MyVeryOwnArrayList<Integer> intList = new MyVeryOwnArrayList<Integer>();

		intList.add(0);
		intList.add(1);
		intList.add(2);
		intList.add(3);
		intList.add(4);
		intList.add(5);

		arrayList.add("Robert");
		arrayList.add("Vinish");
		arrayList.add("Bruce");
		arrayList.add("Javier");
		arrayList.add("John");
		arrayList.add("MissingNo");
		arrayList.add("");
		arrayList.add("");
		arrayList.add("");
		arrayList.add("");

		for (int i = 0; i < arrayList.size(); ++i) {
			System.out.println(arrayList.get(i).toString());
		}


	}

}
