package com.aditya.emsapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aditya.emsapi.model.Performance;
import com.aditya.emsapi.services.PerformanceService;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @GetMapping
    public List<Performance> getAllReviews() {
        return performanceService.getAllReviews();
    }

    @PostMapping("/review")
    public Performance submitReview(@RequestBody Performance performance) {
        return performanceService.addReview(performance);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        performanceService.deleteReview(id);
    }
}

