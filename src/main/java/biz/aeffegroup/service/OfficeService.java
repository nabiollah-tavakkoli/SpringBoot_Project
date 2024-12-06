package biz.aeffegroup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.aeffegroup.entity.OfficeEntity;
import biz.aeffegroup.model.OfficeModel;
import biz.aeffegroup.repository.OfficeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OfficeService {
	
	@Autowired
	private OfficeRepository officeRep;
	@Autowired
	private ModelMapper modelMapper;
	
	
	public OfficeEntity saveOffice(OfficeEntity office) {
		try {
			
			officeRep.save(office);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return officeRep.findById(office.getId()).orElseThrow(NullPointerException::new);
	}
	
	// read
	public OfficeEntity findClientById(Long clientId) {
		OfficeEntity office = null;
		try {
			office = officeRep.findById(clientId).orElseThrow(NullPointerException::new);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return office ;
	}
	
	public OfficeEntity udpateOffice(OfficeEntity office, Long OfficeId) {
		OfficeEntity of = null;
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
	
	public List<OfficeEntity> fetchOffice(){
		List<OfficeEntity> officeEntityList = null;
		try {
			officeEntityList = officeRep.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return officeEntityList;
	}
	
	public List<OfficeModel> fetchOfficeModel(){
		List<OfficeEntity> officeEntityList = null;
		List<OfficeModel> officeModelList = new ArrayList<OfficeModel>();
		try {
			officeEntityList = fetchOffice();
			for(OfficeEntity officeEntity : officeEntityList) {
				officeModelList.add(modelMapper.map(officeEntity, OfficeModel.class));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return officeModelList;
	}
	
	public void deleteOfficeByIf(Long OfficeId) {
		try {
			officeRep.deleteById(OfficeId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
}
