package biz.aeffegroup.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import biz.aeffegroup.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{ // CrudRepository<Entity , tipo_entity>

	

}
