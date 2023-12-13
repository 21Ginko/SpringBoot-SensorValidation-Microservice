package com.connectedcoops.validationmicroservice.model.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class LastSensorReadingRepository {
    String userUUID = "KVwb8RaoiTbxZp2lna5SD9fONZR2";
    @Autowired
    private Firestore firestore;
    public List<QueryDocumentSnapshot> querySensorReadings(List<String> activeSensors)
            throws ExecutionException, InterruptedException {
        CollectionReference readingsCollection = firestore.collection("users").document(userUUID).collection("last_sensor_readings");

        Query query = readingsCollection.whereIn(FieldPath.documentId(), activeSensors);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        return querySnapshot.get().getDocuments();
    }

}
