package com.example.credit_application_service.infrastructure.adapters.in.web.dto;

import java.util.UUID;

public class EvaluarSolicitudResponse {
    private UUID solicitudId;
    private String estado;
    private String nivelRiesgo;
    private String detalle;

    public UUID getSolicitudId() { return solicitudId; }
    public void setSolicitudId(UUID solicitudId) { this.solicitudId = solicitudId; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getNivelRiesgo() { return nivelRiesgo; }
    public void setNivelRiesgo(String nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }

    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
}
