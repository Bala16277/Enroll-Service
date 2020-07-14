package com.hcl.trainingenroll.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.trainingenroll.dto.EnrollmentDto1;
import com.hcl.trainingenroll.dto.EnrollmentResponseDto;
import com.hcl.trainingenroll.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

	private static Logger logger = Logger.getLogger(EnrollmentController.class);

	@Autowired
	EnrollmentService enrollmentService;

	@PostMapping("/")
	public ResponseEntity<EnrollmentResponseDto> addEnrollment(@RequestParam Integer trainingId,
			@RequestParam Integer employeeId) {
		logger.info("Inside add enrollment method of enrollment controller::::::::::::::::::: ");
		EnrollmentResponseDto enrollmentResponseDto = enrollmentService.addEnrollment(trainingId, employeeId);
		return new ResponseEntity<>(enrollmentResponseDto, HttpStatus.CREATED);
	}

	@GetMapping("/searchEnrollmentDetails")
	public ResponseEntity<List<EnrollmentDto1>> getEnrollmentDetailsByCourseName(@RequestParam String courseName) {
		List<EnrollmentDto1> enrollmentDtos = enrollmentService.getEnrollmentDetailsByCourseName(courseName);
		return new ResponseEntity<>(enrollmentDtos, HttpStatus.OK);
	}
}
