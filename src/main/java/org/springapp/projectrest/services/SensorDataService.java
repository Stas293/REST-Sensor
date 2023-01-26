package org.springapp.projectrest.services;

import org.springapp.projectrest.exceptions.SensorDataAddException;
import org.springapp.projectrest.models.Sensor;
import org.springapp.projectrest.models.SensorData;
import org.springapp.projectrest.repostitories.SensorDataRepository;
import org.springapp.projectrest.repostitories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class SensorDataService {
    private final SensorDataRepository sensorDataRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorDataService(SensorDataRepository sensorDataRepository, SensorRepository sensorRepository) {
        this.sensorDataRepository = sensorDataRepository;
        this.sensorRepository = sensorRepository;
    }

    public void registerSensorData(SensorData sensorData) throws SensorDataAddException {
        sensorData.setTimestamp(LocalDateTime.now());
        Sensor sensor = sensorRepository.findByName(sensorData.getSensor().getName()).orElseThrow(() -> new SensorDataAddException("Sensor with name " + sensorData.getSensor().getName() + " does not exist"));
        sensorData.setSensor(sensor);
        Set<SensorData> sensorDataList = sensor.getSensorData();
        sensorDataList.add(sensorData);
        sensorDataRepository.save(sensorData);
    }

    public List<SensorData> getSensorData() {
        return sensorDataRepository.findAll();
    }

    public int getRainyDaysCount() {
        return sensorDataRepository.countByRaining(true);
    }
}
