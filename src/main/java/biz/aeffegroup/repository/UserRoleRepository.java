package biz.aeffegroup.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import biz.aeffegroup.entity.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
