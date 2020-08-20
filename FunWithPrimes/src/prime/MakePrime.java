package prime;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class MakePrime implements Runnable {
	public static Deque<Integer> theDeque = new LinkedList<Integer>();

	Random rand = new Random();

	public MakePrime() {
		this.run();
	}

	public void run() {

		theDeque.push(rand.nextInt(10000));
		if (theDeque.size() > 5) {
			System.out.println("TOO BIG OF A DEQUE");
			System.exit(-1);
		}
	}
}
