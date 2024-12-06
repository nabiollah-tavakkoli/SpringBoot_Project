package biz.aeffegroup.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="responsibilityentity")
public class ResponsibilityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "responsibility")
	private String responsibility;
	
	@ManyToOne
	@JoinColumn(name = "id_office")
	private OfficeEntity office;
	
	@OneToOne
	@JoinColumn(name = "id_user")
	private UserEntity users;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "developer_responsibility",
			joinColumns = @JoinColumn(name = "responsibility_id"),
			inverseJoinColumns = @JoinColumn(name = "developer_id"))
	private Set<DeveloperEntity> developers;
}
