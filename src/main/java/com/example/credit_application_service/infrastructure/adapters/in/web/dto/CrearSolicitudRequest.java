package com.example.credit_application_service.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CrearSolicitudRequest {

    @NotBlank
    private String afiliadoDocumento;

    @Min(value = 1, message = "Monto debe ser mayor a 0")
    private double monto;

    @Min(value = 1, message = "Plazo mínimo 1 mes")
    private int plazoMeses;

    @Min(value = 1, message = "Tasa debe ser positiva")
    private double tasaPropuesta;

    public CrearSolicitudRequest() {}

    public String getAfiliadoDocumento() { return afiliadoDocumento; }
    public void setAfiliadoDocumento(String afiliadoDocumento) { this.afiliadoDocumento = afiliadoDocumento; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public int getPlazoMeses() { return plazoMeses; }
    public void setPlazoMeses(int plazoMeses) { this.plazoMeses = plazoMeses; }

    public double getTasaPropuesta() { return tasaPropuesta; }
    public void setTasaPropuesta(double tasaPropuesta) { this.tasaPropuesta = tasaPropuesta; }
}
