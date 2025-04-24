package com.parking.controller;

import com.parking.model.Parqueadero;
import com.parking.service.ParqueaderoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parqueaderos")
@Tag(name = "Parqueadero", description = "API para gestionar parqueaderos")
public class ParqueaderoController {

    @Autowired
    private ParqueaderoService parqueaderoService;

    @GetMapping
    @Operation(summary = "Obtener todos los parqueaderos")
    public List<Parqueadero> getAllParqueaderos() {
        return parqueaderoService.getAllParqueaderos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un parqueadero por ID")
    public ResponseEntity<Parqueadero> getParqueaderoById(@PathVariable Long id) {
        Optional<Parqueadero> parqueadero = parqueaderoService.getParqueaderoById(id);
        return parqueadero.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo parqueadero")
    public Parqueadero createParqueadero(@RequestBody Parqueadero parqueadero) {
        return parqueaderoService.createParqueadero(parqueadero);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un parqueadero existente")
    public ResponseEntity<Parqueadero> updateParqueadero(
            @PathVariable Long id,
            @RequestBody Parqueadero parqueaderoDetails) {
        Parqueadero updatedParqueadero = parqueaderoService.updateParqueadero(id, parqueaderoDetails);
        return updatedParqueadero != null ?
                ResponseEntity.ok(updatedParqueadero) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un parqueadero")
    public ResponseEntity<Void> deleteParqueadero(@PathVariable Long id) {
        parqueaderoService.deleteParqueadero(id);
        return ResponseEntity.noContent().build();
    }
} 