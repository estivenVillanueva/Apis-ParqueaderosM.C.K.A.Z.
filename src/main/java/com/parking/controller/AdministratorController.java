package com.parking.controller;

import com.parking.model.Administrator;
import com.parking.service.AdministratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/administrators")
@CrossOrigin(origins = "*")
@Tag(name = "Gestión de Administradores", description = "API para la gestión de administradores del sistema")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @PostMapping
    @Operation(
        summary = "Registrar nuevo administrador",
        description = "Crea un nuevo administrador en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador registrado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "409", description = "El correo o teléfono ya están registrados")
    })
    public ResponseEntity<Administrator> createAdministrator(
        @Parameter(description = "Datos del administrador a registrar", required = true)
        @RequestBody Administrator administrator) {
        return ResponseEntity.ok(administratorService.createAdministrator(administrator));
    }

    @GetMapping
    @Operation(
        summary = "Listar todos los administradores",
        description = "Obtiene la lista de todos los administradores registrados"
    )
    public ResponseEntity<List<Administrator>> getAllAdministrators() {
        return ResponseEntity.ok(administratorService.getAllAdministrators());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar administrador por ID",
        description = "Obtiene la información de un administrador específico por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    public ResponseEntity<Administrator> getAdministratorById(
        @Parameter(description = "ID del administrador a buscar", required = true)
        @PathVariable Long id) {
        Optional<Administrator> administrator = administratorService.getAdministratorById(id);
        return administrator.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    @Operation(
        summary = "Buscar administrador por correo",
        description = "Obtiene la información de un administrador por su correo electrónico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    public ResponseEntity<Administrator> getAdministratorByEmail(
        @Parameter(description = "Correo electrónico del administrador", required = true)
        @PathVariable String email) {
        Optional<Administrator> administrator = administratorService.getAdministratorByEmail(email);
        return administrator.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar administrador",
        description = "Actualiza la información de un administrador existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado"),
        @ApiResponse(responseCode = "409", description = "El nuevo correo o teléfono ya están registrados")
    })
    public ResponseEntity<Administrator> updateAdministrator(
        @Parameter(description = "ID del administrador a actualizar", required = true)
        @PathVariable Long id,
        @Parameter(description = "Nuevos datos del administrador", required = true)
        @RequestBody Administrator administrator) {
        try {
            return ResponseEntity.ok(administratorService.updateAdministrator(id, administrator));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar administrador",
        description = "Elimina un administrador del sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    public ResponseEntity<Void> deleteAdministrator(
        @Parameter(description = "ID del administrador a eliminar", required = true)
        @PathVariable Long id) {
        try {
            administratorService.deleteAdministrator(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 