package biz.aeffegroup.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "surname", "id_office", "id_user"})
public class DeveloperModel {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("surname")
	private String surname;
	
	@JsonProperty("id_office")
	private OfficeModel office;

	@JsonProperty("id_user")
	private UserModel users;
	
	@JsonProperty("responsibilities_id")
	private Set<ResponsibilityModel> responsibilites;
}
