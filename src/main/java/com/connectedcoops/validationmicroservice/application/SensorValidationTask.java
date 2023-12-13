package com.connectedcoops.validationmicroservice.application;

import com.connectedcoops.validationmicroservice.model.repository.LastSensorReadingRepository;
import com.connectedcoops.validationmicroservice.model.repository.SensorRepository;
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

    public void executeValidationTask() {
        try {
            List<String> activeSensors = sensorRepository.queryActiveSensors();

            List<QueryDocumentSnapshot> sensorReadings = lastSensorReadingRepository.querySensorReadings(activeSensors);

            sensorMalfunctionCheck.checkMalfunctioningSensors(sensorReadings);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
