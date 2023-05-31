package com.example.vehicleServiceStation.controller;

import com.example.vehicleServiceStation.model.VehicleRequest;
import com.example.vehicleServiceStation.service.ServiceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/serviceStation")
public class ServiceStationController {
    @Autowired
    private ServiceStationService serviceStationService;

    @PostMapping("/acceptVehicle")
    public ResponseEntity<String> acceptVehicle(@RequestBody VehicleRequest request) {
        System.out.println("Service Request for the vehicle " + request.toString());
        return serviceStationService.acceptVehicleForService(request);
    }

    @PostMapping("/completeService/{serviceRecordId}")
    public ResponseEntity<String> completeServiceForVehicle(@PathVariable Long serviceRecordId) {
        return serviceStationService.completeServiceForVehicle(serviceRecordId);
    }

    /*
    @GetMapping("/allVehiclesInQueue")
    public ResponseEntity<List<Vehicle>> getAllVehiclesInQueue() {
        List<Vehicle> vehicleList = serviceStationService.getAllVehicles();
        return ResponseEntity.ok(vehicleList);
    }
    @GetMapping("/allVehiclesDeliveredToday")
    public ResponseEntity<List<Vehicle>> getAllVehiclesDeliveredToday() {
        List<Vehicle> vehicleList = serviceStationService.getAllVehicles();
        return ResponseEntity.ok(vehicleList);
    }
*/
}