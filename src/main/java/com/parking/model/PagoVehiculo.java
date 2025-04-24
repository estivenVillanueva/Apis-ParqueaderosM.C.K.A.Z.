package com.parking.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pagos_vehiculos")
public class PagoVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;

    @Column(name = "fecha_vencimiento")
    private LocalDateTime fechaVencimiento;

    @Column(name = "estado_pago", nullable = false)
    private String estadoPago; // PENDIENTE, PAGADO, VENCIDO

    @Column(name = "numero_factura", unique = true)
    private String numeroFactura;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago; // EFECTIVO, TARJETA, TRANSFERENCIA

    @Column(name = "recordatorio_enviado")
    private Boolean recordatorioEnviado = false;
} 