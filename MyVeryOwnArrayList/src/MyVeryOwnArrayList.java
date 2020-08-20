import java.util.Arrays;

public class MyVeryOwnArrayList<T> {

	private int size, currentIndex = 0;
	private T type;
	private Object[] tArr;

	public MyVeryOwnArrayList() {
		this.size = 5;
		tArr = new Object[size];
	}
	
	public MyVeryOwnArrayList(int size) {
		this.size = size;

	}
	
	public Object get(int index) {
		if (index < 0 || index > tArr.length) {
			System.out.println("Invalid index");
			return null;
		}
		return tArr[index];
	}
	
	public void add(T tToAdd) {
		
		if(currentIndex + 1 > size) {
			/*System.out.println("List is at capacity, could not add " + tToAdd.toString());
			return;*/
			increaseSize(tArr);
			size=tArr.length;
			System.out.println("Hit limit, increased size.  New size is: " + tArr.length);
		}
		tArr[currentIndex] = tToAdd;
		currentIndex++;
	}
	
	private void increaseSize(Object[] arr) throws RuntimeException {
		arr = Arrays.copyOf(arr, arr.length+1);
		tArr = arr;
	}
	
	public int size() {
		return size;
	}

}