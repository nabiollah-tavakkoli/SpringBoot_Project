package biz.aeffegroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biz.aeffegroup.entity.ResponsibilityEntity;

@Repository
public interface ResponsibilityRepository extends JpaRepository<ResponsibilityEntity, Long> {

}
