package com.parking.controller;

import com.parking.model.Vehicle;
import com.parking.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
@Tag(name = "Vehicle Management", description = "APIs for managing vehicle registration in parking system")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/entry")
    @Operation(summary = "Register vehicle entry", description = "Registers a new vehicle entry into the parking system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehicle entry registered successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Vehicle> registerEntry(
        @Parameter(description = "License plate of the vehicle", required = true)
        @RequestParam String licensePlate) {
        return ResponseEntity.ok(vehicleService.registerEntry(licensePlate));
    }

    @PostMapping("/exit")
    @Operation(summary = "Register vehicle exit", description = "Registers a vehicle exit from the parking system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehicle exit registered successfully"),
        @ApiResponse(responseCode = "404", description = "Vehicle not found or already exited")
    })
    public ResponseEntity<Vehicle> registerExit(
        @Parameter(description = "License plate of the vehicle", required = true)
        @RequestParam String licensePlate) {
        Vehicle vehicle = vehicleService.registerExit(licensePlate);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Get all vehicles", description = "Retrieves all vehicles registered in the system")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{licensePlate}")
    @Operation(summary = "Get vehicle by license plate", description = "Retrieves a specific vehicle by its license plate")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehicle found"),
        @ApiResponse(responseCode = "404", description = "Vehicle not found")
    })
    public ResponseEntity<Vehicle> getVehicleByLicensePlate(
        @Parameter(description = "License plate of the vehicle", required = true)
        @PathVariable String licensePlate) {
        Vehicle vehicle = vehicleService.getVehicleByLicensePlate(licensePlate);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/parked")
    @Operation(summary = "Get currently parked vehicles", description = "Retrieves all vehicles currently parked in the system")
    public ResponseEntity<List<Vehicle>> getCurrentlyParkedVehicles() {
        return ResponseEntity.ok(vehicleService.getCurrentlyParkedVehicles());
    }
} 