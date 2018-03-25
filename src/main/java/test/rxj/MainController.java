package test.rxj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@Autowired
	private SomeProcess someProcess;
	
	@RequestMapping(value = "/api/go/{word}", method = RequestMethod.GET)
	public void go(@PathVariable("word") String word) {
		someProcess.nowWeJest(word);
	}
}
