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

import biz.aeffegroup.entity.Office;
import biz.aeffegroup.service.OfficeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("office")
public class OfficeController {
	
	@Autowired
	private OfficeService officeService;
	
	@PostMapping("create")
	@PreAuthorize("admin")
	public ResponseEntity<Office> create(@RequestBody Office office){
		try {
			return new ResponseEntity<Office>(officeService.saveOffice(office), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Office>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("read")
	@PreAuthorize("user")
	public ResponseEntity<List<Office>> read(){
		try {
			return new ResponseEntity<List<Office>>(officeService.fetchOffice(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<Office>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("update")
	@PreAuthorize("admin")
	public ResponseEntity<Office> update(@RequestBody Office office, Long officeId){
		try {
			return new ResponseEntity<Office>(officeService.udpateOffice(office, officeId), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Office>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("delete")
	@PreAuthorize("admin")
	public ResponseEntity<Void> delete(@RequestBody Long officeId){
		try {
			officeService.deleteOfficeByIf(officeId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
