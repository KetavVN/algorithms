package concurrency.readwrite.dataholder;

import java.security.SecureRandom;

public class ReadWriteMain {

	public static void main(String ...args) {
		
		final GenericDataHolder<Integer> dataHolder = new GenericDataHolder<>(new Integer(10));
		
		Runnable r1 = () -> {
			SecureRandom random = new SecureRandom();
			int i = 0;
			while(i<5) {
				try {
					dataHolder.read();
					dataHolder.write(random.nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
		};
		
		Runnable r2 = () -> {
			int i = 0;
			while(i<5) {
				try {
					dataHolder.read();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
		};
		
		Runnable w1 = () -> {
			SecureRandom random = new SecureRandom();
			int i = 0;
			while(i<5) {
				try {
					dataHolder.write(random.nextInt());
					dataHolder.read();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
		};
		
		Runnable w2 = () -> {
			SecureRandom random = new SecureRandom();
			int i = 0;
			while(i<5) {
				try {
					dataHolder.write(random.nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
		};
		
		Thread t1 = new Thread(r1, "r1");
		Thread t2 = new Thread(w1, "w1");
		Thread t3 = new Thread(r2, "r2");
		Thread t4 = new Thread(w2, "w2");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		try {
			Thread.currentThread().join(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
