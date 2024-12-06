package biz.aeffegroup.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id", "id_user", "id_role"})
public class UserRoleModel {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("id_user")
	private UserModel user;
	
	@JsonProperty("id_role")
	private RoleModel role;
}
