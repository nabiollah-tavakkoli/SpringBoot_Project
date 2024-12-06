package biz.aeffegroup.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id", "username", "password", "role", "userList"})
public class UserModel {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonProperty("role")
	private String role;

	@JsonIgnore
	@JsonProperty("role_List")
	private List<UserRoleModel> roleList;
}
