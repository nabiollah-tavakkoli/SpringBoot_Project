package biz.aeffegroup.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import biz.aeffegroup.entity.OfficeEntity;
import biz.aeffegroup.entity.UserEntity;
import biz.aeffegroup.model.InfoModel;
import biz.aeffegroup.model.UserModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InfoService {

	@Autowired
	private UsersDetailsService userDetailsService;
	@Autowired
	private OfficeService officeservice;
	@Autowired
	private ModelMapper modelMapper;
	
	//requested create
	public List<InfoModel> requestedFetch(){
		List<OfficeEntity> officeList = null;
		List<UserModel> userModelList = null;
		List<UserEntity> userEntityList = new ArrayList<UserEntity>();
		List<InfoModel> infoList = new ArrayList<InfoModel>();
		InfoModel requestedInfo = new InfoModel();
		try {
			officeList = officeservice.fetchOffice();
			userModelList = userDetailsService.fetch();
			for(UserModel userModel : userModelList) {
				userEntityList.add(modelMapper.map(userModel, UserEntity.class));
			}
			for(OfficeEntity office : officeList) {
				for(UserEntity user : userEntityList) {
					requestedInfo = convertOfficeToInfo(office, user);
					requestedInfo.setCreationDate(LocalDate.now());
					infoList.add(requestedInfo);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return infoList;
	}
	
	private InfoModel convertOfficeToInfo(@RequestBody OfficeEntity office, @RequestBody UserEntity user) {
		TypeMap<OfficeEntity, InfoModel> typeMapOfficeToInfo = modelMapper.typeMap(OfficeEntity.class, InfoModel.class);
		typeMapOfficeToInfo.addMappings(mapper ->{
			mapper.map(OfficeEntity::getId, InfoModel::setOfficeId);
			mapper.map(OfficeEntity::getName, InfoModel::setOfficeName);

		});
		TypeMap<UserEntity, InfoModel> typeMapClientToInfo = modelMapper.typeMap(UserEntity.class, InfoModel.class);
		typeMapClientToInfo.addMappings(mapper ->{
			mapper.map(UserEntity::getId, InfoModel::setClinetId);
			mapper.map(UserEntity::getUsername, InfoModel::setClientName);

		});
		
		InfoModel info = new InfoModel();
		modelMapper.map(office, info);
		modelMapper.map(user, info);
		
		return info;
	}


}
