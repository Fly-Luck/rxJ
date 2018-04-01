package rxj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@Autowired
	private SomeProcess someProcess;
	
	@RequestMapping(value = "/api/go/{name}", method = RequestMethod.GET)
	public void go(@PathVariable("name") String name) {
		someProcess.nowWeJest(name);		
		System.out.println(name);
		someProcess.nowWeJest(name);
		someProcess.nowWeJest(name);
	}
}
