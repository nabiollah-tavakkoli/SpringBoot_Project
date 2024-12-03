package biz.aeffegroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biz.aeffegroup.entity.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

}
