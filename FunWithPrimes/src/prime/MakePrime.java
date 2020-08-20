package prime;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class MakePrime extends Thread {
	public static Deque<Integer> theDeque = new LinkedList<Integer>();

	TakePrime other;
	
	Random rand = new Random();
	
	public MakePrime(TakePrime other) {
		this.other=other;
	}
	
	public static int count = 0;

	
	public synchronized void producePrimes() {
		for (int i = 0; i < 5; i++) {
			theDeque.push(rand.nextInt(10000));
		}
	}

	public void run() {
		while (count < 1000) {
			producePrimes();
			other.eatPrimes();
			count++;
		}
	}
}
