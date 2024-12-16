package biz.aeffegroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import biz.aeffegroup.model.CourseModel;
import biz.aeffegroup.service.CourseService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("course")
public class CourseController {


	
	@Autowired
	private CourseService courseService;
	
	@PostMapping("create")
	@PreAuthorize("admin")
	public ResponseEntity<CourseModel> create(
			@RequestParam Long student_id,
			@RequestParam Long course_id){
		try {
			return new ResponseEntity<CourseModel>(courseService.create(student_id, course_id), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<CourseModel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("read")
	@PreAuthorize("user")
	public ResponseEntity<List<CourseModel>> read(){
		try {
			return new ResponseEntity<List<CourseModel>>(courseService.read(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<CourseModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
