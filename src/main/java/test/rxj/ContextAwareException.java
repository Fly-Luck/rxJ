package test.rxj;

import java.util.Map;

public class ContextAwareException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	private Map<String, Object> context;
	
	public ContextAwareException(Map<String, Object> context) {
		super();
		this.context = context;
	}
	
	public ContextAwareException(String message, Map<String, Object> context) {
		super(message);
		this.context = context;
	}
	
	public ContextAwareException(Throwable e, Map<String, Object> context) {
		super(e);
		this.context = context;
	}
	
	public ContextAwareException(String message, Throwable e, Map<String, Object> context) {
		super(message, e);
		this.context = context;
	}
	
	public Map<String, Object> getContext() {
		return context;
	}
}
