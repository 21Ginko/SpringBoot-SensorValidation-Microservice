package com.connectedcoops.validationmicroservice.application;

import com.connectedcoops.validationmicroservice.model.repository.LastSensorReadingRepository;
import com.connectedcoops.validationmicroservice.model.repository.SensorRepository;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
@Service
public class SensorValidationTask {
    private final SensorRepository sensorRepository;
    private final LastSensorReadingRepository lastSensorReadingRepository;
    private final SensorMalfunctionCheck sensorMalfunctionCheck;
    @Autowired
    public SensorValidationTask(SensorRepository sensorRepository, LastSensorReadingRepository lastSensorReadingRepository, SensorMalfunctionCheck sensorMalfunctionCheck) {
        this.sensorRepository = sensorRepository;
        this.lastSensorReadingRepository = lastSensorReadingRepository;
        this.sensorMalfunctionCheck = sensorMalfunctionCheck;
    }

    public String executeValidationTask(int sensor_ID) {
        try {
            List<String> activeSensors = sensorRepository.queryActiveSensors();

            if (activeSensors.contains(String.valueOf(sensor_ID))) {
                DocumentSnapshot sensorReadings = lastSensorReadingRepository.querySensorReadings(sensor_ID);
                return sensorMalfunctionCheck.checkMalfunctioningSensors(sensorReadings);
            } else {
                return "El sensor consultado con ID " + sensor_ID + " se encuentra en estado INACTIVO.";
            }


        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
