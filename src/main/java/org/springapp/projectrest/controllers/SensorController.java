package org.springapp.projectrest.controllers;

import jakarta.validation.Valid;
import org.springapp.projectrest.dtos.SensorDto;
import org.springapp.projectrest.exceptions.SensorNotCreatedException;
import org.springapp.projectrest.models.Sensor;
import org.springapp.projectrest.services.SensorService;
import org.springapp.projectrest.utils.Mapper;
import org.springapp.projectrest.utils.SensorErrorResponse;
import org.springapp.projectrest.validators.SensorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final Mapper mapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, Mapper mapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.mapper = mapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public void registerSensor(@RequestBody @Valid SensorDto sensorDto, BindingResult bindingResult) throws SensorNotCreatedException {
        Sensor sensor = mapper.convertTo(sensorDto, Sensor.class);
        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new SensorNotCreatedException("Sensor not created", System.currentTimeMillis());
        }

        sensorService.registerSensor(sensor);
    }

    @ExceptionHandler(SensorNotCreatedException.class)
    public ResponseEntity<SensorErrorResponse> handleSensorNotCreatedError(SensorNotCreatedException e) {
        SensorErrorResponse errorResponse = new SensorErrorResponse(e.getMessage(), e.getTimestamp());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
