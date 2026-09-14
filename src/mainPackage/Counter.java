package mainPackage;

public class Counter {

	int count = 0;

	public void increment() {
		synchronized (this) {

			count++;
		}
	}

	public void getCount() {
		System.out.println(count);
	}
}
