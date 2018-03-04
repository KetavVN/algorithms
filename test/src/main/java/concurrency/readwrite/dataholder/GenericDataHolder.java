package concurrency.readwrite.dataholder;

import java.security.SecureRandom;

import concurrency.readwrite.proxy.IReadWriteLock;
import concurrency.readwrite.proxy.FairReadWriteLock;

public class GenericDataHolder<T> {

	private T data;
	private IReadWriteLock readWriteLock;
	private SecureRandom random;
	
	public GenericDataHolder(T d) {
		data = d;
		readWriteLock = new FairReadWriteLock();
		random = new SecureRandom();
	}
	
	public T read() throws InterruptedException {
		int read = readWriteLock.addReadLock();
		
		System.out.println(String.format("Thread=%s acquired read lock. reads=%d", Thread.currentThread().getName(), read));
		
		//assume 10k code lines are called from here!
		int millis = 5 + random.nextInt(15);
		Thread.sleep(millis);	//locks are not released!
		
		read = readWriteLock.releaseReadLock();
		System.out.println(String.format("Thread=%s releasing read lock. reads=%d", Thread.currentThread().getName(), read));
		
		return data;
	}

	public void write(T d) throws InterruptedException {
		readWriteLock.addWriteLock();
		
		System.out.println(String.format("Thread=%s acquired write lock. Args=%s", Thread.currentThread().getName(), d.toString()));
		
		//assume 10k code lines are called from here!
		int millis = 5 + random.nextInt(15);
		Thread.sleep(millis);	//locks are not released!
		
		data = d;
		
		System.out.println(String.format("Thread=%s releasing write lock.", Thread.currentThread().getName()));
		
		readWriteLock.releaseWriteLock();
	}
	
}
