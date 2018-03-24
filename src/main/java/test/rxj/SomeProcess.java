package test.rxj;

import java.util.concurrent.ExecutorService;

import rx.Observable;
import rx.schedulers.Schedulers;

public class SomeProcess {
	private JestActivity jestActivity;
	
	private ExecutorService executorService;
	
	public SomeProcess(ExecutorService executorService, JestActivity jestActivity) {
		this.jestActivity = jestActivity;
	}
	
	public void nowWeJest(String source) {
		Observable.just(source).map(jestActivity).subscribeOn(Schedulers.from(executorService)).subscribe(jestActivity);
	}
}
