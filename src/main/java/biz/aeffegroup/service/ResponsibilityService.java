package biz.aeffegroup.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.aeffegroup.entity.DeveloperEntity;
import biz.aeffegroup.entity.ResponsibilityEntity;
import biz.aeffegroup.model.ResponsibilityModel;
import biz.aeffegroup.repository.DeveloperRepository;
import biz.aeffegroup.repository.OfficeRepository;
import biz.aeffegroup.repository.ResponsibilityRepository;
import biz.aeffegroup.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResponsibilityService {
	
	@Autowired
	private ResponsibilityRepository resRep;
	@Autowired
	private DeveloperRepository developerRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OfficeRepository officeRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponsibilityModel create(Long id_responsibility, Long id_office, Long id_user, Long id_developer) {
		ResponsibilityEntity responsibilityEntity = new ResponsibilityEntity();
		Set<DeveloperEntity> developerEntitiesList = new HashSet<DeveloperEntity>();
		try {
			responsibilityEntity = resRep.findById(id_responsibility).orElseThrow(NullPointerException::new);
			if(Objects.nonNull(responsibilityEntity.getDevelopers())) {
				developerEntitiesList = responsibilityEntity.getDevelopers();
			}
			
			developerEntitiesList.add(developerRepository.findById(id_developer).orElseThrow(NullPointerException::new));
			responsibilityEntity.setDevelopers(developerEntitiesList);
			
			responsibilityEntity.setUsers(userRepository.findById(id_user).orElseThrow(NullPointerException::new));
			responsibilityEntity.setOffice(officeRepository.findById(id_office).orElseThrow(NullPointerException::new));
			resRep.save(responsibilityEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return modelMapper.map(resRep.findById(responsibilityEntity.getId()).orElseThrow(NullPointerException::new), ResponsibilityModel.class);
	}
	
	// read
	public ResponsibilityEntity findClientById(Long clientId) {
		ResponsibilityEntity responsible = null;
		try {
			responsible = resRep.findById(clientId).orElseThrow(NullPointerException::new);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return responsible ;
	}
	
	public ResponsibilityEntity updateResponsible(ResponsibilityEntity responsible, Long RespId) {
		ResponsibilityEntity res = null;
		try {
			res = resRep.findById(RespId).orElseThrow(NullPointerException::new);
			if(Objects.nonNull(responsible) && !"".equals(responsible.getName())) {
				res.setName(responsible.getName());
				res.setResponsibility(responsible.getResponsibility());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return resRep.findById(RespId).orElseThrow(NullPointerException::new);
	}
	
	public List<ResponsibilityModel> fetch(){
		List<ResponsibilityEntity> respEntityList = null;
		List<ResponsibilityModel> respModelList = new ArrayList<ResponsibilityModel>();
		try {
			respEntityList = resRep.findAll();
			for(ResponsibilityEntity responsibilityEntity : respEntityList) {
				respModelList.add(modelMapper.map(responsibilityEntity, ResponsibilityModel.class));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return respModelList;
	}
	
	public void deleteResponsible(Long respId) {
		try {
			resRep.deleteById(respId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
