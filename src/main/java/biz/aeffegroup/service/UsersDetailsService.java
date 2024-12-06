package biz.aeffegroup.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import biz.aeffegroup.entity.UserEntity;
import biz.aeffegroup.entity.UserRoleEntity;
import biz.aeffegroup.model.UserModel;
import biz.aeffegroup.repository.UserRepository;
import biz.aeffegroup.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username);
		
		if(user == null) {
			log.error("username = " + username +", not found");
			throw new UsernameNotFoundException(username);
		}
		
		return new UsersPrincipal(user);
	}

	
	public List<UserModel> fetch(){
		List<UserEntity> userEntityList = null;
		List<UserModel> userModelList = new ArrayList<UserModel>();
		try {
			userEntityList = userRepository.findAll();
			for(UserEntity userEntity : userEntityList) {
				userModelList.add(modelMapper.map(userEntity, UserModel.class));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return userModelList;
	}

	//save/create
	public UserModel create(UserModel userModel) {
		List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();
		List<UserRoleEntity> roleEntityList = new ArrayList<UserRoleEntity>();
		UserEntity userEntity = new UserEntity();
		try {
			userEntity = modelMapper.map(userModel, UserEntity.class);
			userRoleEntityList = userRoleRepository.findAll();
			for(UserRoleEntity userRoleEntity : userRoleEntityList) {
				if(userRoleEntity.getUser().equals(userEntity)) {
					roleEntityList.add(userRoleEntity);
				}
			}
			userEntity.setRoleList(roleEntityList);
			userRepository.save(userEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return modelMapper.map(userRepository.findById(userEntity.getId()).orElseThrow(NullPointerException::new), UserModel.class);
	}

}
