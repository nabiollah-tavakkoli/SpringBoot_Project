package biz.aeffegroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biz.aeffegroup.model.InfoModel;
import biz.aeffegroup.model.UserModel;
import biz.aeffegroup.service.InfoService;
import biz.aeffegroup.service.UsersDetailsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("info")
public class InfoController {
	
	@Autowired
	private InfoService infoService;
	
	@Autowired
	private UsersDetailsService usersDetailsService;

	@GetMapping("requestedread")
	@PreAuthorize("user")
	public ResponseEntity<List<InfoModel>> RequestedRead(){
		try {
			return new ResponseEntity<List<InfoModel>>(infoService.requestedFetch(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<InfoModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("readusers")
	@PreAuthorize("admin")
	public ResponseEntity<List<UserModel>> readusers(){
		try {
			return new ResponseEntity<List<UserModel>>(usersDetailsService.fetch(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<UserModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
