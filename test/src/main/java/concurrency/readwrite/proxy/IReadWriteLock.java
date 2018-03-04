package concurrency.readwrite.proxy;

public interface IReadWriteLock {

	int addReadLock() throws InterruptedException;

	int releaseReadLock();

	void addWriteLock() throws InterruptedException;

	void releaseWriteLock() throws InterruptedException;
	
}
