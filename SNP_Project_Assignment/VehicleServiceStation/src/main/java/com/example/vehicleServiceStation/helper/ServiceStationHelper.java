package com.example.vehicleServiceStation.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceStationHelper {

    @Value("${POLICE_VERIFICATION_SERVICE_URL}")
    private String policeVerificationEndPoint;


    @Value("${INSURANCE_VERIFICATION_SERVICE_URL}")
    String insuranceEndPoint;

    RestTemplate restTemplate = new RestTemplate();

    public boolean performPoliceVerification(String registrationNumber) {
        ResponseEntity<String> verificationResult =
                restTemplate.getForEntity(policeVerificationEndPoint + registrationNumber, String.class);
        if (verificationResult.getStatusCode().is2xxSuccessful()) {
            return false;
        }
        return true;
    }

    public boolean performInsuranceVerification(String registrationNumber) {
        // implement helper class to find the instance URL and host run time .
        ResponseEntity<String> verificationResult = restTemplate.getForEntity
                (insuranceEndPoint+registrationNumber, String.class);
        if(verificationResult.getStatusCode().is2xxSuccessful()){
            return false;
        }
        return true;
    }

}
