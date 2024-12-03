package biz.aeffegroup.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.aeffegroup.entity.Office;
import biz.aeffegroup.repository.OfficeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OfficeService {
	
	@Autowired
	private OfficeRepository officeRep;
	
	public Office saveOffice(Office office) {
		try {
			officeRep.save(office);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return officeRep.findById(office.getId()).orElseThrow(NullPointerException::new);
	}
	
	// read
	public Office findClientById(Long clientId) {
		Office office = null;
		try {
			office = officeRep.findById(clientId).orElseThrow(NullPointerException::new);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return office ;
	}
	
	public Office udpateOffice(Office office, Long OfficeId) {
		Office of = null;
		try {
			of = officeRep.findById(OfficeId).orElseThrow(NullPointerException::new);
			if(Objects.nonNull(office) && !"".equals(office.getName())) {
				of.setName(office.getName());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return officeRep.findById(OfficeId).orElseThrow(NullPointerException::new);
	}
	
	public List<Office> fetchOffice(){
		List<Office> officeIter = null;
		try {
			officeIter = officeRep.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return officeIter;
	}
	
	public void deleteOfficeByIf(Long OfficeId) {
		try {
			officeRep.deleteById(OfficeId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
