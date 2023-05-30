package com.example.vehicleServiceStation.repository;


import com.example.vehicleServiceStation.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRecordRepository extends JpaRepository<Customer,Long> {

   // boolean verifyCustomerByAadhaarNumber(Long aadhaarNumber);

    //Optional<Customer> findCustomerByAadhaarNumber(Long aadhaarNumber);
}
