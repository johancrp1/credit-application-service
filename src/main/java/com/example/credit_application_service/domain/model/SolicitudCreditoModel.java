package com.example.credit_application_service.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public final class SolicitudCreditoModel {

    private final UUID id;
    private final String afiliadoDocumento;
    private final double monto;
    private final int plazoMeses;
    private final double tasaPropuesta;
    private final LocalDate fechaSolicitud;
    private final EstadoSolicitudEnum estado;
    private final EvaluacionRiesgoModel evaluacion;

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

    public UUID getId() { return id; }
    public String getAfiliadoDocumento() { return afiliadoDocumento; }
    public double getMonto() { return monto; }
    public int getPlazoMeses() { return plazoMeses; }
    public double getTasaPropuesta() { return tasaPropuesta; }
    public LocalDate getFechaSolicitud() { return fechaSolicitud; }
    public EstadoSolicitudEnum getEstado() { return estado; }
    public EvaluacionRiesgoModel getEvaluacion() { return evaluacion; }
}
