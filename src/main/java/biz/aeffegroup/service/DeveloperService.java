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
import biz.aeffegroup.model.DeveloperModel;
import biz.aeffegroup.repository.DeveloperRepository;
import biz.aeffegroup.repository.OfficeRepository;
import biz.aeffegroup.repository.ResponsibilityRepository;
import biz.aeffegroup.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeveloperService {
	
	@Autowired
	private DeveloperRepository devRep;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OfficeRepository officeRepository;
	@Autowired
	private ResponsibilityRepository responsibilityRepository;
	@Autowired
	private ModelMapper modelMapper;

	public DeveloperModel create(Long id_developer, Long id_office, Long id_user, Long id_responsibility) {
		DeveloperEntity developerEnt = null;
		Set<ResponsibilityEntity> developerResponsibilityEntities = new HashSet<ResponsibilityEntity>();
		
		try {
			developerEnt = devRep.findById(id_developer).orElseThrow(NullPointerException::new);
			
			if(Objects.nonNull(developerEnt.getResponsibilites())) {
				developerResponsibilityEntities = developerEnt.getResponsibilites();
			}
			
			responsibilityRepository.findById(id_responsibility).ifPresent(developerResponsibilityEntities::add);
			developerEnt.setResponsibilites(developerResponsibilityEntities);
			
			developerEnt.setUsers(userRepository.findById(id_user).orElseThrow(NullPointerException::new));
			developerEnt.setOffice(officeRepository.findById(id_office).orElseThrow(NullPointerException::new));
			
			devRep.save(developerEnt);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return modelMapper.map(devRep.findById(developerEnt.getId()).orElseThrow(NullPointerException::new), DeveloperModel.class);
	}
	
	// read
	public DeveloperEntity findClientById(Long clientId) {
		DeveloperEntity developer = null;
		try {
			developer = devRep.findById(clientId).orElseThrow(NullPointerException::new);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return developer ;
	}
	
	public List<DeveloperEntity> fetchDeveloper(){
		List<DeveloperEntity> developerList = null;
		try {
			developerList = devRep.findAll();
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return developerList;
	}
	
	public List<DeveloperModel> fetchDeveloperModel(){
		List<DeveloperEntity> developerEntityList = null;
		List<DeveloperModel> developerModelList = new ArrayList<DeveloperModel>();
		try {
			developerEntityList = fetchDeveloper();
			for(DeveloperEntity developerEntity : developerEntityList) {
				developerModelList.add(modelMapper.map(developerEntity, DeveloperModel.class));
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return developerModelList;
	}
	
	public DeveloperEntity updateDeveloper(DeveloperEntity developer, Long developerId) {
		DeveloperEntity dev = null;
		try {
			dev = devRep.findById(developerId).orElseThrow(NullPointerException::new);
			if(Objects.nonNull(developer) && !"".equals(developer.getName())) {
				dev.setName(developer.getName());
				dev.setSurname(developer.getSurname());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return devRep.findById(developerId).orElseThrow(NullPointerException::new);
	}
	
	public void deleteDeveloperById(Long developerId) {
		try {
			devRep.deleteById(developerId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}

}
