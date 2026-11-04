package LearningFutureAndCompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CompletableFutureRunAsyncExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// At here runAsync method will execute task on a ForkJoinPool.commonPool()
		// we use runAsync method to complete the task asynchronously but dont want to
		// return results ,it is mainly used for background tasks like email
		// sending,file processing etc.

		// it will work on globalThreadpool ForkJoinPool.commonPool()
//		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
//			try {
//				System.out.println("thread name : " + Thread.currentThread().getName());
//				System.out.println("is daemon : " + Thread.currentThread().isDaemon());
//				List<Employee> allEmployees = EmployeeDatabase.getAllEmployees();
//				allEmployees.forEach(System.out::println);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});

		ThreadPoolExecutor customExecutor = new ThreadPoolExecutor(1, 5, 2, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(5));
		CompletableFuture.runAsync(() -> {

			for (int i = 1; i <= 10; i++) {
				int task = i;
				System.out.println("I m  " + Thread.currentThread().getName() + " and i m executing the task : " + i);
			}

		}, customExecutor);

		Thread.sleep(10000);
		System.out.println("main completed ");
		
		System.out.println("hello");
	}

}