package concurrency.mutex.semaphore;

import java.security.SecureRandom;

public class SemaphoreThread implements Runnable {
	
	private SemaphoreData obj;
	
	public SemaphoreThread(SemaphoreData o) {
		obj = o;
	}
	
	@Override
	public void run() {
		SecureRandom random = new SecureRandom();
		int i = 0;
		while(i<15) {
			int index = random.nextInt(15);
			int value = random.nextInt();
			try {
				obj.set(index, value);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}
	
	
}
