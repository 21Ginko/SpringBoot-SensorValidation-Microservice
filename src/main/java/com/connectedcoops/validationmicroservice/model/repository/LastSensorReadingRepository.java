package com.connectedcoops.validationmicroservice.model.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class LastSensorReadingRepository {
    String userUUID = "0t1hKAe4PKZtrTsPM95LSlNjTfq1";
    @Autowired
    private final Firestore firestore;

    public LastSensorReadingRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public DocumentSnapshot querySensorReadings(int sensor_ID)
            throws ExecutionException, InterruptedException {
        CollectionReference readingsCollection = firestore.collection("users").document(userUUID).collection("last_sensor_readings");

        ApiFuture<DocumentSnapshot> documentSnapshot = readingsCollection.document(String.valueOf(sensor_ID)).get();

        return documentSnapshot.get();
    }

}
