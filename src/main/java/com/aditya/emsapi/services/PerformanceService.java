package com.aditya.emsapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.emsapi.model.Performance;
import com.aditya.emsapi.repository.PerformanceRepository;

import java.util.List;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public List<Performance> getAllReviews() {
        return performanceRepository.findAll();
    }

    public Performance addReview(Performance performance) {
        return performanceRepository.save(performance);
    }

    public void deleteReview(Long id) {
        performanceRepository.deleteById(id);
    }
}

