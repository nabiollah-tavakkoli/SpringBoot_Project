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

import biz.aeffegroup.entity.Responsible;
import biz.aeffegroup.service.ResponsibleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("responsible")
public class ResponsibleController {
	
	@Autowired
	private ResponsibleService responsibleService;
	
	@PostMapping("create")
	@PreAuthorize("admin")
	public ResponseEntity<Responsible> create(@RequestBody Responsible responsible){
		try {
			return new ResponseEntity<Responsible>(responsibleService.saveResponsibile(responsible), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Responsible>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("read")
	@PreAuthorize("user")
	public ResponseEntity<List<Responsible>> read(){
		try {
			return new ResponseEntity<List<Responsible>>(responsibleService.fetchResponsible(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<Responsible>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("update")
	@PreAuthorize("admin")
	public ResponseEntity<Responsible> update(@RequestBody Responsible responsible, @RequestBody Long ResponsibleId){
		try {
			return new ResponseEntity<Responsible>(responsibleService.updateResponsible(responsible, ResponsibleId), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Responsible>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("delete")
	@PreAuthorize("admin")
	public ResponseEntity<Void> delete(@RequestBody Long ResponsibleId){
		try {
			responsibleService.deleteResponsible(ResponsibleId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
