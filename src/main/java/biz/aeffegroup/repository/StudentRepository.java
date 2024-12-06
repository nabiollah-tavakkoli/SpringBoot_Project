package biz.aeffegroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import biz.aeffegroup.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
