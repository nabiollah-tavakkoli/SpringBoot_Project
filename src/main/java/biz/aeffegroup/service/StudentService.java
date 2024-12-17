package biz.aeffegroup.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.aeffegroup.entity.StudentEntity;
import biz.aeffegroup.exception.MyEntityNotFoundException;
import biz.aeffegroup.model.CourseModel;
import biz.aeffegroup.model.StudentModel;
import biz.aeffegroup.repository.CourseRepository;
import biz.aeffegroup.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = {MyEntityNotFoundException.class})
@Slf4j
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	//create
	public StudentModel create(Long student_id, Long course_id) {
		StudentEntity studentEntity = new StudentEntity();
		//Set<CourseEntity> courseEntitySet = new HashSet<CourseEntity>();
		
		StudentModel studentModel = new StudentModel();
		Set<CourseModel> courseModels = new HashSet<CourseModel>();
		try {
			studentEntity = studentRepository.findById(student_id).orElseThrow(NullPointerException::new);
			
			studentModel = modelMapper.map(studentEntity, StudentModel.class);
			courseModels = studentModel.getCourseSet();
			if(!courseRepository.existsById(course_id)) {
				throw new MyEntityNotFoundException("not found any course to add");
			}
			courseModels.add(modelMapper.map(courseRepository.findById(course_id).orElseThrow(NullPointerException::new), CourseModel.class));
			studentModel.setCourseSet(courseModels);
			studentRepository.save(modelMapper.map(studentModel, StudentEntity.class));
			//courseEntitySet = studentEntity.getCourseSet();
			//courseEntitySet.add(courseRepository.findById(course_id).orElseThrow(NullPointerException::new));
			//studentEntity.setCourseSet(courseEntitySet);
			//studentRepository.save(studentEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return modelMapper.map(studentRepository.findById(studentEntity.getId()), StudentModel.class);
	}
	
	//read
	public List<StudentModel> read(){
		List<StudentModel> studentModels = new ArrayList<StudentModel>();
		List<StudentEntity> studentEntities = new ArrayList<StudentEntity>();
		try {
			studentEntities = studentRepository.findAll();
			for(StudentEntity studentEntity : studentEntities) {
				studentModels.add(modelMapper.map(studentEntity, StudentModel.class));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return studentModels;
	}

}
