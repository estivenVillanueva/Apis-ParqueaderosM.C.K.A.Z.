package com.parking.repository;

import com.parking.model.PagoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PagoVehiculoRepository extends JpaRepository<PagoVehiculo, Long> {
    
    @Query("SELECT p FROM PagoVehiculo p WHERE p.vehiculo.id = :vehiculoId")
    List<PagoVehiculo> findByVehiculoId(@Param("vehiculoId") Long vehiculoId);
    
    @Query("SELECT p FROM PagoVehiculo p WHERE p.estadoPago = 'PENDIENTE' AND p.fechaVencimiento <= :fecha")
    List<PagoVehiculo> findPagosPendientesVencidos(@Param("fecha") LocalDateTime fecha);
    
    @Query("SELECT p FROM PagoVehiculo p WHERE p.estadoPago = 'PENDIENTE' AND p.fechaVencimiento <= :fecha AND p.recordatorioEnviado = false")
    List<PagoVehiculo> findPagosPendientesParaRecordatorio(@Param("fecha") LocalDateTime fecha);
} 