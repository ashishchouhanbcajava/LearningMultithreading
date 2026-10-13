package mainPackage;

class Pen {

    // The Pen object's intrinsic lock is acquired when this method starts.
    public synchronized void writingWithPenAndPaper(Paper paper) {

        System.out.println(
            Thread.currentThread().getName() + " started writing with pen "
        );

        // Attempts to acquire the Paper object's lock.
        paper.finishingWriting();
    }

    // Acquires the same Pen object's lock while this method executes.
    public synchronized void finishingWriting() {

        System.out.println(
            Thread.currentThread().getName() + " finished writing "
        );
    }
}

class Paper {

	public synchronized void writingWithPaperAndPen(Pen pen) {
		System.out.println(Thread.currentThread().getName() + "started writing with paper ");
		pen.finishingWriting();
	}

	public synchronized void finishingWriting() {
		System.out.println(Thread.currentThread().getName() + " finished writing  ");
	}
}

class Task1 implements Runnable {

    private Paper paper;
    private Pen pen;

    public Task1(Paper paper, Pen pen) {
        super();
        this.paper = paper;
        this.pen = pen;
    }

    @Override
    public void run() {

        // Acquire the Pen lock before starting the writing operation.
        // This ensures that this thread has exclusive access to the Pen object.
        synchronized (pen) {

            // writingWithPaperAndPen() is synchronized on the Paper object.
            // Since we already hold the Pen lock, we are controlling the
            // order in which locks are acquired.
            paper.writingWithPaperAndPen(pen);
        }
    }
}
class Task2 implements Runnable {

    private Paper paper;
    private Pen pen;

    public Task2(Paper paper, Pen pen) {
        super();
        this.paper = paper;
        this.pen = pen;
    }

    @Override
    public void run() {

        // Acquire the Pen lock through the synchronized method.
        // This keeps the lock acquisition order consistent and prevents
        // the circular waiting condition required for a deadlock.
        pen.writingWithPenAndPaper(paper);
    }
}

public class DeadLockExample {

	public static void main(String[] args) {

		Paper paper = new Paper();
		Pen pen = new Pen();
		Task1 task1 = new Task1(paper, pen);
		Task2 task2 = new Task2(paper, pen);
		Thread t1 = new Thread(task1, "Thread 1");
		Thread t2 = new Thread(task2, "Thread 2");
		t1.start();
		t2.start();
	}

}
