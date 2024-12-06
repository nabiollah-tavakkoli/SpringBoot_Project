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

import biz.aeffegroup.entity.ResponsibilityEntity;
import biz.aeffegroup.model.ResponsibilityModel;
import biz.aeffegroup.service.ResponsibilityService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("responsibility")
public class ResponsibilityController {
	
	@Autowired
	private ResponsibilityService responsibilityService;
	
	@PostMapping("create")
	@PreAuthorize("admin")
	public ResponseEntity<ResponsibilityModel> create(
			@RequestParam Long id_responsibility, 
			@RequestParam Long id_office, 
			@RequestParam Long id_user, 
			@RequestParam Long id_developer){
		try {
			return new ResponseEntity<ResponsibilityModel>(responsibilityService.create(id_responsibility, id_office, id_user, id_developer), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<ResponsibilityModel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("read")
	@PreAuthorize("user")
	public ResponseEntity<List<ResponsibilityModel>> read(){
		try {
			return new ResponseEntity<List<ResponsibilityModel>>(responsibilityService.fetch(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<ResponsibilityModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("update")
	@PreAuthorize("admin")
	public ResponseEntity<ResponsibilityEntity> update(@RequestBody ResponsibilityEntity responsible, @RequestBody Long ResponsibleId){
		try {
			return new ResponseEntity<ResponsibilityEntity>(responsibilityService.updateResponsible(responsible, ResponsibleId), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<ResponsibilityEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("delete")
	@PreAuthorize("admin")
	public ResponseEntity<Void> delete(@RequestBody Long ResponsibleId){
		try {
			responsibilityService.deleteResponsible(ResponsibleId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
