package com.connectedcoops.validationmicroservice.model.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class LastSensorReadingRepository {
    String userUUID = "0t1hKAe4PKZtrTsPM95LSlNjTfq1";
    @Autowired
    private Firestore firestore;
    public DocumentSnapshot querySensorReadings(int sensor_ID)
            throws ExecutionException, InterruptedException {
        CollectionReference readingsCollection = firestore.collection("users").document(userUUID).collection("last_sensor_readings");

        ApiFuture<DocumentSnapshot> documentSnapshot = readingsCollection.document(String.valueOf(sensor_ID)).get();

        return documentSnapshot.get();
    }

}
