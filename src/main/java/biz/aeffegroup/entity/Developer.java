package biz.aeffegroup.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="developer")
public class Developer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String SurName;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_office")
	private Office office;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private Users users;
}
