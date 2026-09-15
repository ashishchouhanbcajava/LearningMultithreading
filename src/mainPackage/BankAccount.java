package mainPackage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

	int balance = 200;

	Lock lock = new ReentrantLock();

	// At here only one thread who is firstly scheduled by os will
	// acquire lock until it finishes its operation and can modify the shared
	// balance and another thread will waits
	// until the first thread finishes its operation and release the lock

//	public synchronized void withDraw(int amt) {
//
//		if (balance > amt) {
//			balance -= amt;
//			System.out.println(Thread.currentThread().getName() + "  proceeding withdrawl");
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

	// At this point, only one thread can acquire the lock at a time.
	// The thread that successfully acquires the lock can perform the
	// protected operation and modify the shared balance.
	//
	// IMPORTANT:
	// It is NOT guaranteed that the thread scheduled first by the OS
	// will acquire the lock first. The OS scheduler decides which thread
	// gets CPU time, while the lock decides which thread gets access to
	// the protected resource.
	//
	// For example, suppose we start two threads:
	//
//	     t1.start();
//	     t2.start();
	//
	// If Thread-1 executes lock.tryLock() first and the lock is available,
	// Thread-1 acquires the lock and performs the operation.
	//
	// While Thread-1 holds the lock, if Thread-2 calls lock.tryLock(),
	// tryLock() immediately returns false because the lock is already
	// held by Thread-1.
	//
	// Therefore, Thread-2 does NOT wait for the lock. Its control goes
	// directly to the else block.
	//
	// Once Thread-1 finishes its operation, it releases the lock using
	// lock.unlock().
	//
	// The important point is:
	//
//	     tryLock()  -> try to acquire the lock immediately
//	                  if unavailable -> return false
//	                  NO WAITING
	//
	// Also, Thread-1 is not necessarily the winner. Thread-2 could
	// acquire the lock first if it happens to execute tryLock() first.

	
	
//	public void withDraw(int amt) {
//		if (lock.tryLock()) {
//			try {
//				// manipulate protected state
//				try {
//					Thread.sleep(4000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				balance -= amt;
//				System.out.println(Thread.currentThread().getName() + "  proceeding withdrawl");
//			} finally {
//				lock.unlock();
//			}
//
//		} else {
//			System.out.println(Thread.currentThread().getName() + "  failed to acquire lock");
//
//		}
//
//	}

	
	 
	// At this point, only one thread can hold the lock at a time.
	//
	// IMPORTANT:
	// The thread that acquires the lock is NOT necessarily the thread
	// that was scheduled first by the OS. The OS scheduler decides which
	// thread gets CPU time, while tryLock() decides whether that thread
	// can acquire the lock.
	//
	// Here we are using:
	//
//	     lock.tryLock(4000, TimeUnit.MILLISECONDS)
	//
	// This means:
	//
//	     1. If the lock is available when a thread calls tryLock(),
//	        the thread immediately acquires the lock and tryLock()
//	        returns true.
	//
//	     2. If the lock is already held by another thread, the calling
//	        thread waits for the lock for a maximum of 4000 milliseconds
//	        (4 seconds).
	//
//	     3. If the lock becomes available within those 4 seconds,
//	        the waiting thread acquires the lock and tryLock() returns true.
	//
//	     4. If the lock does NOT become available within 4 seconds,
//	        tryLock() returns false and the control moves to the else block.
	//
	// Example:
	//
	// Suppose Thread-1 calls tryLock() first and the lock is available:
	//
//	     Thread-1
//	          ↓
//	     tryLock(4 sec)
//	          ↓
//	     Lock available
//	          ↓
//	     🔒 Lock acquired
//	          ↓
//	     performs withdrawal
//	          ↓
//	     sleep(4 sec)
//	          ↓
//	     unlock()
	//
	// Meanwhile, Thread-2 calls tryLock() while Thread-1 owns the lock:
	//
//	     Thread-2
//	          ↓
//	     tryLock(4 sec)
//	          ↓
//	     Lock is already held by Thread-1
//	          ↓
//	     waits for maximum 4 seconds
	//
	// Now there are two possible outcomes:
	//
	// CASE 1:
	// Thread-1 releases the lock within Thread-2's 4-second waiting period.
	//
