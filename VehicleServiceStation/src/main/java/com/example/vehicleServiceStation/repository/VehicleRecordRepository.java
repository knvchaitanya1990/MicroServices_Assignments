package com.example.vehicleServiceStation.repository;

import com.example.vehicleServiceStation.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRecordRepository extends JpaRepository<Vehicle,Long>  {

    Vehicle findByVehicleRegistrationNumber(String registrationNumber);
}
