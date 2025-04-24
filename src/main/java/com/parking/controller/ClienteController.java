package com.parking.controller;

import com.parking.model.Cliente;
import com.parking.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Cliente", description = "API para gestionar clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtener todos los clientes")
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un cliente por ID")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        return cliente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente")
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.createCliente(cliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente existente")
    public ResponseEntity<Cliente> updateCliente(
            @PathVariable Long id,
            @RequestBody Cliente clienteDetails) {
        Cliente updatedCliente = clienteService.updateCliente(id, clienteDetails);
        return updatedCliente != null ?
                ResponseEntity.ok(updatedCliente) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
} 