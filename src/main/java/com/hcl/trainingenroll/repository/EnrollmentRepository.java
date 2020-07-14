package com.hcl.trainingenroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.trainingenroll.entity.Enrollment;
import com.hcl.trainingenroll.entity.Training;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
	
	public List<Enrollment> findByTraining(Training training);
	

}
