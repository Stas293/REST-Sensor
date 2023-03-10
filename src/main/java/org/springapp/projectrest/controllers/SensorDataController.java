package org.springapp.projectrest.controllers;

import jakarta.validation.Valid;
import org.springapp.projectrest.dtos.SensorDataDto;
import org.springapp.projectrest.exceptions.SensorDataAddException;
import org.springapp.projectrest.models.SensorData;
import org.springapp.projectrest.services.SensorDataService;
import org.springapp.projectrest.utils.ExceptionParser;
import org.springapp.projectrest.utils.Mapper;
import org.springapp.projectrest.utils.SensorDataErrorResponse;
import org.springapp.projectrest.validators.SensorDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class SensorDataController {
    private final SensorDataService sensorDataService;
    private final Mapper mapper;
    private final SensorDataValidator sensorDataValidator;

    @Autowired
    public SensorDataController(SensorDataService sensorDataService, Mapper mapper, SensorDataValidator sensorDataValidator) {
        this.sensorDataService = sensorDataService;
        this.mapper = mapper;
        this.sensorDataValidator = sensorDataValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid SensorDataDto sensorDataDto, BindingResult bindingResult) throws SensorDataAddException {
        SensorData sensorData = mapper.convertTo(sensorDataDto, SensorData.class);
        sensorDataValidator.validate(sensorData, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new SensorDataAddException(
                    ExceptionParser.parseBindingResult(bindingResult)
                            .orElse("Sensor data not added"),
                    System.currentTimeMillis());
        }

        sensorDataService.registerSensorData(sensorData);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<SensorDataDto>> getMeasurements() {
        return ResponseEntity.ok().body(
                sensorDataService.getSensorData().stream()
                        .map(sensorData -> mapper.convertTo(sensorData, SensorDataDto.class))
                        .toList()
        );
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<String> getRainyDaysCount() {
        return ResponseEntity.ok().body(
                "Rainy days count: " + sensorDataService.getRainyDaysCount()
        );
    }

    @ExceptionHandler(SensorDataAddException.class)
    public ResponseEntity<SensorDataErrorResponse> handleSensorDataAddError(SensorDataAddException e) {
        SensorDataErrorResponse errorResponse = new SensorDataErrorResponse(e.getMessage(), e.getTimestamp());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
