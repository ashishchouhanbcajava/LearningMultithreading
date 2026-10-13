package mainPackage;

public class SecondClass extends Thread {

	private Counter counter;

	public SecondClass(Counter counter) {
		// TODO Auto-generated constructor stub
		this.counter = counter;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			counter.increment();
		}
	}

}

//public class SecondClass implements Runnable {
//
//	@Override
//	public void run() {
//
//		System.out.println("i am second class thread......... " + Thread.currentThread().getName());
//	}
//
//}
