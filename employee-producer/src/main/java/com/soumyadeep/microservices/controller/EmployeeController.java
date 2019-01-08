package com.soumyadeep.microservices.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.soumyadeep.microservices.model.Employee;

@RestController
public class EmployeeController {

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getDataFallback")
	public Employee firstPage() {

		Employee employee = new Employee();
		employee.setName("soumyadeep1");
		employee.setDesignation("Director Technology");
		employee.setEmpId("1");
		employee.setAddress("Bangalore");

		if (employee.getEmpId().equalsIgnoreCase("1")) {

			throw new RuntimeException();
		}

		return employee;
	}

	public Employee getDataFallback() {

		Employee employee = new Employee();
		employee.setName("fallback-soumyadeep1");
		employee.setDesignation("fallback-Director Technology");
		employee.setEmpId("fallback-1");
		employee.setAddress("fallback-Bangalore");

		return employee;
	}
}
