package LearningFutureAndCompletableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureAndCompletableFutureExample {
	
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		
		Future<?> submit = threadPool.submit(()->{
			
		});
	}

}
