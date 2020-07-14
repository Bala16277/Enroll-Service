package com.hcl.trainingenroll.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.trainingenroll.dto.CourseDto;

@FeignClient(value="course-service", url = "http://localhost:7788/empcourse/courses")
public interface CourseClient {
	
	@GetMapping("")
	public CourseDto getCourseByCourseName(@RequestParam("courseName") String courseName);
	
	@GetMapping("/search")
	public List<CourseDto> getCourseByCourseNameContains(@RequestParam("courseName") String courseName);

}
