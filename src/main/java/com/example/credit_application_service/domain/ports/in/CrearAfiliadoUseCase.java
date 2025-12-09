package com.example.credit_application_service.domain.ports.in;

import com.example.credit_application_service.domain.model.AfiliadoModel;

public interface CrearAfiliadoUseCase {

    AfiliadoModel execute(CrearAfiliadoCommand command);

    record CrearAfiliadoCommand(String documento, String nombre, double salario) {}
}
