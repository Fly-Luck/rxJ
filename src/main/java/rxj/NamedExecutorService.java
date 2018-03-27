package rxj;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.management.MalformedObjectNameException;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import com.fasterxml.jackson.databind.util.Named;

public class NamedExecutorService implements ExecutorService, Named{
	
	String name;
	
	ThreadPoolExecutor executor;
	
	public NamedExecutorService(String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			int queueSize, RejectedExecutionHandler handler) throws MalformedObjectNameException {
		BlockingQueue<Runnable> workQueue = new SynchronousQueue<>();
		if(queueSize > 0) {
			workQueue = new LinkedBlockingDeque<>(queueSize);
		} else if(queueSize < 0) {
			workQueue = new LinkedBlockingQueue<>();
		}
		CustomizableThreadFactory threadFactory = new CustomizableThreadFactory(name + "-");
		threadFactory.setDaemon(true);
		executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
		this.name = name;
	}

	@Override
	public void execute(Runnable command) {
		executor.execute(command);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void shutdown() {
		executor.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		return executor.shutdownNow();
	}

	@Override
	public boolean isShutdown() {
		return executor.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return executor.isTerminated();
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return executor.awaitTermination(timeout, unit);
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return executor.submit(task);
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		return executor.submit(task, result);
	}

	@Override
	public Future<?> submit(Runnable task) {
		return executor.submit(task);
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		return executor.invokeAll(tasks);
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException {
		return executor.invokeAll(tasks, timeout, unit);
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		return executor.invokeAny(tasks);
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		return executor.invokeAny(tasks, timeout, unit);
	}
}
