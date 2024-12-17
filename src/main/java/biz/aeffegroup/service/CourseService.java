package biz.aeffegroup.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.aeffegroup.entity.CourseEntity;
import biz.aeffegroup.exception.MyEntityNotFoundException;
import biz.aeffegroup.model.CourseModel;
import biz.aeffegroup.model.StudentModel;
import biz.aeffegroup.repository.CourseRepository;
import biz.aeffegroup.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = {MyEntityNotFoundException.class})
@Slf4j
public class CourseService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	//create
	public CourseModel create(Long student_id, Long course_id) {
		CourseEntity courseEntity = new CourseEntity();
		//Set<StudentEntity> studentEntitySet = new HashSet<StudentEntity>();
		
		CourseModel courseModel = new CourseModel();
		Set<StudentModel> studentModelSet = new HashSet<StudentModel>();
		try {
			courseEntity = courseRepository.findById(course_id).orElseThrow(NullPointerException::new);
			
			courseModel = modelMapper.map(courseEntity, CourseModel.class);
			studentModelSet = courseModel.getStudentSet();
			if(!studentRepository.existsById(student_id)) {
				throw new MyEntityNotFoundException("not found any student to add");
			}
			studentModelSet.add(modelMapper.map(studentRepository.findById(student_id).orElseThrow(NullPointerException::new), StudentModel.class));
			courseModel.setStudentSet(studentModelSet);
			courseRepository.save(modelMapper.map(courseModel, CourseEntity.class));
			//studentEntitySet = courseEntity.getStudentSet();
			//studentEntitySet.add(studentRepository.findById(student_id).orElseThrow(NullPointerException::new));
			//courseEntity.setStudentSet(studentEntitySet);
			//courseRepository.save(courseEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return modelMapper.map(courseRepository.findById(courseEntity.getId()), CourseModel.class);
	}
	
	//read
	public List<CourseModel> read(){
		List<CourseModel> courseModels = new ArrayList<CourseModel>();
		List<CourseEntity> courseEntities = new ArrayList<CourseEntity>();
		try {
			courseEntities = courseRepository.findAll();
			for(CourseEntity studentEntity : courseEntities) {
				courseModels.add(modelMapper.map(studentEntity, CourseModel.class));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return courseModels;
	}
}
