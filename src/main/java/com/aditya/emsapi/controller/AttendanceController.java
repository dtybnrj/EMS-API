package com.aditya.emsapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aditya.emsapi.model.Attendance;
import com.aditya.emsapi.services.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public List<Attendance> getAllAttendanceRecords() {
        return attendanceService.getAllRecords();
    }

    @PostMapping("/checkin")
    public Attendance checkIn(@RequestBody Attendance attendance) {
        return attendanceService.markAttendance(attendance);
    }

    @PostMapping("/checkout")
    public Attendance checkOut(@RequestBody Attendance attendance) {
        return attendanceService.markAttendance(attendance);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendanceRecord(@PathVariable Long id) {
        attendanceService.deleteAttendanceRecord(id);
    }
}

