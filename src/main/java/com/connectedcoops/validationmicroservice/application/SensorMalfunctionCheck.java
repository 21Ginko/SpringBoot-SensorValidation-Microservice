package com.connectedcoops.validationmicroservice.application;

import com.connectedcoops.validationmicroservice.model.entity.Notification;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.checkerframework.checker.formatter.qual.ReturnsFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SensorMalfunctionCheck {
    String userUUID = "0t1hKAe4PKZtrTsPM95LSlNjTfq1";
    @Autowired
    private Firestore firestore;
    public String checkMalfunctioningSensors(DocumentSnapshot sensorReadings) {

        CollectionReference notificationsCollection = firestore.collection("users").document(userUUID).collection("notifications");

        String sensor_ID = sensorReadings.getId();
        String sensorName = sensorReadings.getString("sensor_info.name");

        if (sensorReadings.get("reading") == null && sensorReadings.get("previous_reading") == null) {
            String type = "Error";
            String message = "Falla detectada en el sensor: "+ sensorName + " con ID: "+ sensor_ID;
            notificationsCollection.add(new Notification(message, type, Timestamp.now()));
            return message;
        } else {
            String type = "Information";
            String message = "Funcionamiento correcto en el sensor: "+ sensorName + " con ID: "+ sensor_ID;
            notificationsCollection.add(new Notification(message, type, Timestamp.now()));
            return message;
        }
    }
}
