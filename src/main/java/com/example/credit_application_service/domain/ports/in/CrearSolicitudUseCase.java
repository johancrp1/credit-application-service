package com.example.credit_application_service.domain.ports.in;

import com.example.credit_application_service.domain.model.SolicitudCreditoModel;

public interface CrearSolicitudUseCase {

    SolicitudCreditoModel execute(CrearSolicitudCommand command);

    record CrearSolicitudCommand(String afiliadoDocumento, double monto,
                                 int plazoMeses, double tasaPropuesta) {}
}
