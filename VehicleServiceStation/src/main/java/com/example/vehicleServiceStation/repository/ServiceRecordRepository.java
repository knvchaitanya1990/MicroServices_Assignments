package com.example.vehicleServiceStation.repository;


import com.example.vehicleServiceStation.model.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord,Long> {

    ServiceRecord getServiceRequestById(Long serviceRequestId);
    //List<ServiceRecord> findByVehicleID(Long vehicleId);

 /*   //@Query("select sr from SERVICE_RECORD sr JOIN VEHICLE v ON sr.vehicle_id=v.id WHERE v.registration_number = :registration_number")

  //  @Query("select sr from SERVICE_RECORD sr JOIN sr.vehicle v where v.registration_number = :registrationNumber")
    List<ServiceRecord> findByVehicleRegistrationNumber(@Param("registrationNumber") String registrationNumber);
*/

}
