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

public class FutureAndCompletableFutureExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);

		long startTime = System.currentTimeMillis();

		System.out.println("1. Task submitting");

		Future<?> submit = threadPool.submit(() -> {
			System.out.println("2. Task started");

			try {
				File file = new File("C:\\Users\\DELL\\Desktop\\LearningMultithreading\\src\\Employees.json");

				System.out.println("3. File exists: " + file.exists());

				List<Employee> employees = getEmployees(file);

				System.out.println("4. Employees loaded: " + employees.size());

				Thread.sleep(6000);

				System.out.println("5. Task completed");

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		

		System.out.println("6. Waiting for task");

		submit.get();

		System.out.println("7. Task result received");

		long endTime = System.currentTimeMillis();

		double executionTime = (endTime - startTime) / 1000.0;

		System.out.println("Total execution time: " + executionTime + " seconds");

		threadPool.shutdown();
	}

	public static List<Employee> getEmployees(File file) throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Employee> value = mapper.readValue(file, new TypeReference<List<Employee>>() {
		});
		return value;
	}

}
