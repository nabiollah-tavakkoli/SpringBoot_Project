package biz.aeffegroup.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.aeffegroup.entity.CourseEntity;
import biz.aeffegroup.entity.StudentEntity;
import biz.aeffegroup.model.CourseModel;
import biz.aeffegroup.repository.CourseRepository;
import biz.aeffegroup.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	//create
	public CourseModel create(Long course_id, Long student_id) {
		CourseEntity courseEntity = new CourseEntity();
		Set<StudentEntity> studentEntitySet = new HashSet<StudentEntity>();
		try {
			courseEntity = courseRepository.findById(course_id).orElseThrow(NullPointerException::new);
			if(Objects.nonNull(courseEntity.getStudentSet())) {
				studentEntitySet.add(studentRepository.findById(student_id).orElseThrow(NullPointerException::new));
			}
			courseEntity.setStudentSet(studentEntitySet);
			courseRepository.save(courseEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return modelMapper.map(studentRepository.findById(courseEntity.getId()), CourseModel.class);
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
