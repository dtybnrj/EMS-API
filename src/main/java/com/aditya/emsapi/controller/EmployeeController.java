package com.aditya.emsapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.emsapi.model.Employee;
import com.aditya.emsapi.services.EmployeeServiceImpl;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl emplServ;
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee empl) {
		return emplServ.createEmployee(empl);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		return emplServ.getAllEmployees();
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Boolean delete = false;
		
		delete = emplServ.deleteEmployee(id);
		
		Map<String,Boolean>response=new HashMap<String, Boolean>();
		
		response.put("deleted", delete);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee emp = emplServ.getEmployeeById(id);
		return ResponseEntity.ok(emp);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployeeById( @RequestBody Employee employee, @PathVariable Long id){
		Employee emp = emplServ.updateEmployeeById(employee,id);
		return ResponseEntity.ok(emp);
	}
	
	public void getEmployeeAttendance() {
		
	}
}
