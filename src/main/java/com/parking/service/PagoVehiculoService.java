package com.parking.service;

import com.parking.model.PagoVehiculo;
import com.parking.repository.PagoVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PagoVehiculoService {

    @Autowired
    private PagoVehiculoRepository pagoVehiculoRepository;

    public List<PagoVehiculo> getAllPagos() {
        return pagoVehiculoRepository.findAll();
    }

    public Optional<PagoVehiculo> getPagoById(Long id) {
        return pagoVehiculoRepository.findById(id);
    }

    public List<PagoVehiculo> getPagosByVehiculoId(Long vehiculoId) {
        return pagoVehiculoRepository.findByVehiculoId(vehiculoId);
    }

    public PagoVehiculo createPago(PagoVehiculo pago) {
        pago.setFechaPago(LocalDateTime.now());
        pago.setEstadoPago("PENDIENTE");
        pago.setNumeroFactura(generarNumeroFactura());
        return pagoVehiculoRepository.save(pago);
    }

    public void deletePago(Long id) {
        pagoVehiculoRepository.deleteById(id);
    }

    public List<PagoVehiculo> getPagosPendientesVencidos() {
        return pagoVehiculoRepository.findPagosPendientesVencidos(LocalDateTime.now());
    }

    public List<PagoVehiculo> getPagosParaRecordatorio() {
        return pagoVehiculoRepository.findPagosPendientesParaRecordatorio(LocalDateTime.now());
    }

    public void marcarRecordatorioEnviado(Long id) {
        Optional<PagoVehiculo> pagoOpt = pagoVehiculoRepository.findById(id);
        if (pagoOpt.isPresent()) {
            PagoVehiculo pago = pagoOpt.get();
            pago.setRecordatorioEnviado(true);
            pagoVehiculoRepository.save(pago);
        }
    }

    private String generarNumeroFactura() {
        return "FACT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
} 