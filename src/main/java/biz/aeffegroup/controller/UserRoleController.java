package biz.aeffegroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import biz.aeffegroup.entity.UserRole;
import biz.aeffegroup.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("userrole")
public class UserRoleController {
	
	@Autowired
	private UserRoleService userRoleService;
	
	@GetMapping("read")
	@PreAuthorize("user")
	public ResponseEntity<List<UserRole>> read(){
		try {
			return new ResponseEntity<List<UserRole>>(userRoleService.fetch(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<UserRole>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
