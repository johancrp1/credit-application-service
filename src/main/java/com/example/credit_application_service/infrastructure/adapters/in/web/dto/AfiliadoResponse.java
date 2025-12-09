package com.example.credit_application_service.infrastructure.adapters.in.web.dto;

import java.time.LocalDate;
import java.util.UUID;

public class AfiliadoResponse {
    private UUID id;
    private String documento;
    private String nombre;
    private double salario;
    private LocalDate fechaAfiliacion;
    private String estado;

    public AfiliadoResponse() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public LocalDate getFechaAfiliacion() { return fechaAfiliacion; }
    public void setFechaAfiliacion(LocalDate fechaAfiliacion) { this.fechaAfiliacion = fechaAfiliacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
