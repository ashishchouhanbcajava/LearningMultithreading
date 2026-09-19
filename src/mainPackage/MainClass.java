package mainPackage;

public class MainClass {

	// when we extends thread class then we can directly starts the thread using the
	// object of the classs that extends thread class

//	public static void main(String[] args) {
//		SecondClass secondClass = new SecondClass();
//		System.out.println("before start");
//		secondClass.start();
//		for (int i = 0; i <= 100; i++) {
//			System.out.println("i am main thread : " + Thread.currentThread().getName());
//		}
//	}

	// but when we implements the runnable interface then we cannot directly starts
	// the thread now we have to create object of the 'Thread' class which have the
	// start methods by calling this start method we can create thread and run our
	// any implementation
	public static void main(String[] args) throws InterruptedException {
//		SecondClass secondClass = new SecondClass();
//
//		Thread thread = new Thread(secondClass);
//		thread.start();

//		Thread t1 = new Thread(() -> {
//
//			System.out.println("T1 started: " + Thread.currentThread().getState());
//
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//
//			System.out.println("T1 finishing: " + Thread.currentThread().getState());
//		}, "Thread-1");
//
//		Thread t2 = new Thread(() -> {
//
//			System.out.println("T2 started: " + Thread.currentThread().getState());
//
//		}, "Thread-2");
//
//		Thread t3 = new Thread(() -> {
//
//			System.out.println("T3 started: " + Thread.currentThread().getState());
//
//		}, "Thread-3");
//
//		Thread t4 = new Thread(() -> {
//
//			System.out.println("T4 started: " + Thread.currentThread().getState());
//
//		}, "Thread-4");
//
//		// Before starting
//		System.out.println("T1: " + t1.getState());
//		System.out.println("T2: " + t2.getState());
//		System.out.println("T3: " + t3.getState());
//		System.out.println("T4: " + t4.getState());
//
//		// Start T1
//		t1.start();
//		t1.interrupt();
//		Thread.sleep(100);
//
//		System.out.println("After starting T1:");
//		System.out.println("T1: " + t1.getState());
//		System.out.println("T2: " + t2.getState());
//		System.out.println("T3: " + t3.getState());
//		System.out.println("T4: " + t4.getState());
//
//		// Start remaining threads
//		t2.start();
//		t3.start();
//		t4.start();
//
//		Thread.sleep(100);
//
//		System.out.println("After starting all:");
//		System.out.println("T1: " + t1.getState());
//		System.out.println("T2: " + t2.getState());
//		System.out.println("T3: " + t3.getState());
//		System.out.println("T4: " + t4.getState());
//
//		// Wait for all threads to finish
//		t1.join();
//		t2.join();
//		t3.join();
//		t4.join();
//
//		System.out.println("After all finished:");
//
//		System.out.println("T1: " + t1.getState());
//		System.out.println("T2: " + t2.getState());
//		System.out.println("T3: " + t3.getState());
//		System.out.println("T4: " + t4.getState());

//		Counter counter = new Counter();
//		SecondClass t1 = new SecondClass(counter);
//		SecondClass t2 = new SecondClass(counter);
//
//		t1.start();
//		t2.start();
//		t1.join();
//		t2.join();
//		counter.getCount();

		// we can achieve thread safety using the following
		// 1. synchronized keyword
		// 2. instance of reentrant Lock class
		// 3. Atomic classes instances

//		BankAccount account = new BankAccount();
//
//		Runnable task = new Runnable() {
//
//			@Override
//			public void run() {
//				account.withDraw(50);
//			}
//		};
//
//		Thread t11 = new Thread(task, "thread 1");
//		Thread t12 = new Thread(task, "thread 2");
//		t11.start();
//		t12.start();

		Object lock1 = new Object();
		Object lock2 = new Object();

		Thread t1 = new Thread(() -> {

			synchronized (lock1) {

				System.out.println("Thread 1: acquired lock1");

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("Thread 1: trying to acquire lock2");

				synchronized (lock2) {

					System.out.println("Thread 1: acquired lock2");
				}
			}
		});

		Thread t2 = new Thread(() -> {

			synchronized (lock1) {

				System.out.println("Thread 2: acquired lock1");

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("Thread 2: trying to acquire lock2");

				synchronized (lock2) {

					System.out.println("Thread 2: acquired lock2");
				}
			}
		});

		t1.start();
		t2.start();
	}

}
