package biz.aeffegroup.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.aeffegroup.entity.UserRoleEntity;
import biz.aeffegroup.model.UserRoleModel;
import biz.aeffegroup.repository.RoleRepository;
import biz.aeffegroup.repository.UserRepository;
import biz.aeffegroup.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	// read
	public List<UserRoleModel> fetch(){
		List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();
		List<UserRoleModel> userRoleModelList = new ArrayList<UserRoleModel>();
		try {
			userRoleEntityList = userRoleRepository.findAll();
			for(UserRoleEntity entity : userRoleEntityList) {
				userRoleModelList.add(modelMapper.map(entity, UserRoleModel.class));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return userRoleModelList;
	}
	
	// create
	public UserRoleModel create(Long id_user, Long id_role) {
		UserRoleEntity userRoleEntity = new UserRoleEntity();
		try {
			userRoleEntity.setUser(userRepository.findById(id_user).orElseThrow(NullPointerException::new));
			userRoleEntity.setRole(roleRepository.findById(id_role).orElseThrow(NullPointerException::new));
			userRoleRepository.save(userRoleEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
			
		}
		return modelMapper.map(userRoleRepository.findById(userRoleEntity.getId()), UserRoleModel.class);
	}

}
