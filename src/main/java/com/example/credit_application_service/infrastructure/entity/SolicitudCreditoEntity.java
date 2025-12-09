package com.example.credit_application_service.infrastructure.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "solicitudes_credito")
public class SolicitudCreditoEntity {

    @Id
    @Column(name = "id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "afiliado_documento", nullable = false)
    private String afiliadoDocumento;

    @Column(name = "monto", nullable = false)
    private double monto;

    @Column(name = "plazo_meses", nullable = false)
    private int plazoMeses;

    @Column(name = "tasa_propuesta", nullable = false)
    private double tasaPropuesta;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoSolicitudEnumEntity estado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evaluacion_id", referencedColumnName = "id")
    private EvaluacionRiesgoEntity evaluacion;

    public SolicitudCreditoEntity() {}

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getAfiliadoDocumento() { return afiliadoDocumento; }
    public void setAfiliadoDocumento(String afiliadoDocumento) { this.afiliadoDocumento = afiliadoDocumento; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public int getPlazoMeses() { return plazoMeses; }
    public void setPlazoMeses(int plazoMeses) { this.plazoMeses = plazoMeses; }

    public double getTasaPropuesta() { return tasaPropuesta; }
    public void setTasaPropuesta(double tasaPropuesta) { this.tasaPropuesta = tasaPropuesta; }

    public LocalDate getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDate fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public EstadoSolicitudEnumEntity getEstado() { return estado; }
    public void setEstado(EstadoSolicitudEnumEntity estado) { this.estado = estado; }

    public EvaluacionRiesgoEntity getEvaluacion() { return evaluacion; }
    public void setEvaluacion(EvaluacionRiesgoEntity evaluacion) { this.evaluacion = evaluacion; }
}
