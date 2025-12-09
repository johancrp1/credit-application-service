package com.example.credit_application_service.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public final class SolicitudCreditoModel {

    private UUID id;
    private String afiliadoDocumento;
    private double monto;
    private int plazoMeses;
    private double tasaPropuesta;
    private LocalDate fechaSolicitud;
    private EstadoSolicitudEnum estado; // <-- Ahora usamos el enum externo
    private EvaluacionRiesgoModel evaluacion;

    // Constructor
    public SolicitudCreditoModel(UUID id, String afiliadoDocumento, double monto,
                                 int plazoMeses, double tasaPropuesta, LocalDate fechaSolicitud,
                                 EstadoSolicitudEnum estado, EvaluacionRiesgoModel evaluacion) {
        this.id = id;
        this.afiliadoDocumento = afiliadoDocumento;
        this.monto = monto;
        this.plazoMeses = plazoMeses;
        this.tasaPropuesta = tasaPropuesta;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        this.evaluacion = evaluacion;
    }

    // Getters
    public UUID getId() { return id; }
    public String getAfiliadoDocumento() { return afiliadoDocumento; }
    public double getMonto() { return monto; }
    public int getPlazoMeses() { return plazoMeses; }
    public double getTasaPropuesta() { return tasaPropuesta; }
    public LocalDate getFechaSolicitud() { return fechaSolicitud; }
    public EstadoSolicitudEnum getEstado() { return estado; }
    public EvaluacionRiesgoModel getEvaluacion() { return evaluacion; }

    // Setters necesarios para evaluación
    public void setEstado(EstadoSolicitudEnum estado) { this.estado = estado; }
    public void setEvaluacion(EvaluacionRiesgoModel evaluacion) { this.evaluacion = evaluacion; }
}
