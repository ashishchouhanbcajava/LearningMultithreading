package mainPackage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerExampleUsingBlockingQueue {
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(2);

//		Thread producer = new Thread(() -> {
//
//			for (int i = 1; i <= 5; i++) {
//
//				try {
//					queue.put(i);
//					System.out.println("Produced : " + i);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//
//		});
//
//		Thread consumer = new Thread(() -> {
//
//			for (int i = 1; i <= 5; i++) {
//			    try {
//				Integer item = queue.take();
//			    System.out.println("Consumed: " + item);
//			
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//		});

		Thread producer = new Thread(() -> {
			for (int i = 1; i <= 5; i++) {
				try {
					System.out.println("Trying to produce: " + i);

					queue.put(i);

					System.out.println("PRODUCED: " + i + " | Queue: " + queue);

				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
			}
		}, "Producer");

		Thread consumer = new Thread(() -> {
			for (int i = 1; i <= 5; i++) {
				try {
					Integer item = queue.take();

					System.out.println("CONSUMED: " + item + " | Queue: " + queue);

					Thread.sleep(2000);

				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
			}
		}, "Consumer");

		producer.start();

		consumer.start();
	}
}
