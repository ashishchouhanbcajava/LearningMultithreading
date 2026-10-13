package mainPackage;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessLockExample {

	// At here fairness means in which order we starts our thread in that order they
	// will be started and execute the desired tasks

	// Fairness does NOT mean that threads will execute in the same order
	// in which we call start().
	//
	// With a fair ReentrantLock (new ReentrantLock(true)), when multiple
	// threads are waiting to acquire the lock, the lock generally gives
	// preference to the threads that have been waiting for the longest,
	// following a roughly FIFO order.
	//
	// Example:
	//
	// Thread-1 acquires the lock
	// Thread-2 → waiting
	// Thread-3 → waiting
	// Thread-4 → waiting
	//
	// When Thread-1 releases the lock:
	//
	// Thread-2 → gets the lock
	// Thread-3 → gets the lock
	// Thread-4 → gets the lock
	//
	// However, fairness does NOT control CPU scheduling or guarantee the
	// order in which threads start executing. CPU scheduling is handled
	// by the OS scheduler.

	Lock lock = new ReentrantLock(true);

	public void accessResource() {
		lock.lock();
		try {
			System.out.println("here " + Thread.currentThread().getName() + " is locking ");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		FairnessLockExample example = new FairnessLockExample();
		Runnable task = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				example.accessResource();
			}

		};

		Thread t1 = new Thread(task, "Thread 1");
		Thread t2 = new Thread(task, "Thread 2");
		Thread t3 = new Thread(task, "Thread 3");

		try {
			t1.start();
			t2.start();
			t3.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
