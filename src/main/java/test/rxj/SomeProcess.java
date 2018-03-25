package test.rxj;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import rx.Observable;
import rx.schedulers.Schedulers;

@Component
public class SomeProcess implements InitializingBean {
	private JestActivity jestActivity;
	
	private MockActivity mockActivity;
	
	private ExecutorService executorService;
	
	public SomeProcess(ExecutorService executorService, JestActivity jestActivity, MockActivity mockActivity) {
		this.jestActivity = jestActivity;
		this.mockActivity = mockActivity;
		this.executorService = executorService;
	}
	
	public void afterPropertiesSet() throws Exception {
		//create the process chain
		jestActivity.then(mockActivity);
	}
	
	public void nowWeJest(String source) {
		Observable.just(source)
				.map(jestActivity)
				.subscribeOn(Schedulers.from(executorService))
				.subscribe(jestActivity);
	}
	
	public void nowWeMock(String source) {
		Observable.just(source)
		.map(mockActivity)
		.subscribeOn(Schedulers.from(executorService))
		.subscribe(mockActivity);		
	}
}
