package biz.aeffegroup.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.aeffegroup.entity.Client;
import biz.aeffegroup.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRep;
	
	// save/create
	public Client saveClient(Client client) {
		try {
			clientRep.save(client);
		} catch (Exception e) {
			 log.error(e.getMessage());
		}
		return clientRep.findById(client.getId()).orElseThrow(NullPointerException::new);
	}
	
	// read
	public Client findClientById(Long clientId) {
		Client client = null;
		try {
			client = clientRep.findById(clientId).orElseThrow(NullPointerException::new);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return client ;
	}
	
	// read 
	public List<Client> fetchClient(){
		Iterable<Client> clientIter = null;
		try {
			clientIter = clientRep.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		// StreamSupport and spliterator() are used for getting a List of certain objects instead of Tterable
		return StreamSupport.stream(clientIter.spliterator(), false).collect(Collectors.toList());
	}
	
	// update
	public Client udpateClient(Client client, Long clientId) {
		Client foundedClient = null;
		try {
			foundedClient = clientRep.findById(clientId).orElseThrow(NullPointerException::new);
			if(Objects.nonNull(client) && !"".equals(client.getName())) {
				foundedClient.setName(client.getName());
				foundedClient.setSurName(client.getSurName());
				foundedClient.setCompanyName(client.getCompanyName());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return clientRep.findById(clientId).orElseThrow(NullPointerException::new) ;
	}

	// delete
	public void deleteClientById(Long departmentId) {
		try {
			clientRep.deleteById(departmentId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}

}
