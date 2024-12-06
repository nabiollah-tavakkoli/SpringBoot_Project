package biz.aeffegroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import biz.aeffegroup.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
