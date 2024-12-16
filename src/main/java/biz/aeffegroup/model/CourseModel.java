package biz.aeffegroup.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "studentset"})
public class CourseModel {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("studentset")
	@JsonManagedReference
	private Set<StudentModel> studentSet;
}
