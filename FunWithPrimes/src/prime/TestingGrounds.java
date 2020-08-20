package prime;

public class TestingGrounds extends Thread{

	
	
	public static void main(String[] args) {
		TakePrime tp = new TakePrime();
		MakePrime mp = new MakePrime(tp);

		mp.start();
		tp.start();
	}

}
