package com.example.vehicleServiceStation.service;

import com.example.vehicleServiceStation.model.ServiceRecord;
import com.example.vehicleServiceStation.model.Vehicle;
import com.example.vehicleServiceStation.repository.ServiceRecordRepository;
import com.example.vehicleServiceStation.repository.VehicleRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRecordService {

    @Autowired
    private ServiceRecordRepository serviceRecordRepository;
    @Autowired
    private VehicleRecordRepository vehicleRecordRepository;

    public ResponseEntity<List<ServiceRecord>> findByVehicleRegistrationNumber(String registrationNumber) {

        Vehicle vehicle = vehicleRecordRepository.findByVehicleRegistrationNumber(registrationNumber);
        if(vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        /*List<ServiceRecord> serviceRecords = serviceRecordRepository.findByVehicleID(vehicle.getId());
        return ResponseEntity.ok(serviceRecords);*/

        return ResponseEntity.notFound().build();

    }
    public Optional<ServiceRecord> findByServiceRecordId(Long serviceRecordId) {
        return serviceRecordRepository.findById(serviceRecordId);
    }
}
