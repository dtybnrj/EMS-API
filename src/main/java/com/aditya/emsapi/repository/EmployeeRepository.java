package com.aditya.emsapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aditya.emsapi.model.EMSUser;
import com.aditya.emsapi.services.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{

    Optional<EmployeeEntity> findByEmailId(String email);

}
