package concurrency.readwrite.proxy;

import java.util.Map;
import java.util.WeakHashMap;

import javax.inject.Named;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Named @Aspect
public class ReadWriteLockProxy {

	//Keys are weak
	private Map<Object, IReadWriteLock> lockMap = new WeakHashMap<>();
	
	@Pointcut("execution(public String concurrency.readwrite.proxy.SampleData.get(..))")
	public void readPointcut() {}
	
	@Pointcut("execution(public void concurrency.readwrite.proxy.SampleData.append(*))")
	public void writePointcut() {}
	
	@Around(value="readPointcut()")
	public Object readAroundAdvice(ProceedingJoinPoint pjp) {
		Object ret = null;
		try {
			Object target = pjp.getTarget();
			IReadWriteLock lock = null;
			if(!lockMap.containsKey(target)) {
				lock = new FairReadWriteLock();
				lockMap.put(target, lock);
			}
			lock = lockMap.get(target);
			lock.addReadLock();
			ret = pjp.proceed();
			lock.releaseReadLock();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	@Around(value="writePointcut()")
	public Object writeAroundAdvice(ProceedingJoinPoint pjp) {
		Object ret = null;
		try {
			Object target = pjp.getTarget();
			IReadWriteLock lock = null;
			if(!lockMap.containsKey(target)) {
				lock = new FairReadWriteLock();
				lockMap.put(target, lock);
			}
			lock = lockMap.get(target);
			lock.addWriteLock();
			ret = pjp.proceed();
			lock.releaseWriteLock();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return ret;
	} 
	
}
