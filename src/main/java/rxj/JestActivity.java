package rxj;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JestActivity extends ProcessActivity<String, String>{
	
	public JestActivity() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected String process(String t1) {
		try {
			if(Math.random() > 0.5) {
				Map<String, Object> context = new HashMap<String, Object>();
				context.put(String.class.getSimpleName(), t1);
				throw new ContextAwareException(context);
			}
			System.out.println("[Jester]: Jesting with {"+t1+"}");
			t1 += " Or I'll be damned";
		} catch(Exception e) {
			Map<String, Object> context = new HashMap<String, Object>();
			context.put(String.class.getSimpleName(), t1);
			throw new ContextAwareException(e, context);
		}
		return t1;
	}
	
	@Override
	protected void onSuccess(String t2) {
		System.out.println("[Jester]: success: " + t2);
	}
	
	@Override
	protected void onFailed(String t2, Throwable e) {
		System.out.println("[Jester]: failed, source:{" + t2 + "}, exception: " + e);
	}
}
