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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import biz.aeffegroup.entity.DeveloperEntity;
import biz.aeffegroup.model.DeveloperModel;
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
	public ResponseEntity<DeveloperModel> create(
			@RequestParam Long id_developer, 
			@RequestParam Long id_office, 
			@RequestParam Long id_user, 
			@RequestParam Long id_responsibility){
		try {
			return new ResponseEntity<DeveloperModel>(devService.create(id_developer, id_office, id_user, id_responsibility), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<DeveloperModel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("read")
	@PreAuthorize("user")
	public ResponseEntity<List<DeveloperModel>> read(){
		try {
			return new ResponseEntity<List<DeveloperModel>>(devService.fetchDeveloperModel(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<DeveloperModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("update")
	@PreAuthorize("admin")
	public ResponseEntity<DeveloperEntity> update(@RequestBody DeveloperEntity developer, @RequestBody Long developerId){
		try {
			return new ResponseEntity<DeveloperEntity>(devService.updateDeveloper(developer, developerId), HttpStatus.OK); 
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<DeveloperEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
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