//	     Thread-1 → unlock()
//	                    ↓
//	     Thread-2 → acquires lock 🔒
//	                    ↓
//	                 proceeds
	//
	// CASE 2:
	// Thread-1 does not release the lock before Thread-2's
	// 4-second timeout expires.
	//
//	     Thread-2 → timeout
//	                    ↓
//	              tryLock() returns false
//	                    ↓
//	              else block executes
//	                    ↓
//	              "failed to acquire lock"
	//
	// Therefore:
	//
//	     tryLock()
//	         → Try immediately
//	         → If unavailable, return false immediately
	//
//	     tryLock(4 seconds)
//	         → Try immediately
//	         → If unavailable, wait up to 4 seconds
//	         → Lock acquired within 4 seconds → true
//	         → Still unavailable after 4 seconds → false
	//
	// Also remember:
	//
//	     Thread.sleep(4000)
	//
	// does NOT release the lock. The thread continues to hold the lock
	// while sleeping. The lock is released only when:
	//
//	     lock.unlock()
	//
	// is executed.
	//
	// Finally, "CPU is allocated to" should not be interpreted as
	// "this thread owns the CPU". This message only tells us that the
	// current thread successfully acquired the lock. CPU scheduling is
	// handled separately by the OS scheduler.
	 

	
//	public void withDraw(int amt) {
//		try {
//			if (lock.tryLock(4000, TimeUnit.MILLISECONDS)) {
//				System.out.println("Cpu is allocated to : "+Thread.currentThread().getName());
//				try {
//					// manipulate protected state
//					try {
//						Thread.sleep(4000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					balance -= amt;
//					System.out.println(Thread.currentThread().getName() + "  proceeding withdrawl");
//				} finally {
//					lock.unlock();
//				}
//
//			} else {
//				System.out.println(Thread.currentThread().getName() + "  failed to acquire lock");
//
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	
	
	 
	// Here we are using lock.lock(), which means the thread will wait
	// until it successfully acquires the lock.
	//
	// The thread that gets CPU time first is NOT necessarily the thread
	// that gets the lock first. The thread must execute lock.lock() and
	// successfully acquire the available lock.
	//
	// Example:
	//
	// Thread-1
//	     ↓
	// lock.lock()
//	     ↓
	// Lock available → 🔒 Lock acquired
//	     ↓
	// performs withdrawal
	//
	// If Thread-2 tries to acquire the same lock while Thread-1 is
	// holding it:
	//
	// Thread-2
//	     ↓
	// lock.lock()
//	     ↓
	// Lock unavailable
//	     ↓
	// waits ⏳
	//
	// Thread-2 will remain waiting until Thread-1 releases the lock.
	//
	// IMPORTANT:
	// Thread.sleep(10000) does NOT release the lock.
	// Thread-1 continues to hold the lock while sleeping.
	//
	// After Thread-1 finishes its protected operation:
	//
//	     lock.unlock()
//	          ↓
//	     🔓 Lock released
//	          ↓
//	     Thread-2 can acquire the lock
//	          ↓
//	     Thread-2 proceeds with its operation
	//
	// Therefore:
	//
//	     lock.lock()
//	         → Acquire the lock
//	         → If unavailable, WAIT until it becomes available
	//
//	     lock.unlock()
//	         → Release the lock
	//
	// Unlike tryLock(), lock() does not return false just because
	// the lock is currently unavailable. It waits for the lock.
	//
	// Also, "CPU is allocated to" only indicates which thread is currently
	// executing this method; it does NOT mean that the thread owns the CPU.
	// CPU scheduling is handled by the OS scheduler.
	 

	public void withDraw(int amt) {
		System.out.println("Cpu is allocated to : " + Thread.currentThread().getName());
		try {

			lock.lock();
			// manipulate protected state
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			balance -= amt;
			System.out.println(Thread.currentThread().getName() + "  proceeding withdrawl");
		} finally {
			lock.unlock();
		}

	}

}
