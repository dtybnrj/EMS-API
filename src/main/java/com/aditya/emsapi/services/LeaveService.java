package com.aditya.emsapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.emsapi.model.Leave;
import com.aditya.emsapi.repository.LeaveRepository;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public Leave requestLeave(Leave leave) {
        return leaveRepository.save(leave);
    }

    public Leave approveLeave(Long id) {
        Leave leave = leaveRepository.findById(id).orElse(null);
        if (leave != null) {
            leave.setStatus("APPROVED");
            return leaveRepository.save(leave);
        }
        return null;
    }

    public void deleteLeave(Long id) {
        leaveRepository.deleteById(id);
    }
}

