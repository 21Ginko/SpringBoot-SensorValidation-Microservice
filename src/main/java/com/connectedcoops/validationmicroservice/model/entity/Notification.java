package com.connectedcoops.validationmicroservice.model.entity;

import com.google.cloud.Timestamp;

public class Notification {

    private int notification_ID;
    private String message;
    private String type;
    private Timestamp timestamp;

    public Notification(int notification_ID, String message, String type, Timestamp timestamp) {
        this.notification_ID = notification_ID;
        this.message = message;
        this.type = type;
        this.timestamp = timestamp;
    }
    public int getNotification_ID() {
        return notification_ID;
    }

    public void setNotification_ID(int notification_ID) {
        this.notification_ID = notification_ID;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}