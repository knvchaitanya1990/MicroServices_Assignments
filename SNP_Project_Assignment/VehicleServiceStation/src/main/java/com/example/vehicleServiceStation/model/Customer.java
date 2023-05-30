package com.example.vehicleServiceStation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerName;
    private String address;
    private String email_address;
    private Long contact_Number;

   /* @Column(name="AADHAAR_NUMBER")
    private Long aadhaar_Number;*/

}
