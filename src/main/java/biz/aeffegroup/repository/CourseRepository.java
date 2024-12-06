package biz.aeffegroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import biz.aeffegroup.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

}
