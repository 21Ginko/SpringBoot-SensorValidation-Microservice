package com.connectedcoops.validationmicroservice.model.entity;

import com.google.cloud.Timestamp;

public class Notification {
    private String message;
    private String type;
    private Timestamp timestamp;

    public Notification(String message, String type, Timestamp timestamp) {
        this.message = message;
        this.type = type;
        this.timestamp = timestamp;
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