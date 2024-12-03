package biz.aeffegroup.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"office_id", "client_id", "office_name", "client_name"})
public class Info {
	
	@JsonProperty("office_id")
	private Long officeId;
	@JsonProperty("client_id")
	private Long clinetId;
	@JsonProperty("client_name")
	private String clientName;
	@JsonProperty("office_name")
	private String officeName;
	private LocalDate creationDate;

}
