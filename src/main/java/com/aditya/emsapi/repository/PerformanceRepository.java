package com.aditya.emsapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aditya.emsapi.model.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}

