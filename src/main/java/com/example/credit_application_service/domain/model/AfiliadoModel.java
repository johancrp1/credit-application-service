package com.example.credit_application_service.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public final class AfiliadoModel {

    public enum EstadoAfiliado { ACTIVO, INACTIVO }

    private final UUID id;
    private final String documento;
    private final String nombre;
    private final double salario;
    private final LocalDate fechaAfiliacion;
    private final EstadoAfiliado estado;

    public AfiliadoModel(UUID id, String documento, String nombre, double salario,
                         LocalDate fechaAfiliacion, EstadoAfiliado estado) {

        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.salario = salario;
        this.fechaAfiliacion = fechaAfiliacion;
        this.estado = estado;
    }

    public UUID getId() { return id; }
    public String getDocumento() { return documento; }
    public String getNombre() { return nombre; }
    public double getSalario() { return salario; }
    public LocalDate getFechaAfiliacion() { return fechaAfiliacion; }
    public EstadoAfiliado getEstado() { return estado; }

    public boolean isActivo() {
        return estado == EstadoAfiliado.ACTIVO;
    }
}
