package biz.aeffegroup.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.aeffegroup.entity.RoleEntity;
import biz.aeffegroup.entity.UserRoleEntity;
import biz.aeffegroup.model.RoleModel;
import biz.aeffegroup.repository.RoleRepository;
import biz.aeffegroup.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ModelMapper modelMapper;

	public RoleModel create(RoleModel roleModel) {
		List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();
		List<UserRoleEntity> userEntityList = new ArrayList<UserRoleEntity>();
		RoleEntity roleEntity = new RoleEntity();
		try {
			roleEntity = modelMapper.map(roleModel, RoleEntity.class);
			userRoleEntityList = userRoleRepository.findAll();
			for(UserRoleEntity userRoleEntity : userRoleEntityList) {
				if(userRoleEntity.getUser().equals(roleEntity)) {
					userEntityList.add(userRoleEntity);
				}
			}
			roleEntity.setUserList(userEntityList);
			roleRepository.save(roleEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return modelMapper.map(roleRepository.findById(roleEntity.getId()).orElseThrow(NullPointerException::new), RoleModel.class);
	}
	
	public List<RoleModel> fetch(){
		List<RoleEntity> roleEntityList = new ArrayList<RoleEntity>();
		List<RoleModel> roleModelList = new ArrayList<RoleModel>();
		try {
			roleEntityList = roleRepository.findAll();
			for(RoleEntity roleEntity : roleEntityList) {
				roleModelList.add(modelMapper.map(roleEntity, RoleModel.class));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return roleModelList;
	}
}
