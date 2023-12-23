package com.connectedcoops.validationmicroservice.model.repository;

import com.google.api.client.util.Lists;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Component
public class SensorRepository {
    String userUUID = "0t1hKAe4PKZtrTsPM95LSlNjTfq1";
    @Autowired
    private Firestore firestore;
    public List<String> queryActiveSensors() throws ExecutionException, InterruptedException {
        CollectionReference sensorsCollection = firestore.collection("users").document(userUUID).collection("sensors");

        Query query = sensorsCollection.whereEqualTo("status", true);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<String> activeSensors = Lists.newArrayList();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            activeSensors.add(Objects.requireNonNull(document.getLong("sensor_ID")).toString());
        }

        return activeSensors;
    }
}
