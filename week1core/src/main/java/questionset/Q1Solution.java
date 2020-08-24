package questionset;

import operations.Question;

public class Q1Solution implements Question {

	public int[] doBubbleSort(int[] intArray) {
		// Q1 - Bubble Sort
		// very simple sort algorithm, just check i and i+1 and flip them if i > i+1,
		// then walk down the line until done
		// public static int[] bubbleSort(int[] intArray) {

		boolean sortDone = false;
		while (!sortDone) {
			sortDone = true;
			for (int i = 0; i < intArray.length - 1; i++) {
				int t = 0;
				// is current index bigger than next index?
				if (intArray[i] > intArray[i + 1]) {
					// yes, swap em, also set done to false because we had a swap
					// System.out.println("Swap");
					sortDone = false;
					t = intArray[i];
					intArray[i] = intArray[i + 1];
					intArray[i + 1] = t;
				}

			}

		}
		// return our newly sorted array
		return intArray;
	}

	@Override
	public void performTask() {
		int[] intArray = new int[] { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };
		System.out.println("Unsorted array: ");
		printArray(intArray);
		intArray = doBubbleSort(intArray);
		System.out.println("Array after sort: ");
		printArray(intArray);

	}
	//helper method to save typing that just loops through and prints an array of integers
	private void printArray(int[] intArray) {
		for (int i = 0; i < intArray.length; i++) {
			System.out.print(intArray[i] + " ");
		}

	}

}
