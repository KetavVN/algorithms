package concurrency.mutex.semaphore;

import java.util.concurrent.atomic.AtomicBoolean;

public class SemaphoreData {

	private int [] data;
	private int semaphoreChunkSize;
	private AtomicBoolean [] locks;
	
	public SemaphoreData(int size, int chunkSize) {
		
		if(size == 0 || chunkSize == 0) {
			throw new RuntimeException("Illegal arguments!");
		}
		
		//assume only one thread will create the data
		data = new int [size];	//initialize with 0
		semaphoreChunkSize = chunkSize;
		locks = new AtomicBoolean [size/chunkSize];
		for(int i=0; i<locks.length; i++) {
			locks[i] = new AtomicBoolean(false);
		}
	}
	
	public void set(int index, int value) throws InterruptedException {
		int chunk = index / semaphoreChunkSize;
		System.out.println(chunk);
		while(!locks[chunk].compareAndSet(false, true)) {
			System.out.println(String.format("Thread=%s is trying to lock the chunk=%d to set data=%d at index=%d", Thread.currentThread().getName(), chunk, value, index));
		}
		
		//just for fun
		Thread.sleep(5);
		
		data[index] = value;
		
		//revert back after operation is done;
		locks[chunk].compareAndSet(true, false);
	}
	
}
