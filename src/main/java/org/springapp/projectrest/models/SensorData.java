package org.springapp.projectrest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_data")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    @NotNull
    @Min(value = -100, message = "Value must be between -100 and 100")
    @Max(value = 100, message = "Value must be between -100 and 100")
    @Column(name = "value", nullable = false)
    private Double value;

    @NotNull
    @Column(name = "raining", nullable = false)
    private boolean raining;

    @NotNull
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorData that)) return false;

        if (Double.compare(that.value, value) != 0) return false;
        if (raining != that.raining) return false;
        if (!sensor.equals(that.sensor)) return false;
        return timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = sensor.hashCode();
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (raining ? 1 : 0);
        result = 31 * result + timestamp.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SensorDatum{" +
                "id=" + id +
                ", sensor=" + sensor +
                ", value=" + value +
                ", raining=" + raining +
                ", timestamp=" + timestamp +
                '}';
    }
}