package com.aditya.emsapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.emsapi.model.Attendance;
import com.aditya.emsapi.repository.AttendanceRepository;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<Attendance> getAllRecords() {
        return attendanceRepository.findAll();
    }

    public Attendance markAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public void deleteAttendanceRecord(Long id) {
        attendanceRepository.deleteById(id);
    }
}

