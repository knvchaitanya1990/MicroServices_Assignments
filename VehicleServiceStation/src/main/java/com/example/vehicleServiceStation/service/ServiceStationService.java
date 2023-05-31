package com.example.vehicleServiceStation.service;

import com.example.vehicleServiceStation.exceptions.DuplicateVehicleException;
import com.example.vehicleServiceStation.exceptions.ServiceStationException;
import com.example.vehicleServiceStation.helper.ServiceCostCalculation;
import com.example.vehicleServiceStation.helper.ServiceStationHelper;
import com.example.vehicleServiceStation.model.Customer;
import com.example.vehicleServiceStation.model.ServiceRecord;
import com.example.vehicleServiceStation.model.Vehicle;
import com.example.vehicleServiceStation.model.VehicleRequest;
import com.example.vehicleServiceStation.repository.CustomerRecordRepository;
import com.example.vehicleServiceStation.repository.ServiceRecordRepository;
import com.example.vehicleServiceStation.repository.VehicleRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceStationService {

    @Autowired
    private ServiceRecordRepository serviceRecordRepository;
    @Autowired
    private VehicleRecordRepository vehicleRecordRepository;
    @Autowired
    private CustomerRecordRepository customerRecordRepository;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ServiceCostCalculation costCalculationService;

    @Autowired
    private ServiceStationHelper serviceStationHelper;

    public ResponseEntity<String> acceptVehicleForService(VehicleRequest request) {

        try {
            Vehicle vehicle = request.getVehicle();
            Customer customer = request.getCustomer();
            if (request.getVehicle().getVehicleType().equals("BIKE") ||
                    request.getVehicle().getVehicleType().equals("CAR")) {
                boolean vehicleInvolvedInTheft = serviceStationHelper.performPoliceVerification(vehicle.getRegistrationNumber());
                if (vehicleInvolvedInTheft) {
                    //Long aadhaarNumber = customer.getAadhaar_Number();
                    // Create a service record
                    ServiceRecord serviceRecord = new ServiceRecord();
                    serviceRecord.setVehicle(vehicle);
                    double estimateCost = costCalculationService.estimateCost(serviceRecord);
                    serviceRecord.setEstimatedCost(estimateCost);
                    serviceRecord.setAccepted(true);
                    serviceRecord.setService_acceptedDate(LocalDate.now());
                    serviceRecord.setEstimateDeliveryDay(LocalDate.now().plusDays(3));
                    // Check weather vehicle details are already present  in the database
            /*  Optional<Customer> isCustomerPresent = customerRecordRepository.findCustomerByAadhaarNumber(aadhaarNumber);
              if(!isCustomerPresent.isPresent())*/
                    customerRecordRepository.save(customer);
                    vehicle.setCustomer(customer);
                    Vehicle vehicleFound = vehicleRecordRepository.findByVehicleRegistrationNumber(vehicle.getRegistrationNumber());
                    if(vehicleFound==null)
                        vehicleRecordRepository.save(vehicle);
                        // throw new DuplicateVehicleException("Vehicle with the same registration number already  exists");
                    serviceRecord.setVehicle(vehicle);
                    // Save the service record
                    serviceRecordRepository.save(serviceRecord);
                    // Send notification to the customer
                    String message = "Your vehicle has been accepted for service and estimated delivery on  : " + serviceRecord.getEstimateDeliveryDay();
                    notificationService.sendNotificationToCustomer(customer, message);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle failed police verification for theft." +
                            "So we can't accept this vehicle for service");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle Type not supported at our service station" +
                        "So we can't accept this vehicle for service");
            }
        } catch (Exception e) {
            throw new ServiceStationException("Error Occurred while processing service request");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to accept vehicle for service.");
    }


    public ResponseEntity<String> completeServiceForVehicle(Long serviceRequestId) {
        try {
            ServiceRecord serviceRecord = serviceRecordRepository.getServiceRequestById(serviceRequestId);
            if (serviceRecord != null) {
                serviceRecord.setInsured(serviceStationHelper.
                        performInsuranceVerification(serviceRecord.getVehicle().getRegistrationNumber()));
                double totalServiceCost = costCalculationService.serviceCostCalculation(serviceRecord);
                serviceRecord.setServiceCompleted(true);
                serviceRecordRepository.save(serviceRecord);
                // Send notification to the customer
                Vehicle vehicle = serviceRecord.getVehicle();
                Customer customer = vehicle.getCustomer();
                // Perform the service completion logic and Update the service completion flag
                String message = " Your vehicle service is completed with total Service Cost is " + totalServiceCost;
                notificationService.sendNotificationToCustomer(customer, message);
            }
            return ResponseEntity.ok("Vehicle Service Completion Updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Failed to complete service for vehicle due to internal service error");
        }
    }
}




