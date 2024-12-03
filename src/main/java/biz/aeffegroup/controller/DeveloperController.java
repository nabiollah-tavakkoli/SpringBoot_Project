package biz.aeffegroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biz.aeffegroup.entity.Developer;
import biz.aeffegroup.service.DeveloperService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("developer")
public class DeveloperController {
	
	@Autowired
	private DeveloperService devService;
	
	@PostMapping("create")
	@PreAuthorize("admin")
	public ResponseEntity<Developer> create(@RequestBody Developer developer){
		try {
			return new ResponseEntity<Developer>(devService.saveDeveloper(developer), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Developer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("read")
	@PreAuthorize("user")
	public ResponseEntity<List<Developer>> read(){
		try {
			return new ResponseEntity<List<Developer>>(devService.fetchDeveloper(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<Developer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("update")
	@PreAuthorize("admin")
	public ResponseEntity<Developer> update(@RequestBody Developer developer, @RequestBody Long developerId){
		try {
			return new ResponseEntity<Developer>(devService.updateDeveloper(developer, developerId), HttpStatus.OK); 
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Developer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("delete")
	@PreAuthorize("admin")
	public ResponseEntity<Void> delete(@RequestBody Long developerId){
		try {
			devService.deleteDeveloperById(developerId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
