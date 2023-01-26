package org.springapp.projectrest.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springapp.projectrest.models.Sensor;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Sensor} entity
 */
public class SensorDto implements Serializable {

    @NotNull
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;

    public SensorDto() {
    }

    public SensorDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorDto entity = (SensorDto) o;
        return Objects.equals(this.name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ")";
    }
}