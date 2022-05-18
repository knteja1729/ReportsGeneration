package in.aitacs.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchResponse {
	
	private Integer eligId;
	private String planName;
	private String planStatus;
	private String email;
	private LocalDate startDate;
	private LocalDate enddate;
	private Long caseNum;
	private Double benfitAmt;
	private String denialreason;
	private String holderName;
	private Long holderSsn;

}
