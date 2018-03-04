package concurrency.readwrite.proxy;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

//Fair implementation of read write lock
public class FairReadWriteLock implements IReadWriteLock {

	public AtomicBoolean write;
	public AtomicInteger reads;
	
	public FairReadWriteLock() {
		write = new AtomicBoolean(false);
		reads = new AtomicInteger(0);
	}
	
	@Override
	public int addReadLock() throws InterruptedException {
		int read = 0;
		synchronized(write) {
			while(!write.compareAndSet(false, false)) {	//do not allow new readers until writer completes. (writer only completes after existing readers have completed! circle of life!!)
				System.out.println(String.format("Thread=%s is waiting for write operation to complete", Thread.currentThread().getName()));
				write.wait();	//release the lock so other thread can set it to false!
			}
			//reads do not need to be locked - its optional!
			//but it should be only for 1 reason - if we do not do this, writer thread will keep on printing(executing) -> waiting for all reads to complete 
			//(i.e. -> writer will keep on executing - which defeats the entire purpose of giving existing readers chance run on CPU and complete their work!)
			//above comments are tested using commenting out all synchronized(read) and reads.notifyAll() statements! 
			synchronized(reads) {
				read = reads.incrementAndGet();
				reads.notifyAll();
			}
			write.notifyAll();
		}
		return read;
	}
	
	@Override
	public int releaseReadLock() {
		int read = 0;
		synchronized(reads) {
			read = reads.decrementAndGet();
			reads.notifyAll();
		}
		return read;
	}

	@Override
	public void addWriteLock() throws InterruptedException {
		synchronized(write) {
			while(!write.compareAndSet(false, true)) {
				System.out.println(String.format("Thread=%s is waiting for write operation completes!", Thread.currentThread().getName()));
				write.wait();	//lock released!
			}
			write.notifyAll();
		}
		synchronized(reads) {
			while(!reads.compareAndSet(0, 0)) {
				System.out.println(String.format("Thread=%s is waiting for read operations completes! reads=%d", Thread.currentThread().getName(), reads.get()));
				reads.wait();	//lock released!
			}
			reads.notifyAll();
		}
	}
	
	@Override
	public void releaseWriteLock() throws InterruptedException {
		synchronized(write) {
			while(!write.compareAndSet(true, false)) {
				System.out.println("This line should never be called!");
				write.wait();	//lock released!
			}
			write.notifyAll();
		}
	}
	
}
