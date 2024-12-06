package biz.aeffegroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import biz.aeffegroup.model.UserRoleModel;
import biz.aeffegroup.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("userrole")
public class UserRoleController {
	
	@Autowired
	private UserRoleService userRoleService;
	
	@PostMapping("create")
	@PreAuthorize("admin")
	public ResponseEntity<UserRoleModel> create(@RequestParam Long id_user, @RequestParam Long id_role) throws Exception{
		try {
			return new ResponseEntity<UserRoleModel>(userRoleService.create(id_user, id_role), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<UserRoleModel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("read")
	@PreAuthorize("'user', 'admin'")
	public ResponseEntity<List<UserRoleModel>> read(){
		try {
			return new ResponseEntity<List<UserRoleModel>>(userRoleService.fetch(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<UserRoleModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
