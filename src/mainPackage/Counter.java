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

	public static void main(String[] args) {

		ThreadLocal<Object> threadLocal = ThreadLocal.withInitial(() -> "i am string");

		Runnable task1 = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				threadLocal.set(10);
				System.out.println("current thread value : " + threadLocal.get());
			}

		};

		Runnable task2 = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				threadLocal.set(100);
				threadLocal.remove();
				System.out.println("current thread value : " + threadLocal.get());

			}

		};

		Thread t1 = new Thread(task1);
		Thread t2 = new Thread(task2);
		t1.start();
		t2.start();

	}
}
