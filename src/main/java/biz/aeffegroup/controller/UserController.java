package biz.aeffegroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biz.aeffegroup.model.UserModel;
import biz.aeffegroup.service.UsersDetailsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UsersDetailsService usersDetailsService;
	
	@PostMapping("create")
	@PreAuthorize("admin")
	public ResponseEntity<UserModel> create(@RequestBody UserModel user){
		try {
			return new ResponseEntity<UserModel>(usersDetailsService.create(user), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<UserModel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("read")
	@PreAuthorize("user")
	public ResponseEntity<List<UserModel>> read(){
		try {
			return new ResponseEntity<List<UserModel>>(usersDetailsService.fetch(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<UserModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
