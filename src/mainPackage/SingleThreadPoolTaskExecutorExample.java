package mainPackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPoolTaskExecutorExample {
	public static void main(String[] args) {

//		single thread executor is used to perform all tasks by a single Thread sequentially one by one 
//		Ek aur important distinction: newSingleThreadExecutor()
//		single worker ki guarantee deta hai. Agar worker terminate ho jaye, 
//		toh executor zaroorat padne par replacement worker create kar sakta hai.
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

		for (int i = 1; i <= 10; i++) {
			int task = i;
			singleThreadExecutor.execute(() -> {
				System.out.println("here is task " + task + " executed by : " + Thread.currentThread().getName());
			});
		}

		singleThreadExecutor.shutdown();
	}

}
