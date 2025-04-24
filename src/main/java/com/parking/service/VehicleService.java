package com.parking.service;

import com.parking.model.Vehicle;
import com.parking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle registerEntry(String licensePlate) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(licensePlate);
        vehicle.setEntryTime(LocalDateTime.now());
        vehicle.setParked(true);
        return vehicleRepository.save(vehicle);
    }

    public Vehicle registerExit(String licensePlate) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findByLicensePlateAndParkedTrue(licensePlate);
        if (vehicleOpt.isPresent()) {
            Vehicle vehicle = vehicleOpt.get();
            vehicle.setExitTime(LocalDateTime.now());
            vehicle.setParked(false);
            return vehicleRepository.save(vehicle);
        }
        return null;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleByLicensePlate(String licensePlate) {
        return vehicleRepository.findByLicensePlate(licensePlate).orElse(null);
    }

    public List<Vehicle> getCurrentlyParkedVehicles() {
        return vehicleRepository.findByParkedTrue();
    }
} 