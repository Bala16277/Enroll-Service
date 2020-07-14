package com.hcl.trainingenroll.dto;

public class EnrollmentResponseDto {
	
	private String trainingName;
	
	private String employeeName;
	
	private String messasge;
	
	private Integer statusCode;
	

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getMessasge() {
		return messasge;
	}

	public void setMessasge(String messasge) {
		this.messasge = messasge;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	

}
