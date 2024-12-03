package biz.aeffegroup.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.aeffegroup.entity.Responsible;
import biz.aeffegroup.repository.ResponsibleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResponsibleService {
	
	@Autowired
	private ResponsibleRepository resRep;
	
	public Responsible saveResponsibile(Responsible responsible) {
		try {
			resRep.save(responsible);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return resRep.findById(responsible.getId()).orElseThrow(NullPointerException::new);
	}
	
	// read
	public Responsible findClientById(Long clientId) {
		Responsible responsible = null;
		try {
			responsible = resRep.findById(clientId).orElseThrow(NullPointerException::new);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return responsible ;
	}
	
	public Responsible updateResponsible(Responsible responsible, Long RespId) {
		Responsible res = null;
		try {
			res = resRep.findById(RespId).orElseThrow(NullPointerException::new);
			if(Objects.nonNull(responsible) && !"".equals(responsible.getName())) {
				res.setName(responsible.getName());
				res.setSurNmae(res.getSurNmae());
				res.setResponsibility(responsible.getResponsibility());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return resRep.findById(RespId).orElseThrow(NullPointerException::new);
	}
	
	public List<Responsible> fetchResponsible(){
		Iterable<Responsible> respIter = null;
		try {
			respIter = resRep.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return StreamSupport.stream(respIter.spliterator(), false).collect(Collectors.toList());
	}
	
	public void deleteResponsible(Long respId) {
		try {
			resRep.deleteById(respId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
