package executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyExecutor2 {

	//fairness not supplied
	//285438681322 nano seconds = 285.438681322 seconds
	//time taken in nano seconds = 258738775891
	//time taken in nano seconds = 302023401290
	//time taken in nano seconds = 322024570605
	//time taken in nano seconds = 320018929436
	//time taken in nano seconds = 322020213235
	
	//fairness = true
	//time taken in nano seconds = 200790682570
	//time taken in nano seconds = 200783847663
	//time taken in nano seconds = 200776428061
	//time taken in nano seconds = 200774333713
	//time taken in nano seconds = 200768807048
	
	//fairness = false
	//time taken in nano seconds = 260741265340
	//time taken in nano seconds = 290032392853
	//time taken in nano seconds = 324038673552
	//time taken in nano seconds = 324018645628
	//time taken in nano seconds = 322021294139
	public static void main(String[] args) {
		
		ThreadPoolExecutor service = new ThreadPoolExecutor(15, 15, 60, 
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(30, false));
		
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
