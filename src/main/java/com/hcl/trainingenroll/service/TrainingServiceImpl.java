package com.hcl.trainingenroll.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.trainingenroll.client.CourseClient;
import com.hcl.trainingenroll.dto.CourseDto;
import com.hcl.trainingenroll.dto.TrainingDto;
import com.hcl.trainingenroll.dto.TrainingRequestDto;
import com.hcl.trainingenroll.dto.TrainingResponseDto;
import com.hcl.trainingenroll.entity.Training;
import com.hcl.trainingenroll.repository.TrainingRepository;

@Service
public class TrainingServiceImpl implements TrainingService {

	private static Logger logger = Logger.getLogger(TrainingServiceImpl.class);

	@Autowired
	TrainingRepository trainingRepository;

	@Autowired
	CourseClient courseClient;
	
	
	Date now = new Date();
	String pattern = "yyyy-MM-dd";
	SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	String mysqlDateString = formatter.format(now);

	public TrainingResponseDto addTraining(TrainingRequestDto trainingRequestDto) {

		logger.info("Inside addtraining method of training service");
		TrainingResponseDto trainingResponseDto = new TrainingResponseDto();
		logger.info("TrainingRequestDto=========: ");

		CourseDto courseDto = courseClient.getCourseByCourseName(trainingRequestDto.getCourseName());
		TrainingDto trainingDto = new TrainingDto();

		try {
			logger.info("inside try");
			BeanUtils.copyProperties(trainingDto, trainingRequestDto);
			trainingDto.setCourseId(courseDto.getCourseId());
			logger.info("trainingDto course id: " + trainingDto.getCourseId());
			logger.info("training name:::: " + trainingDto.getTrainingName());
			Training training = new Training();
			BeanUtils.copyProperties(training, trainingDto);
			logger.info("training name:::: " + trainingDto.getTrainingName());
			trainingRepository.save(training);
//			BeanUtils.copyProperties(trainingResponseDto, trainingDto);

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		trainingResponseDto.setMessage("Training has been added successfully");
		trainingResponseDto.setStatusCode(HttpStatus.CREATED.value());
		return trainingResponseDto;
	}

	public List<TrainingDto> searchByCourseNameContains(String courseName) {
		List<TrainingDto> trainingDtos = new ArrayList<>();
		logger.info("Inside search course by like name in training serviceimpl method::: ");
		List<CourseDto> courseDtos = courseClient.getCourseByCourseNameContains(courseName);
		logger.info(courseDtos);
		try {
			for (CourseDto courseDto : courseDtos) {
				logger.info("inside first loop");
				logger.info("courseId::::+ " +courseDto.getCourseId());
				List<Training> trainings = trainingRepository.findByCourseId(courseDto.getCourseId());
				
				for (Training training : trainings) {
					TrainingDto trainingDto = new TrainingDto();
					logger.info("Training id: "+training.getTrainingId());
					logger.info("inside second loop");
					logger.info(":::::::::"+trainingDto.getCourseId());
					BeanUtils.copyProperties(trainingDto, training);
					logger.info(":::::::::"+trainingDto.getCourseId());
					trainingDtos.add(trainingDto);
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
		return trainingDtos;
	}
	

}
