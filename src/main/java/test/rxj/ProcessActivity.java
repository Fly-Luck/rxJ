package test.rxj;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

public abstract class ProcessActivity <T1, T2> implements Func1<T1, T2>, Observer<T2>{
	protected ProcessActivity<T2, ?> next;
	
	public <T3> ProcessActivity<T2, T3> then(ProcessActivity<T2, T3> next) {
		this.next = next;
		return next;
	}
	
	public void onCompleted() {
		// TODO Auto-generated method stub
	}
	
	@SuppressWarnings("unchecked")
	public void onNext(T2 t2) {
		onSuccess(t2);
		if(null != next) {
			Observable.just(t2).map(next).subscribe((Observer<? super Object>)next);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onError(Throwable e) {
		if(e instanceof ContextAwareException){
			for(Object o: ((ContextAwareException) e).getContext().values()) {
				T2 t2 = (T2) o;
				onFailed(t2, e);
			}
		} else {
			System.out.print("Unexpected error, don't know what to do with it: ");
			e.printStackTrace();
		}
	}
	
	public T2 call(T1 t1) {
		return process(t1);
	}
	
	protected abstract T2 process(T1 t1);
	
	// a better onCompleted
	protected abstract void onSuccess(T2 t2);
	
	// specified ops for onError
	protected abstract void onFailed(T2 t2, Throwable e);
	
}
