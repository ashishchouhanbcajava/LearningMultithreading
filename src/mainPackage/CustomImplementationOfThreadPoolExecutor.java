package mainPackage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomImplementationOfThreadPoolExecutor {

	public static void doTask() {
		for (int task = 1; task <= 1000; task++) {
			System.out
					.println("hi i am task : " + task + " and i am performed by : " + Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) {

		// it is the size of the Threads by default
		int corePoolSize = 1;

		// it is maximum size of threads in the pool including the bydefault threads
		// size
		int maximumPoolSize = 3;

		// it is lifespan of the threads
		long keepAliveTime = 0L;// alive

//		it is the queue in which those tasks gets stored which are not currently picked by any thread
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);

		// here we created an custom implementation of the ThreadPoolExecutor for custom
		// working of threads
		ExecutorService threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
				TimeUnit.SECONDS, workQueue);

		try {
			for (int i = 0; i <= 5; i++) {
				int task = i;

				threadPoolExecutor.execute(() -> {

//					try {
//						Thread.sleep(2000);
//					} catch (InterruptedException e) {
//						Thread.currentThread().interrupt();
//						return;
//					}
					doTask();
//					System.out.println("Task: " + task + " executed by: " + Thread.currentThread().getName());
				});
			}
		} catch (RejectedExecutionException e) {
			System.out.println("A task was rejected!");
		} finally {
			threadPoolExecutor.shutdown();
		}

//		for (int i = 0; i <= 5; i++) {
//			int task = i;
//
//			threadPoolExecutor.execute(() -> {
//				System.out.println("Task: " + task + " executed by: " + Thread.currentThread().getName());
//			});
//		}

//		tasks=6, core thread=1,pool size=3,queue capacity=2

	}
}
