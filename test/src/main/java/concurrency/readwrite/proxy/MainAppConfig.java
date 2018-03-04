package concurrency.readwrite.proxy;

import java.security.SecureRandom;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@Configuration
@EnableAspectJAutoProxy
@EnableSpringConfigured											//used for @Configurable pseudo-beans
@EnableLoadTimeWeaving(aspectjWeaving=AspectJWeaving.ENABLED)	//used for @Configurable pseudo-beans
@ComponentScan(basePackages= {"concurrency.readwrite.proxy"})
public class MainAppConfig {

	public static void main(String args[]) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MainAppConfig.class);
		
		//final SampleData data = new SampleData(10);
		final SampleData data = ctx.getBean(SampleData.class, 10);
		//final SampleData data1 = ctx.getBean(SampleData.class, 11);
		
		Runnable r1 = () -> {
			SecureRandom random = new SecureRandom();
			int i = 0;
			while(i<5) {
				data.get();
				data.append(Integer.toString(random.nextInt()));
				i++;
			}
		};
		
		Runnable r2 = () -> {
			int i = 0;
			while(i<5) {
				data.get();
				i++;
			}
		};
		
		Runnable w1 = () -> {
			SecureRandom random = new SecureRandom();
			int i = 0;
			while(i<5) {
				data.append(Integer.toString(random.nextInt()));
				data.get();
				i++;
			}
		};
		
		Runnable w2 = () -> {
			SecureRandom random = new SecureRandom();
			int i = 0;
			while(i<5) {
				data.append(Integer.toString(random.nextInt()));
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
		
		((AnnotationConfigApplicationContext)ctx).close();
	}
	
}
