package com.connectedcoops.validationmicroservice.controller;

import com.connectedcoops.validationmicroservice.application.SensorValidationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SensorMalfunctionController {
    private final SensorValidationTask sensorValidationTask;

    @Autowired
    public SensorMalfunctionController(SensorValidationTask sensorValidationTask) {
        this.sensorValidationTask = sensorValidationTask;
    }

    @GetMapping("/validation-task")
    public String executeValidationTask() {
        sensorValidationTask.executeValidationTask();
        return "Validation task executed!";
    }
}

