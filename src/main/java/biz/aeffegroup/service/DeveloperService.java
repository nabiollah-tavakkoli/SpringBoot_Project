package biz.aeffegroup.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.aeffegroup.entity.Developer;
import biz.aeffegroup.repository.DeveloperRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeveloperService {
	
	@Autowired
	private DeveloperRepository devRep;
	
	public Developer saveDeveloper(Developer developer) {
		try {
			devRep.save(developer);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return devRep.findById(developer.getId()).orElseThrow(NullPointerException::new);
	}
	
	// read
	public Developer findClientById(Long clientId) {
		Developer developer = null;
		try {
			developer = devRep.findById(clientId).orElseThrow(NullPointerException::new);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return developer ;
	}
	
	public List<Developer> fetchDeveloper(){
		Iterable<Developer> devIter = null;
		try {
			devIter = devRep.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return StreamSupport.stream(devIter.spliterator(), false).collect(Collectors.toList());
	}
	
	public Developer updateDeveloper(Developer developer, Long developerId) {
		Developer dev = null;
		try {
			dev = devRep.findById(developerId).orElseThrow(NullPointerException::new);
			if(Objects.nonNull(developer) && !"".equals(developer.getName())) {
				dev.setName(developer.getName());
				dev.setSurName(developer.getSurName());
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
