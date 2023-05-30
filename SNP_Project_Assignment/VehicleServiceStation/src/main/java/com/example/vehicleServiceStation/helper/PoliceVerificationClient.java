package com.example.vehicleServiceStation.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class PoliceVerificationClient {

    RestTemplate restTemplate = new RestTemplate();

    @Value("POLICE_VERIFICATION_SERVICE_URL")
    String policeVerificationEndPoint;

    @Autowired
    private ServiceInstanceInfo serviceInstanceInfo;

    public boolean performPoliceVerification(String registrationNumber) {

        ResponseEntity<String> verificationResult = restTemplate.
                getForEntity(policeVerificationEndPoint+registrationNumber, String.class);

        if(verificationResult.getStatusCode().is2xxSuccessful()){
            return true;
        }
        return false;
    }
}
