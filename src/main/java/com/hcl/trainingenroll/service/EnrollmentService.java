package com.hcl.trainingenroll.service;

import java.util.List;

import com.hcl.trainingenroll.dto.EnrollmentDto1;
import com.hcl.trainingenroll.dto.EnrollmentResponseDto;

public interface EnrollmentService {
	
	
	public EnrollmentResponseDto addEnrollment(Integer trainingId, Integer employeeId);
	
	public List<EnrollmentDto1> getEnrollmentDetailsByCourseName(String courseName);

}
