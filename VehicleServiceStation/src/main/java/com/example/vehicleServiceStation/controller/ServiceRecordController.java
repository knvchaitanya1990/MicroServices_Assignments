package com.example.vehicleServiceStation.controller;

import com.example.vehicleServiceStation.model.ServiceRecord;
import com.example.vehicleServiceStation.service.ServiceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/serviceStation/")
public class ServiceRecordController {

    @Autowired
    private ServiceRecordService serviceRecordService;

    @GetMapping("/service-records/vehicle/{registrationNumber}")
    public ResponseEntity<List<ServiceRecord>>
    getServiceHistoryByVehicleRegistrationNumber(@PathVariable("registrationNumber")
                                                 String registrationNumber) {
      return serviceRecordService.findByVehicleRegistrationNumber(registrationNumber);

    }

    @GetMapping("/service-record/status/{serviceRecordId}")
    public Optional<ServiceRecord>
    getServiceRecordDetails(@PathVariable("serviceRecordId") Long serviceRecordId) {
        return serviceRecordService.findByServiceRecordId(serviceRecordId);

    }

}
