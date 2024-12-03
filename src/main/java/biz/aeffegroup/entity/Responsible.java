package biz.aeffegroup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="responsible")
public class Responsible {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "SurNmae")
	private String SurNmae;
	@Column(name = "responsibility")
	private String responsibility;
	
	@ManyToOne
	@JoinColumn(name = "id_office")
	private Office office;
	
	@OneToOne
	@JoinColumn(name = "id_user")
	private Users users;
}
