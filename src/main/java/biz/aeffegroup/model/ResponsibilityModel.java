package biz.aeffegroup.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "SurName", "id_office", "id_user"})
public class ResponsibilityModel {


	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("responsibility")
	private String responsibility;

	@JsonProperty("id_office")
	private OfficeModel office;

	@JsonProperty("id_user")
	private UserModel users;
	
	@JsonProperty("developer_id")
	private Set<DeveloperModel> developers;
}
