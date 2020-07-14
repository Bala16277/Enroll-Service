package com.hcl.trainingenroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.hcl.trainingenroll.entity.Training;

public interface TrainingRepository extends JpaRepository<Training, Integer> {
	
	
	public List<Training> findByCourseId(Integer courseId);
	

	public Training findByTrainingId(Integer trainingId);
	
}
