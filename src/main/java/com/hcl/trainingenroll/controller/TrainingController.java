package com.hcl.trainingenroll.controller;



import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.trainingenroll.dto.TrainingDto;
import com.hcl.trainingenroll.dto.TrainingRequestDto;
import com.hcl.trainingenroll.dto.TrainingResponseDto;
import com.hcl.trainingenroll.service.TrainingService;


@RestController
@RequestMapping("/trainings")
public class TrainingController {
	
	private static Logger logger = Logger.getLogger(TrainingController.class);
	
	@Autowired
	TrainingService trainingService;
	
	@PostMapping("/")
	public ResponseEntity<TrainingResponseDto> addTraining(@RequestBody TrainingRequestDto trainingRequestDto) {
		logger.info("Inside add training method of training controller::::::::::::::::::: ");
		TrainingResponseDto trainingResponseDto = trainingService.addTraining(trainingRequestDto);
		return new ResponseEntity<>(trainingResponseDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<TrainingDto>> searchByCourseName(@RequestParam String courseName) {
		logger.info("Inside search by course name method of training controller::::::::::::::::::: ");
		List<TrainingDto> trainingDtos = trainingService.searchByCourseNameContains(courseName);
		return new ResponseEntity<>(trainingDtos,HttpStatus.OK);
	}
}
