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

		Future<?> submit = threadPool.submit(() -> {
			try {
				File file = new File("C:\\Users\\DELL\\Desktop\\LearningMultithreading\\src\\Employees.json");
				List<Employee> employees = getEmployees(file);
				System.out.print(employees);
				Thread.sleep(2000);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		submit.get();
		threadPool.shutdown();
	}

	public static List<Employee> getEmployees(File file) throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Employee> value = mapper.readValue(file, new TypeReference<List<Employee>>() {
		});
		return value;
	}

}
