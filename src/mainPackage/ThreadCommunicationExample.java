package mainPackage;

class SharedResource {
	int data;
	boolean hasData;

	public synchronized void produce(int value) {
		while (hasData) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		data = value;
		hasData = true;
		System.out.println("produced  : " + data);
		notifyAll();
	}

	public synchronized int consume() {
		while (!hasData) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		hasData = false;
		System.out.println(Thread.currentThread().getName() + "  consumed  : " + data);
		notifyAll();
		return data;
	}
}

class Producer implements Runnable {
	SharedResource resource;

	public Producer(SharedResource resource) {
		super();
		this.resource = resource;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 1; i < 10; i++) {
			resource.produce(i);
		}
	}

}

class Consumer implements Runnable {
	SharedResource resource;

	public Consumer(SharedResource resource) {
		super();
		this.resource = resource;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (this) {

			for (int i = 1; i < 5; i++) {
				resource.consume();
			}
		}
	}

}

//class Consumer1 implements Runnable {
//	SharedResource resource;
//
//	public Consumer1(SharedResource resource) {
//		super();
//		this.resource = resource;
//	}
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		synchronized (this) {
//			for (int i = 0; i <= 10; i++) {
//				resource.consume();
//			}
//		}
//	}
//
//}

public class ThreadCommunicationExample {

	public static void main(String[] args) {
		SharedResource resource = new SharedResource();
		Thread t1 = new Thread(new Producer(resource));
		Thread t2 = new Thread(new Consumer(resource), "Consumer 1");
//		Thread t3 = new Thread(new Consumer1(resource), "Consumer 2");

		t1.start();

		t2.start();
//		t3.start();
	}
}
