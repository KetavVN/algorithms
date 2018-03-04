package concurrency.mutex.semaphore;

public class SemaphoreMain {

	public static void main(String ...args) {
		
		SemaphoreData obj = new SemaphoreData(15, 5);
		
		SemaphoreThread s1 = new SemaphoreThread(obj);
		SemaphoreThread s2 = new SemaphoreThread(obj);
		SemaphoreThread s3 = new SemaphoreThread(obj);
		
		Thread t1 = new Thread(s1, "s1");
		Thread t2 = new Thread(s2, "s2");
		Thread t3 = new Thread(s3, "s3");
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			Thread.currentThread().join(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
