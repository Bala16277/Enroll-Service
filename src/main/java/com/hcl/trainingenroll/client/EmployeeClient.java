package com.hcl.trainingenroll.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.trainingenroll.dto.EmployeeDto;

@FeignClient(value="employee-service", url = "http://localhost:7788/empcourse/employees")
public interface EmployeeClient {
	
	@GetMapping("")
	public EmployeeDto getEmployeebyEmployeeId(@RequestParam("employeeId") Integer employeeId);

}
