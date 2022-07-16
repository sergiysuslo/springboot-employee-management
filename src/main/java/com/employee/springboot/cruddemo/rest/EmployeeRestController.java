package com.employee.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.employee.springboot.cruddemo.entity.Employee;
import com.employee.springboot.cruddemo.service.EmployeeService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	 @GetMapping("/employees")
	 public List<Employee> listAll() {
		 return employeeService.findAll();
	 }
	 
	 @GetMapping("/employees/{employeeId}")
	 public Employee getEmployee(@PathVariable int employeeId) {
		 Employee theEmployee = employeeService.findById(employeeId);
		 
		 if(theEmployee == null) {
			 throw new RuntimeException("Employee id not found - " + employeeId);
		 }
		 return theEmployee;
	 }	
	 
	 @PostMapping("/employees")
	 public Employee addEmployee(@RequestBody Employee theEmployee) {
		 
		 theEmployee.setId(0);
		 
		 employeeService.save(theEmployee);
		 
		 return theEmployee;
	 }
	 
	 @PutMapping("/employees")
	 public Employee updateEmployee(@RequestBody Employee theEmployee) {
		 employeeService.save(theEmployee);
		 return theEmployee;
	 }
	 
	 @DeleteMapping("/employees/{employeeId}")
	 public String deleteEmployee(@PathVariable int employeeId) {
		 
		 Employee tempEmployee = employeeService.findById(employeeId);
		 if(tempEmployee==null) {
			 throw new RuntimeException("Employee id not found - " + employeeId);
		 }
		 
		 employeeService.deleteById(employeeId);
		 
		 return "Deleted employee - " + tempEmployee.toString();
	 }
	 
	 
	 
	 
	 
	 
}
