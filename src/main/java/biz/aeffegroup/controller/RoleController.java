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

import biz.aeffegroup.model.RoleModel;
import biz.aeffegroup.service.RoleService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@PostMapping("create")
	@PreAuthorize("admin")
	public ResponseEntity<RoleModel> create(@RequestBody RoleModel role){
		try {
			return new ResponseEntity<RoleModel>(roleService.create(role), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<RoleModel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("read")
	@PreAuthorize("user")
	public ResponseEntity<List<RoleModel>> read(){
		try {
			return new ResponseEntity<List<RoleModel>>(roleService.fetch(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<RoleModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
