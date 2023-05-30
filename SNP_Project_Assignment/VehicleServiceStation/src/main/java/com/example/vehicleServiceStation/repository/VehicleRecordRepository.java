package com.example.vehicleServiceStation.repository;

import com.example.vehicleServiceStation.model.ServiceRecord;
import com.example.vehicleServiceStation.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRecordRepository extends JpaRepository<Vehicle,Long>  {

    Vehicle getVehicleByRegistrationNumber(String registration_number);
    boolean existsByRegistrationNumber(String registration_number);

    Vehicle findByVehicleRegistrationNumber(String registrationNumber);
}
