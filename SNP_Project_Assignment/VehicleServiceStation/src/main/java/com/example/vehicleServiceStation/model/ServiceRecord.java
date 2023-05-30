package com.example.vehicleServiceStation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="SERVICE_RECORD")
public class ServiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean accepted;

    //@ManyToOne(cascade = CascadeType.PERSIST)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private double estimatedCost;

    private LocalDate estimateDeliveryDay;
    private LocalDate service_acceptedDate;
    private double totalCost;
    private boolean serviceCompleted;

    private boolean isInsured;

}
