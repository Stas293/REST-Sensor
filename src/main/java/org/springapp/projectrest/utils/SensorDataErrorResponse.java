package org.springapp.projectrest.utils;

public class SensorDataErrorResponse {
    private String message;
    private long timestamp;

    public SensorDataErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SensorDataErrorResponse{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
