package concurrency.readwrite.proxy;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Scope(value="prototype")
@Named			//Aspect worked on this one without any issue
//@Configurable //Aspect is not able to pick up these pseudo-beans
//http://olafsblog.sysbsb.de/why-configurable-and-transactional-dont-belong-to-into-the-same-class/
public class SampleData {

	private StringBuilder myData;
	private final int hash;
	
	public SampleData(int hash1) {
		hash = hash1;
		myData = new StringBuilder();
	}
	
	/*public SampleData() {
		hash = 10;
		myData = new StringBuilder();
	}*/
	
	public String get() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(String.format("Thread=%s readData=%s", Thread.currentThread().getName(), myData));
		return myData.toString().trim();
	}
	
	public void append(String str) {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		myData.append(str).append(" ");
		System.out.println(String.format("Thread=%s writeData=%s", Thread.currentThread().getName(), myData));
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SampleData other = (SampleData) obj;
		if (hash != other.hash)
			return false;
		return true;
	}
	
}

/**
Why @Configurable and @Transactional don’t belong to into the same class
By olaf On March 26, 2008 · 7 Comments

When using the Spring framework, one can still benefit from dependency injection etc. even if a bean is not obtained from a bean factory by using the @Configurable annotation, f.e.:
	
@Configurable(value = "myBean")
public class MyBean {
   ...
}

Where myBean is the bean id in the spring context.

This approach can be somewhat problematic, though, when used in conjunction with aspect-oriented features.
Say, for example, you also want to use JPA and require a transactional behavior.

You would usually do the following:
	
@Configurable(value = "myBean")
public class MyBean {
   @Transactional
    public void foo() {
    ...
    }
}

This will not work, but lead to an exception like this:


java.lang.IllegalStateException: Post-processor tried to replace bean instance of type [my.package.MyBean] with (proxy) object of type [my.package.MyBean$$EnhancerByCGLIB$$c029ddbf] - not supported for aspect-configured classes!

The reason is that @Configurable will ask spring to configure the current bean during constructor invocation. During configuration, a number of bean post processors will be invoked on the bean, and one of them, namely the InfrastructureAdvisorAutoProxyCreator will find the @Transactional annotation – which advises the post processor to proxy the bean in order to manage the required transaction’s context.

But you cannot override the expected outcome of a class instantiation with new with a proxy object – since it is not of the same type as the expected object!

Thus, @Configurable and @Transactional – together with any other advice that leads to a proxy object – cannot coexist in your class.

 */
