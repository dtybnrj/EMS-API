package com.aditya.emsapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aditya.emsapi.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
}

