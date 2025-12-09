package com.example.credit_application_service.infrastructure.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "afiliados", uniqueConstraints = {
        @UniqueConstraint(name = "uk_afiliado_documento", columnNames = "documento")
})
public class AfiliadoEntity {

    @Id
    @Column(name = "id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "documento", nullable = false, length = 50)
    private String documento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "salario", nullable = false)
    private double salario;

    @Column(name = "fecha_afiliacion", nullable = false)
    private LocalDate fechaAfiliacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoAfiliadoEntity estado;

    public enum EstadoAfiliadoEntity { ACTIVO, INACTIVO }

    public AfiliadoEntity() {}

    public AfiliadoEntity(UUID id, String documento, String nombre, double salario, LocalDate fechaAfiliacion, EstadoAfiliadoEntity estado) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.salario = salario;
        this.fechaAfiliacion = fechaAfiliacion;
        this.estado = estado;
    }

    // getters & setters

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

    public EstadoAfiliadoEntity getEstado() { return estado; }
    public void setEstado(EstadoAfiliadoEntity estado) { this.estado = estado; }
}
