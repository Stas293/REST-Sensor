package org.springapp.projectrest.services;

import org.springapp.projectrest.models.Sensor;
import org.springapp.projectrest.repostitories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void registerSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Sensor getSensorByName(String name) {
        return sensorRepository.findByName(name).orElse(null);
    }
}
