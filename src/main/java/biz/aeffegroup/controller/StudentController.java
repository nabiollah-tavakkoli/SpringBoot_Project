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

import biz.aeffegroup.model.StudentModel;
import biz.aeffegroup.service.StudentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("student")
public class StudentController {

	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("create")
	@PreAuthorize("admin")
	public ResponseEntity<StudentModel> create(
			@RequestParam Long student_id,
			@RequestParam Long course_id){
		try {
			return new ResponseEntity<StudentModel>(studentService.create(student_id, course_id), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<StudentModel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("read")
	@PreAuthorize("user")
	public ResponseEntity<List<StudentModel>> read(){
		try {
			return new ResponseEntity<List<StudentModel>>(studentService.read(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<StudentModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
