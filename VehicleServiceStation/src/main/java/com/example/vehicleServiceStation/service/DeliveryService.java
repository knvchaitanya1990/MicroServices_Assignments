package com.example.vehicleServiceStation.service;

import com.example.vehicleServiceStation.model.ServiceRecord;
import com.example.vehicleServiceStation.repository.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private ServiceStationService serviceStationService;

    @Autowired
    private ServiceRecordRepository serviceRecordRepository;

    public LocalDate getDeliveryDateBasedOnServiceRecord(Long serviceRecordID) {
       Optional<ServiceRecord> serviceRecord = serviceRecordRepository.findById(serviceRecordID);
       if(serviceRecord.isPresent()){
           return serviceRecord.get().getEstimateDeliveryDay();
           //return  serviceRecord.getEstimateDeliveryDay();
       }
       else return LocalDate.now();
    }


    public LocalDate getDeliveryDateBasedOnVehicleRegNum(String registrationNumber) {

        return LocalDate.now();

    }
}
