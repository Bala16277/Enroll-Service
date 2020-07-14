package com.hcl.trainingenroll.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.trainingenroll.client.CourseClient;
import com.hcl.trainingenroll.client.EmployeeClient;
import com.hcl.trainingenroll.dto.CourseDto;
import com.hcl.trainingenroll.dto.EmployeeDto;
import com.hcl.trainingenroll.dto.EnrollmentDto;
import com.hcl.trainingenroll.dto.EnrollmentDto1;
import com.hcl.trainingenroll.dto.EnrollmentResponseDto;
import com.hcl.trainingenroll.entity.Enrollment;
import com.hcl.trainingenroll.entity.Training;
import com.hcl.trainingenroll.repository.EnrollmentRepository;
import com.hcl.trainingenroll.repository.TrainingRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	private static Logger logger = Logger.getLogger(EnrollmentServiceImpl.class);

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	TrainingRepository trainingRepository;

	@Autowired
	EmployeeClient employeeClient;

	@Autowired
	CourseClient courseClient;

	public EnrollmentResponseDto addEnrollment(Integer trainingId, Integer employeeId) {

		logger.info("inside enrollment method::: ");
		EnrollmentResponseDto enrollmentResponseDto = new EnrollmentResponseDto();
		EmployeeDto employeeDto = employeeClient.getEmployeebyEmployeeId(employeeId);
		logger.info("employee id:  " + employeeDto.getEmployeeName());
		Enrollment enrollment = new Enrollment();
		Training training = trainingRepository.findByTrainingId(trainingId);
		try {
			EnrollmentDto enrollmentDto = new EnrollmentDto();
			enrollmentDto.setTrainingId(trainingId);
			enrollmentDto.setEmployeeId(employeeId);
			BeanUtils.copyProperties(enrollment, enrollmentDto);
			enrollment.setTraining(training);
			enrollmentRepository.save(enrollment);
			enrollmentResponseDto.setTrainingName(training.getTrainingName());
			enrollmentResponseDto.setEmployeeName(employeeDto.getEmployeeName());
			enrollmentResponseDto.setMessasge("Successfully enrolled");
			enrollmentResponseDto.setStatusCode(HttpStatus.CREATED.value());

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return enrollmentResponseDto;
	}

	public List<EnrollmentDto1> getEnrollmentDetailsByCourseName(String courseName) {
		logger.info("inside enrollment details::::: ");
		List<EnrollmentDto1> enrollmentDtos = new ArrayList<>();

		CourseDto courseDto = courseClient.getCourseByCourseName(courseName);
		logger.info("courseDto:::::::::::: " + courseDto.getCourseId());

		List<Training> trainings = trainingRepository.findByCourseId(courseDto.getCourseId());
		logger.info("training info::::: " + trainings);

		for (Training training : trainings) {

			logger.info("inside for loop: ");
			logger.info("training id::::::::::::::::::: " + training.getTrainingId());

			List<Enrollment> enrollments = enrollmentRepository.findByTraining(training);
			for (Enrollment enrollment : enrollments) {
				EnrollmentDto1 enrollmentDto = new EnrollmentDto1();
				logger.info("Emp id:::: " + enrollment.getEmployeeId());
				logger.info("course name: " + courseDto.getCourseName());
				enrollmentDto.setCourseName(courseDto.getCourseName());
				enrollmentDto.setEmployeeName(
						employeeClient.getEmployeebyEmployeeId(enrollment.getEmployeeId()).getEmployeeName());
				logger.info("trainingName " + training.getTrainingName());
				enrollmentDto.setTrainingName(training.getTrainingName());
				enrollmentDtos.add(enrollmentDto);
			}

		}

		return enrollmentDtos;
	}

}
