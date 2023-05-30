package com.example.vehicleServiceStation.controller;

import com.example.vehicleServiceStation.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/delivery-service")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/service-record/{serviceRecordID}")
    public ResponseEntity<String> getDeliveryDateBasedOnServiceRecord(@PathVariable("serviceRecordID")
                                                                          Long serviceRecordID) {
        // Implement the logic to calculate the delivery date based on service requirements
        LocalDate deliveryDate = deliveryService.getDeliveryDateBasedOnServiceRecord(serviceRecordID);
        return ResponseEntity.ok("Estimated delivery date: " + deliveryDate.toString());
    }


    @GetMapping("/vehicle/{vehicle}")
    public ResponseEntity<String> getDeliveryDateBasedOnVehicleRegNum(@PathVariable("registrationNumber")
                                                                          String registrationNumber) {
        // Implement the logic to calculate the delivery date based on service requirements
        LocalDate deliveryDate = deliveryService.getDeliveryDateBasedOnVehicleRegNum(registrationNumber);
        return ResponseEntity.ok("Estimated delivery date: " + deliveryDate.toString());
    }

}
