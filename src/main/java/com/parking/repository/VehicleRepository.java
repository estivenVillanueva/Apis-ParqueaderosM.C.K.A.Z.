package com.parking.repository;

import com.parking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByLicensePlate(String licensePlate);
    Optional<Vehicle> findByLicensePlateAndParkedTrue(String licensePlate);
    List<Vehicle> findByParkedTrue();
} 