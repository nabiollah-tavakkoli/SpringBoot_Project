package biz.aeffegroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biz.aeffegroup.entity.Client;
import biz.aeffegroup.service.ClientService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("create")
	@PreAuthorize("admin")
	public ResponseEntity<Client> create(@RequestBody Client client) throws Exception{
		try {
			return new ResponseEntity<Client>(clientService.saveClient(client), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Client>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("read")
	@PreAuthorize("user")
	public ResponseEntity<List<Client>> read(){
		try {
			return new ResponseEntity<List<Client>>(clientService.fetchClient(), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<List<Client>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("update")
	@PreAuthorize("admin")
	public ResponseEntity<Client> update(@RequestBody Client client, @RequestBody Long clientId){
		try {
			return new ResponseEntity<Client>(clientService.udpateClient(client, clientId), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Client>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("delete")
	public ResponseEntity<Void> delete(@RequestBody Long clientId){
		try {
			clientService.deleteClientById(clientId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
