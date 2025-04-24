package com.parking.controller;

import com.parking.model.PagoVehiculo;
import com.parking.service.PagoVehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos-vehiculos")
@Tag(name = "Pago Vehículo", description = "API para gestionar pagos de vehículos")
public class PagoVehiculoController {

    @Autowired
    private PagoVehiculoService pagoVehiculoService;

    @GetMapping
    @Operation(summary = "Obtener todos los pagos de vehículos")
    public List<PagoVehiculo> getAllPagos() {
        return pagoVehiculoService.getAllPagos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un pago por ID")
    public ResponseEntity<PagoVehiculo> getPagoById(@PathVariable Long id) {
        Optional<PagoVehiculo> pago = pagoVehiculoService.getPagoById(id);
        return pago.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/vehiculo/{vehiculoId}")
    @Operation(summary = "Obtener pagos por ID de vehículo")
    public List<PagoVehiculo> getPagosByVehiculoId(@PathVariable Long vehiculoId) {
        return pagoVehiculoService.getPagosByVehiculoId(vehiculoId);
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo pago de vehículo")
    public PagoVehiculo createPago(@RequestBody PagoVehiculo pago) {
        return pagoVehiculoService.createPago(pago);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pago de vehículo")
    public ResponseEntity<Void> deletePago(@PathVariable Long id) {
        pagoVehiculoService.deletePago(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pendientes-vencidos")
    @Operation(summary = "Obtener pagos pendientes vencidos")
    public List<PagoVehiculo> getPagosPendientesVencidos() {
        return pagoVehiculoService.getPagosPendientesVencidos();
    }

    @GetMapping("/recordatorios")
    @Operation(summary = "Obtener pagos pendientes para recordatorio")
    public List<PagoVehiculo> getPagosParaRecordatorio() {
        return pagoVehiculoService.getPagosParaRecordatorio();
    }

    @PutMapping("/{id}/marcar-recordatorio")
    @Operation(summary = "Marcar recordatorio como enviado")
    public ResponseEntity<Void> marcarRecordatorioEnviado(@PathVariable Long id) {
        pagoVehiculoService.marcarRecordatorioEnviado(id);
        return ResponseEntity.ok().build();
    }
} 