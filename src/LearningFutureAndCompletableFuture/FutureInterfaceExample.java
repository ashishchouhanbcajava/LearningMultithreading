package LearningFutureAndCompletableFuture;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FutureInterfaceExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);

		long startTime = System.currentTimeMillis();

//		Future<?> task1 = threadPool.submit(() -> {
//
//			try {
//				File file = new File("C:\\Users\\DELL\\Desktop\\LearningMultithreading\\src\\Employees.json");
//
//				List<Employee> employees = getEmployees(file);
//				System.out.println(" Employees loaded: " + employees.size());
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//
//		task1.get();
//		task1.get();

		Future<Integer> future = threadPool.submit(() -> {
		    return 100;
		});

		Integer result = future.get();

		boolean cancelled = future.cancel(false);

		System.out.println(cancelled);
		System.out.println(future.isCancelled());
		System.out.println(future.isDone());
//		try {
//
////			task1.get(1, TimeUnit.SECONDS);
////		} catch (Exception e) {
////			// TODO: handle exception
////			Thread.currentThread().interrupt();
////
////			e.printStackTrace();
////		}

		long endTime = System.currentTimeMillis();

		double executionTime = (endTime - startTime) / 1000.0;

		System.out.println("  Total execution time  taken by task 1 : " + executionTime + " seconds");

		startTime = System.currentTimeMillis();

//		Future<?> task2 = threadPool.submit(() -> {
//
//			try {
//				File file = new File("C:\\Users\\DELL\\Desktop\\LearningMultithreading\\src\\Employees.json");
//
//				List<Employee> employees = getEmployees(file);
//				System.out.println(" Employees loaded: " + employees.size());
//
//				Thread.sleep(4000);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});

		// when we call get() method on a future then the others calling threads will
		// wait until the task is executed
//		task2.get();

		endTime = System.currentTimeMillis();

		executionTime = (endTime - startTime) / 1000.0;

//		System.out.println("  Total execution time  taken by task 2 : " + executionTime + " seconds");

		threadPool.shutdown();
	}

	public static List<Employee> getEmployees(File file) throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Employee> value = mapper.readValue(file, new TypeReference<List<Employee>>() {
		});
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;
	}

}
