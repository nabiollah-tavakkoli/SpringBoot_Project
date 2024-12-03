package biz.aeffegroup.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Demo")
public class DemoController {
	@Autowired
	private DemoService demoService;

	@GetMapping("hello")
	public ResponseEntity<String> hello() throws Exception{
		try {
			return new ResponseEntity<String>(demoService.read(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("ciao")
	public ResponseEntity<String> ciao() {
		try {
			return new ResponseEntity<String>(demoService.update(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}


