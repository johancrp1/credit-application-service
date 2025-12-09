package com.example.credit_application_service.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CrearAfiliadoRequest {

    @NotBlank(message = "documento es obligatorio")
    @Size(max = 50)
    private String documento;

    @NotBlank(message = "nombre es obligatorio")
    private String nombre;

    @Min(value = 1, message = "salario debe ser mayor que 0")
    private double salario;

    public CrearAfiliadoRequest() {}

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}
