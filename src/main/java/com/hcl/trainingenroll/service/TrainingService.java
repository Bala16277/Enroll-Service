package com.hcl.trainingenroll.service;



import java.util.List;

import com.hcl.trainingenroll.dto.TrainingDto;
import com.hcl.trainingenroll.dto.TrainingRequestDto;
import com.hcl.trainingenroll.dto.TrainingResponseDto;


public interface TrainingService {

	public TrainingResponseDto addTraining(TrainingRequestDto trainingRequestDto);
	
	public List<TrainingDto> searchByCourseNameContains(String courseName);
	
}
