package executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyExecutor1 {

	//316730877874 nano seconds = 316.730877874 seconds 
	//time taken in nano seconds = 316727862717
	//time taken in nano seconds = 322019926516
	//time taken in nano seconds = 324018864649
	//time taken in nano seconds = 324020933549
	//time taken in nano seconds = 322016989477
	public static void main(String[] args) {
		
		ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(15);
		
		for(int j=0; j<5; j++) {
			
			long starttime = System.nanoTime();
			
			for(int i=0; i<3260; i++) {
				HelloWorldTask task = new HelloWorldTask();
				checkAndProceed(service);
				service.submit(task);
			}
			
			while(service.getActiveCount() != 0);
			
			long endtime = System.nanoTime();
			
			System.out.println("time taken in nano seconds = "+(endtime-starttime));
			
		}

		service.shutdownNow();
		
	}

	private static void checkAndProceed(ThreadPoolExecutor service) {
		if(service.getQueue().size() >= 20) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
