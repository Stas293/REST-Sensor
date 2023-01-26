package org.springapp.projectrest.validators;

import org.springapp.projectrest.models.SensorData;
import org.springapp.projectrest.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorDataValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorDataValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorData.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorData sensorData = (SensorData) target;

        if (sensorService.getSensorByName(sensorData.getSensor().getName()) == null) {
            errors.rejectValue("sensor", "sensor.name.not.exists");
        }
    }
}
