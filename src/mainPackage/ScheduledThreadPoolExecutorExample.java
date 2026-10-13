package mainPackage;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorExample {

	public static void main(String[] args) {

//		 Submits a periodic action that becomes enabled first after the
//	     given initial delay, and subsequently with the given period;
//	     that is, executions will commence after
//	     {@code initialDelay}, then {@code initialDelay + period}, then
//	     {@code initialDelay + 2   period}, and so on.
//	    
//	     <p>The sequence of task executions continues indefinitely until
//	     one of the following exceptional completions occur:

		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);

		for (int i = 1; i <= 5; i++) {
			int task = i;

//			Maan le customer ne order place kiya hai aur tujhe 
//			10 seconds baad ek baar order ka status check karna hai
			// then we can use schedule method

//			scheduledThreadPool.schedule(() -> {
//				System.out.println("task in schedule : " + task + " is executed by : " + Thread.currentThread().getName());
//			}, 5, TimeUnit.SECONDS);

			
			
//			Submits a periodic action that becomes enabled first after the
//		       given initial delay, and subsequently with the given period;
//		     that is, executions will commence after
//		     {@code initialDelay}, then {@code initialDelay + period}, then
//		     {@code initialDelay + 2   period}, and so on.
//		    
//		     <p>The sequence of task executions continues indefinitely until
//		     one of the following exceptional completions occur:
//		     <ul>
//		     <li>The task is {@linkplain Future#cancel explicitly cancelled}
//		     via the returned future.
//			scheduledThreadPool.scheduleAtFixedRate(() -> {
//				System.out.println("task in scheduleAtFixedRate : " + task + " is executed by : "
//						+ Thread.currentThread().getName());
//			}, 5, 10, TimeUnit.SECONDS);

			
			
			
//			   Submits a periodic action that becomes enabled first after the
//		       given initial delay, and subsequently with the given delay
//		       between the termination of one execution and the commencement of
//		       the next.
//		      
//		       <p>The sequence of task executions continues indefinitely until
//		       one of the following exceptional completions occur:
			scheduledThreadPool.scheduleWithFixedDelay(() -> {
				System.out.println("task : " + task + " is executed by : " + Thread.currentThread().getName());
			}, 2, 7, TimeUnit.SECONDS);
		}

		scheduledThreadPool.shutdown();

	}
}
