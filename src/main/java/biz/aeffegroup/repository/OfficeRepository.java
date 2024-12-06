package biz.aeffegroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biz.aeffegroup.entity.OfficeEntity;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity, Long> {

}
