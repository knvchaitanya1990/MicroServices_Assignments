package com.epam.snp.policeservice.controller;

import com.epam.snp.policeservice.model.PoliceVerification;
import com.epam.snp.policeservice.service.PoliceVerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/verify-theft")
@Tag(description = "Validates the Vehicle against the Theft Database",
        name = "Vehicle Theft Resource")
public class PoliceVerificationController {

    @Autowired
    private PoliceVerificationService verificationService;

    @Operation(summary = "Get Status of Vehicle involved in theft or not",
            description = "Provides true if vehicle not involved in Theft and false if it involves in Theft.")
    @GetMapping("/vehicle/{registrationNumber}")
    public ResponseEntity<String> verifyTheft(@PathVariable("registrationNumber") String registrationNumber) {
        if (verificationService.vehicleInvolvedInTheft(registrationNumber)) {
            return ResponseEntity.status(HttpStatus.OK).body("Vehicle is involved in theft.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle is not involved in theft.");
        }
    }

    @Operation(summary = "List of all Vehicles involved in theft",
            description = "Provides the List of all Vehicles involved in theft")
    @GetMapping("/allVehicles")
    public List<PoliceVerification> getAllVehicles(){
        return  verificationService.findAllVehicles();
    }

}