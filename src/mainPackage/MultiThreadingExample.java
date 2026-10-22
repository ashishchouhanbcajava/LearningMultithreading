package mainPackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadingExample {

//	public static void main(String[] args) {
//		long startTime = System.currentTimeMillis();
////		generateTask();
////		generateTask();
////		generateTask();
//
//		ExecutorService threadPool = Executors.newFixedThreadPool(3);
//		threadPool.execute(() -> {
//			generateTask();
//		});
//		threadPool.execute(() -> {
//			generateTask();
//		});
//
//		threadPool.execute(() -> {
//			generateTask();
//		});
//
//		threadPool.execute(() -> {
//			generateTask();
//		});
//
//		threadPool.shutdown();
//
//		long endTime = System.currentTimeMillis();
//		double timeInSeconds = (endTime - startTime) / 1000.0;
//		System.out.println("Execution time: " + timeInSeconds + " seconds");
//
//	}
//
//	static void generateTask() {
//		// TODO Auto-generated method stub
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		long startTime = System.currentTimeMillis();

		executor.submit(() -> generateReport("Sales"));
		executor.submit(() -> generateReport("Purchase"));
		executor.submit(() -> generateReport("Inventory"));
		executor.shutdown();

//		generateReport("Sales");
//		generateReport("Purchase");
//		generateReport("Inventory");

		boolean awaitTermination = executor.awaitTermination(1, TimeUnit.MINUTES);

		if (awaitTermination) {

			long endTime = System.currentTimeMillis();
			double timeInSeconds = (endTime - startTime) / 1000.0;
			System.out.println("Execution time: " + timeInSeconds + " seconds");
		}

	}

	static void generateReport(String name) {
		System.out.println(name + " started by " + Thread.currentThread().getName());

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}

		System.out.println(name + " completed");
	}
}
