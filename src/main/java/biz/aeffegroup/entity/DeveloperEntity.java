package biz.aeffegroup.entity;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="developer")
public class DeveloperEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	//@JoinColumn(name = "id_office")
	@JoinTable(
			name = "developerOffice", 
			joinColumns = @JoinColumn(name = "id_developer"), 
			inverseJoinColumns = @JoinColumn(name = "id_office"))
	private OfficeEntity office;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	//@JoinColumn(name = "id_user")
	@JoinTable(
			name = "developerUser", 
			joinColumns = @JoinColumn(name = "id_developer"),
			inverseJoinColumns = @JoinColumn(name = "id_user"))
	private UserEntity users;
	
	@ManyToMany(mappedBy = "developers")
	private Set<ResponsibilityEntity> responsibilites;
	
}
