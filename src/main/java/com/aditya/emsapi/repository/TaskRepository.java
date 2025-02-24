package com.aditya.emsapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.aditya.emsapi.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
