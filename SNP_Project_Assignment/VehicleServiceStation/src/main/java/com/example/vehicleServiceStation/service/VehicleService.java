package com.example.vehicleServiceStation.service;

import com.example.vehicleServiceStation.model.Vehicle;
import com.example.vehicleServiceStation.repository.VehicleRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRecordRepository vehicleRecordRepository;

    public Vehicle getVehicleByRegistrationNumber(String registrationNumber){

        return vehicleRecordRepository.getVehicleByRegistrationNumber(registrationNumber);
    }
}
