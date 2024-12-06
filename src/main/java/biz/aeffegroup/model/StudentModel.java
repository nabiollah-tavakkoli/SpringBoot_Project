package biz.aeffegroup.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "surname"})
public class StudentModel {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("surname")
	private String surname;
	
	@JsonProperty("courseset")
	private Set<CourseModel> courseSet;
}
