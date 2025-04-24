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
@Tag(name = "Gestión de Vehículos", description = "API para la gestión de entrada y salida de vehículos del parqueadero")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/entry")
    @Operation(
        summary = "Registrar entrada de vehículo", 
        description = "Registra la entrada de un nuevo vehículo al parqueadero"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehículo registrado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "409", description = "El vehículo ya está registrado en el parqueadero")
    })
    public ResponseEntity<Vehicle> registerEntry(
        @Parameter(description = "Placa del vehículo (ejemplo: ABC123)", required = true)
        @RequestParam String licensePlate) {
        return ResponseEntity.ok(vehicleService.registerEntry(licensePlate));
    }

    @PostMapping("/exit")
    @Operation(
        summary = "Registrar salida de vehículo", 
        description = "Registra la salida de un vehículo del parqueadero"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Salida registrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Vehículo no encontrado o ya ha salido")
    })
    public ResponseEntity<Vehicle> registerExit(
        @Parameter(description = "Placa del vehículo (ejemplo: ABC123)", required = true)
        @RequestParam String licensePlate) {
        Vehicle vehicle = vehicleService.registerExit(licensePlate);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(
        summary = "Listar todos los vehículos", 
        description = "Obtiene el registro histórico de todos los vehículos"
    )
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{licensePlate}")
    @Operation(
        summary = "Buscar vehículo por placa", 
        description = "Obtiene la información de un vehículo específico por su placa"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehículo encontrado"),
        @ApiResponse(responseCode = "404", description = "Vehículo no encontrado")
    })
    public ResponseEntity<Vehicle> getVehicleByLicensePlate(
        @Parameter(description = "Placa del vehículo a buscar", required = true)
        @PathVariable String licensePlate) {
        Vehicle vehicle = vehicleService.getVehicleByLicensePlate(licensePlate);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/parked")
    @Operation(
        summary = "Listar vehículos estacionados", 
        description = "Obtiene la lista de vehículos que actualmente están en el parqueadero"
    )
    public ResponseEntity<List<Vehicle>> getCurrentlyParkedVehicles() {
        return ResponseEntity.ok(vehicleService.getCurrentlyParkedVehicles());
    }
} 