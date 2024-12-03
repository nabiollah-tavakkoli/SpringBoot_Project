package biz.aeffegroup.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import biz.aeffegroup.entity.Client;
import biz.aeffegroup.entity.Office;
import biz.aeffegroup.model.Info;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InfoService {

	@Autowired
	private ClientService clientservice;
	@Autowired
	private OfficeService officeservice;
	@Autowired
	private ModelMapper modelMapper;
	
	//requested create
	public List<Info> requestedFetch(){
		List<Office> officeList = null;
		List<Client> clientList = null;
		List<Info> infoList = new ArrayList<Info>();
		Info requestedInfo = new Info();
		try {
			officeList = officeservice.fetchOffice();
			clientList = clientservice.fetchClient();
			for(Office office : officeList) {
				for(Client client : clientList) {
					requestedInfo = convertOfficeToInfo(office, client);
					requestedInfo.setCreationDate(LocalDate.now());
					infoList.add(requestedInfo);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return infoList;
	}
	
	private Info convertOfficeToInfo(@RequestBody Office office, @RequestBody Client client) {
		TypeMap<Office, Info> typeMapOfficeToInfo = modelMapper.typeMap(Office.class, Info.class);
		typeMapOfficeToInfo.addMappings(mapper ->{
			mapper.map(Office::getId, Info::setOfficeId);
			mapper.map(Office::getName, Info::setOfficeName);

		});
		TypeMap<Client, Info> typeMapClientToInfo = modelMapper.typeMap(Client.class, Info.class);
		typeMapClientToInfo.addMappings(mapper ->{
			mapper.map(Client::getId, Info::setClinetId);
			mapper.map(Client::getName, Info::setClientName);

		});
		
		Info info = new Info();
		modelMapper.map(office, info);
		modelMapper.map(client, info);
		
		return info;
	}


}
