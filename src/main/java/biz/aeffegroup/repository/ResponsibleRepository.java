package biz.aeffegroup.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import biz.aeffegroup.entity.Responsible;

@Repository
public interface ResponsibleRepository extends CrudRepository<Responsible, Long> {

}
