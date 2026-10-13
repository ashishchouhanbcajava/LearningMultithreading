package mainPackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolTaskExecutorExample {
	public static void main(String[] args) {

//		Available idle thread milta hai toh usko reuse karta hai.
//		Zaroorat padne par naye threads create karta hai.
//		Normally idle thread 60 seconds tak unused rahe toh terminate ho sakta hai.
//		Ismein threads ki fixed upper limit nahi hoti; bahut saare tasks aane par bahut saare threads ban sakte hain.
		ExecutorService cachedThreadPoolExecutor = Executors.newCachedThreadPool();
		for (int i = 1; i <= 100; i++) {
			int task = i;
			cachedThreadPoolExecutor.execute(() -> {
				System.out.println("here is task : " + task + " executed by : " + Thread.currentThread().getName());
			});
		}
		cachedThreadPoolExecutor.shutdown();

	}
}
