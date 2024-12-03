package biz.aeffegroup.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DemoService {
	
	@Autowired
	private DemoDAO demoDao;
	
	//Create
	public String create() {
		try {
			return "create";
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	//Read
	public String read() {
		try {
			return demoDao.read();
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	//Update
	public String update() {
		try {
			return demoDao.update();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	//Delete 
	public void delete() {
		try {
			demoDao.delete();
		} catch (Exception e) {
			 log.error(e.getMessage());
		}
	}


}


