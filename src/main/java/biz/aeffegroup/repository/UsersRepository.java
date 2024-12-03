package biz.aeffegroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biz.aeffegroup.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	Users findByUsername(String username);
}
