package com.example.credit_application_service.application.usecase;

import com.example.credit_application_service.domain.model.*;
import com.example.credit_application_service.domain.ports.in.EvaluarSolicitudUseCase;
import com.example.credit_application_service.domain.ports.out.AfiliadoRepositoryPort;
import com.example.credit_application_service.domain.ports.out.RiskCentralPort;
import com.example.credit_application_service.domain.ports.out.SolicitudRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EvaluarSolicitudUseCaseImpl implements EvaluarSolicitudUseCase {

    private final SolicitudRepositoryPort solicitudRepository;
    private final AfiliadoRepositoryPort afiliadoRepository;
    private final RiskCentralPort riskCentralPort;

    public EvaluarSolicitudUseCaseImpl(SolicitudRepositoryPort solicitudRepository,
                                       AfiliadoRepositoryPort afiliadoRepository,
                                       RiskCentralPort riskCentralPort) {
        this.solicitudRepository = solicitudRepository;
        this.afiliadoRepository = afiliadoRepository;
        this.riskCentralPort = riskCentralPort;
    }

    @Override
    @Transactional
    public SolicitudCreditoModel execute(EvaluarSolicitudCommand command) {

        // Obtener la solicitud
        SolicitudCreditoModel solicitud = solicitudRepository.findById(command.solicitudId())
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada"));

        // Obtener afiliado
        AfiliadoModel afiliado = afiliadoRepository.findByDocumento(solicitud.getAfiliadoDocumento())
                .orElseThrow(() -> new IllegalArgumentException("Afiliado no encontrado"));

        if (afiliado.getEstado() != AfiliadoModel.EstadoAfiliado.ACTIVO) {
            throw new IllegalArgumentException("Afiliado no está activo");
        }

        // Evaluar riesgo mediante el microservicio mock
        RiskEvaluationModel risk = riskCentralPort.evaluarRiesgo(
                afiliado.getDocumento(),
                solicitud.getMonto(),
                solicitud.getPlazoMeses()
        );

        // Evaluación interna: políticas
        boolean aprobado = true;
        String motivo = "";

        double cuotaMensual = solicitud.getMonto() / solicitud.getPlazoMeses();
        if (cuotaMensual / afiliado.getSalario() > 0.5) {
            aprobado = false;
            motivo = "Cuota mensual supera 50% del salario";
        }

        if (afiliado.getFechaAfiliacion().plusMonths(6).isAfter(solicitud.getFechaSolicitud())) {
            aprobado = false;
            motivo = "Antigüedad menor a 6 meses";
        }

        if (solicitud.getMonto() > afiliado.getSalario() * 10) {
            aprobado = false;
            motivo = "Monto solicitado excede 10x salario";
        }

        // Crear evaluación de riesgo
        EvaluacionRiesgoModel evaluacion = new EvaluacionRiesgoModel(
                UUID.randomUUID(),
                risk.getScore(),
                risk.getNivelRiesgo(),
                motivo.isEmpty() ? risk.getDetalle() : motivo
        );

        // Actualizar solicitud usando el enum externo
        solicitud.setEvaluacion(evaluacion);
        solicitud.setEstado(aprobado ? EstadoSolicitudEnum.APROBADO
                : EstadoSolicitudEnum.RECHAZADO);

        // Guardar y retornar
        return solicitudRepository.save(solicitud);
    }
}
