package com.aditya.emsapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aditya.emsapi.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}

