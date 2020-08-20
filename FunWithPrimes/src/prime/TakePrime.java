package prime;

public class TakePrime implements Runnable {


	public boolean checkPrime(int in) {
		if(in < 2) return false;
		for(int i = 2; i <= in / 2; i++) {
			if (in % i == 0) return false;
		}
		return true;
	}
	
	public TakePrime() {
		this.run();
	}
	
	@Override
	public void run() {
			if(this.checkPrime(MakePrime.theDeque.getFirst())) System.out.println(MakePrime.theDeque.getFirst() + " is prime!");
			else System.out.println(MakePrime.theDeque.getFirst() + " is not prime");
			MakePrime.theDeque.pop();
	}
}
