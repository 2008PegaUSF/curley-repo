package prime;

public class TakePrime extends Thread {

	public boolean checkPrime(int in) {
		if (in < 2)
			return false;
		for (int i = 2; i <= in / 2; i++) {
			if (in % i == 0)
				return false;
		}
		return true;
	}

	public void eatPrimes() {
		for (int i = 0; i < MakePrime.theDeque.size(); i++) {
			if (this.checkPrime(MakePrime.theDeque.getFirst()))
				System.out.println(MakePrime.theDeque.getFirst() + " is prime!");
			else
				System.out.println(MakePrime.theDeque.getFirst() + " is not prime");
			MakePrime.theDeque.pop();
		}
	}
	
	@Override
	public void run() {
		while(MakePrime.count < 1000) {
			this.eatPrimes();
		}
	}
}
