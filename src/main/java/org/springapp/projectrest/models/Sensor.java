package org.springapp.projectrest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @NotNull
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Sensor sensor)) return false;

        return name.equals(sensor.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
