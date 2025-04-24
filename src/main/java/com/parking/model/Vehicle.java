package com.parking.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@Table(name = "vehicles")
@Schema(description = "Entidad que representa un vehículo en el sistema de parqueadero")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del registro", example = "1")
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Placa del vehículo", example = "ABC123")
    private String licensePlate;

    @Column(nullable = false)
    @Schema(description = "Fecha y hora de entrada al parqueadero", example = "2024-04-24T08:30:00")
    private LocalDateTime entryTime;

    @Schema(description = "Fecha y hora de salida del parqueadero", example = "2024-04-24T10:30:00")
    private LocalDateTime exitTime;

    @Column(name = "is_parked", nullable = false)
    @Schema(description = "Indica si el vehículo está actualmente en el parqueadero", example = "true")
    private boolean parked = true;
} 