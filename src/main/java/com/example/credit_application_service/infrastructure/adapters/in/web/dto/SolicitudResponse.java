package com.example.credit_application_service.infrastructure.adapters.in.web.dto;

import com.example.credit_application_service.infrastructure.entity.SolicitudCreditoEntity;

import java.time.LocalDate;
import java.util.UUID;

public class SolicitudResponse {

    private UUID id;
    private String afiliadoDocumento;
    private double monto;
    private int plazoMeses;
    private double tasaPropuesta;
    private LocalDate fechaSolicitud;
    private String estado;

    public SolicitudResponse() {}

    // getters & setters
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

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
