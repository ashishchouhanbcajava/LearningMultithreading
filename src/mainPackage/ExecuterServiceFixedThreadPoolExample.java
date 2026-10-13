package mainPackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuterServiceFixedThreadPoolExample {

	public static void main(String[] args) {

//		fixed threadpool executor is used to create a pool of fixed threads and 
//		perform all tasks by these threads and if the thread 
//		 If additional tasks are submitted when all threads are active,
//	     they will wait in the queue until a thread is available.

		ExecutorService service = Executors.newFixedThreadPool(3);
		for (int i = 1; i <= 20; i++) {
			int task = i;
			service.submit(() -> {
				System.out.println("task : " + task + "  is executed by " + Thread.currentThread().getName());
			});

		}

		service.shutdown();
	}

}
