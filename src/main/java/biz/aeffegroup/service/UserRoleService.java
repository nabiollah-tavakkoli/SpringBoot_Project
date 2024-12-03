package biz.aeffegroup.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.aeffegroup.entity.UserRole;
import biz.aeffegroup.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	public List<UserRole> fetch(){
		return StreamSupport.stream(userRoleRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

}
