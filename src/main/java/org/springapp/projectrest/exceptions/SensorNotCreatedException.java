package org.springapp.projectrest.exceptions;

public class SensorNotCreatedException extends Exception {
    private final String message;
    private final long timestamp;

    public SensorNotCreatedException() {
        this.message = "Sensor not created";
        this.timestamp = System.currentTimeMillis();
    }

    public SensorNotCreatedException(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public SensorNotCreatedException(String message, long timestamp) {
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
        return "SensorNotCreatedException{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
