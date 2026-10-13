package mainPackage;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
	public int count;

	ReadWriteLock lock = new ReentrantReadWriteLock(true);
	Lock read = lock.readLock();
	Lock write = lock.writeLock();

	public void write() {
		write.lock();
		count++;
		System.out.println(Thread.currentThread().getName() + "  is writing : " + count);
		write.unlock();
	}

	public void read() {
		read.lock();
		System.out.println(Thread.currentThread().getName() + "  is reading : " + count);
		read.unlock();
	}

	public static void main(String[] args) {
		ReadWriteLockExample lockExample = new ReadWriteLockExample();
		Runnable writer = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10; i++) {
					lockExample.write();
				}
			}
		};

		Runnable reader = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10; i++) {
					lockExample.read();
					;
				}
			}
		};
		Thread t1 = new Thread(writer, "t1");
		Thread t2 = new Thread(reader, "t2");
		Thread t3 = new Thread(reader, "t3");
		
		t1.start();
		t2.start();
		t3.start();

	}
}
