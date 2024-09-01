package com.aditya.emsapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aditya.emsapi.model.EMSUser;
import com.aditya.emsapi.model.Employee;
import com.aditya.emsapi.repository.EMSUserRepo;
import com.aditya.emsapi.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository emplRepo;

	@Autowired
	private EMSUserRepo emsUserRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Employee createEmployee(Employee empl) {
		
		EmployeeEntity emplEnt = new EmployeeEntity();
		
		BeanUtils.copyProperties(empl, emplEnt);
		emplEnt.setIsFirstTimeLogin(true);
		emplRepo.save(emplEnt);

		EMSUser emsUser = new EMSUser();
		emsUser.setEmail(empl.getEmailId());
		String pswd = RandomCodeGenerator.generateRandomCode();
		String encodedPassWord = passwordEncoder.encode(pswd);

		emsUser.setPassword(encodedPassWord);
		emsUser.setName(empl.getFirstName() + " " + empl.getLastName());
		emsUser.setRole("EMP");
		emsUserRepo.save(emsUser);

		empl.setRandomGeneratedPassword(pswd);
		
		return empl;
	}

	public List<Employee> getAllEmployees() {
		List<EmployeeEntity> emplEntity = emplRepo.findAll();
		List<Employee> empl= emplEntity.stream()
				.map(emp-> new Employee(emp.getId(),
				emp.getFirstName(),
				emp.getLastName(),
				emp.getEmailId(),null))
				.collect(Collectors.toList());
		return empl;
	}

	public Boolean deleteEmployee(Long id) {
		EmployeeEntity empl = emplRepo.findById(id).get();
		emplRepo.delete(empl);
		return true;
	}

	public Employee updateEmployeeById(Employee employee, Long id) {
		EmployeeEntity empEnt = emplRepo.findById(id).get();
		if(empEnt!=null) {
			BeanUtils.copyProperties(employee, empEnt);
			emplRepo.save(empEnt);
		}
		return employee;
	}

	public Employee getEmployeeById(Long id) {
		EmployeeEntity empEnt = emplRepo.findById(id).get();
		Employee emp = new Employee();
		BeanUtils.copyProperties(empEnt, emp);
		return emp;
	}

}
