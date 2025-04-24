package com.parking.model;

import jakarta.persistence.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@Table(name = "administrators")
@Schema(description = "Entidad que representa un administrador del sistema de parqueadero")
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del administrador", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nombre completo del administrador", example = "Juan Pérez")
    private String name;

    @Column(nullable = false, unique = true)
    @Schema(description = "Correo electrónico del administrador", example = "juan.perez@example.com")
    private String email;

    @Column(nullable = false)
    @Schema(description = "Contraseña del administrador (debe estar encriptada)")
    private String password;

    @Column(nullable = false, unique = true)
    @Schema(description = "Número de teléfono del administrador", example = "+573001234567")
    private String phoneNumber;
} 