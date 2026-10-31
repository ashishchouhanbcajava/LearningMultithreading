package LearningFutureAndCompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
	public static void main(String[] args) {

		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
			try {
				System.out.println("thread name : " + Thread.currentThread().getName());
				List<Employee> allEmployees = EmployeeDatabase.getAllEmployees();
				allEmployees.forEach(System.out::println);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		runAsync.join();
	}

}
