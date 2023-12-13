package com.connectedcoops.validationmicroservice.application;

import com.connectedcoops.validationmicroservice.model.entity.Notification;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SensorMalfunctionCheck {
    String userUUID = "KVwb8RaoiTbxZp2lna5SD9fONZR2";
    @Autowired
    private Firestore firestore;
    public void checkMalfunctioningSensors(List<QueryDocumentSnapshot> sensorReadings) {

        CollectionReference notificationsCollection = firestore.collection("users").document(userUUID).collection("notifications");

        for (DocumentSnapshot document : sensorReadings) {
            String sensor_ID = document.getId();
            String sensorName = document.getString("sensor_info.name");
            if (document.get("reading") == null && document.get("previous_reading") == null) {
                String type = "Error";
                String message = "Falla detectada en el sensor: "+ sensorName + " con ID: "+ sensor_ID;
                notificationsCollection.add(new Notification(message, type, Timestamp.now()));
            } else {
                String type = "Information";
                String message = "Funcionamiento correcto en el sensor: "+ sensorName + " con ID: "+ sensor_ID;
                notificationsCollection.add(new Notification(message, type, Timestamp.now()));
            }

        }
    }
}
