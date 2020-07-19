package executors;

import java.util.concurrent.Callable;

public class HelloWorldTask implements Callable<Object> {

	@Override
	public Object call() throws Exception {
		Thread.sleep(700);
		return null;
	}

}
