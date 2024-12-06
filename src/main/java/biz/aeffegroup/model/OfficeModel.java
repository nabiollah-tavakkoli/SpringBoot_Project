package biz.aeffegroup.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"office_id", "office_name", "office_develoeprs"})
public class OfficeModel {

	@JsonProperty("office_id")
	private Long id;
	@JsonProperty("office_name")
	private String name;
	
	@JsonIgnore
	@JsonProperty("office_develoeprs")
	private List<DeveloperModel> developers;
	
}
