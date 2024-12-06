package biz.aeffegroup.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id", "roles", "role_List"})
public class RoleModel {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("roles")
	private String roles;

	@JsonIgnore
	@JsonProperty("user_List")
	private List<UserRoleModel> userList;
}
