package com.example.credit_application_service.application.usecase;

import com.example.credit_application_service.domain.model.AfiliadoModel;
import com.example.credit_application_service.domain.model.SolicitudCreditoModel;
import com.example.credit_application_service.domain.model.EstadoSolicitudEnum;
import com.example.credit_application_service.domain.ports.in.CrearSolicitudUseCase;
import com.example.credit_application_service.domain.ports.out.AfiliadoRepositoryPort;
import com.example.credit_application_service.domain.ports.out.SolicitudRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CrearSolicitudUseCaseImpl implements CrearSolicitudUseCase {

    private final SolicitudRepositoryPort solicitudRepository;
    private final AfiliadoRepositoryPort afiliadoRepository;

    public CrearSolicitudUseCaseImpl(SolicitudRepositoryPort solicitudRepository, AfiliadoRepositoryPort afiliadoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.afiliadoRepository = afiliadoRepository;
    }

    @Override
    @Transactional
    public SolicitudCreditoModel execute(CrearSolicitudCommand command) {
        AfiliadoModel afiliado = afiliadoRepository.findByDocumento(command.afiliadoDocumento())
                .orElseThrow(() -> new IllegalArgumentException("Afiliado no encontrado"));

        if (!afiliado.isActivo()) {
            throw new IllegalArgumentException("Afiliado no está activo");
        }
        if (command.monto() <= 0) throw new IllegalArgumentException("Monto inválido");
        if (command.plazoMeses() <= 0) throw new IllegalArgumentException("Plazo inválido");

        SolicitudCreditoModel solicitud = new SolicitudCreditoModel(
                UUID.randomUUID(),
                afiliado.getDocumento(),
                command.monto(),
                command.plazoMeses(),
                command.tasaPropuesta(),
                LocalDate.now(),
                EstadoSolicitudEnum.PENDIENTE,
                null
        );

        return solicitudRepository.save(solicitud);
    }
}
