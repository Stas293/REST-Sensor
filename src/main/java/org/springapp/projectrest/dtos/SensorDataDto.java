package org.springapp.projectrest.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springapp.projectrest.models.SensorData;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link SensorData} entity
 */
public class SensorDataDto implements Serializable {
    @NotNull
    private SensorDto sensor;
    @NotNull
    @Min(value = -100, message = "Value must be between -100 and 100")
    @Max(value = 100, message = "Value must be between -100 and 100")
    private double value;
    @NotNull
    private boolean raining;

    public SensorDataDto() {
    }

    public SensorDataDto(SensorDto sensor, double value, boolean raining) {
        this.sensor = sensor;
        this.value = value;
        this.raining = raining;
    }

    public SensorDto getSensor() {
        return sensor;
    }

    public void setSensor(SensorDto sensor) {
        this.sensor = sensor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean getRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorDataDto entity = (SensorDataDto) o;
        return Objects.equals(this.sensor, entity.sensor) &&
                Objects.equals(this.value, entity.value) &&
                Objects.equals(this.raining, entity.raining);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensor, value, raining);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "sensor = " + sensor + ", " +
                "value = " + value + ", " +
                "raining = " + raining + ")";
    }
}