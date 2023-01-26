package org.springapp.projectrest.exceptions;

public class SensorDataAddException extends Exception {
    private final String message;
    private final long timestamp;

    public SensorDataAddException() {
        this.message = "Sensor data not added";
        this.timestamp = System.currentTimeMillis();
    }

    public SensorDataAddException(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public SensorDataAddException(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "SensorDataAddException{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
