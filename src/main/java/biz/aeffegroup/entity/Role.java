package biz.aeffegroup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
 @Table(name = "role")
public class Role {
	
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "roles")
	private String roles;

}
