package com.example.credit_application_service.application.usecase;

import com.example.credit_application_service.domain.model.AfiliadoModel;
import com.example.credit_application_service.domain.ports.in.CrearAfiliadoUseCase;
import com.example.credit_application_service.domain.ports.out.AfiliadoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CrearAfiliadoUseCaseImpl implements CrearAfiliadoUseCase {

    private final AfiliadoRepositoryPort afiliadoRepository;

    public CrearAfiliadoUseCaseImpl(AfiliadoRepositoryPort afiliadoRepository) {
        this.afiliadoRepository = afiliadoRepository;
    }

    @Override
    @Transactional
    public AfiliadoModel execute(CrearAfiliadoCommand command) {
        // Validaciones de negocio
        if (command.salario() <= 0) {
            throw new IllegalArgumentException("salario debe ser mayor que 0");
        }

        afiliadoRepository.findByDocumento(command.documento()).ifPresent(a ->
        { throw new DuplicateDocumentoException("Documento ya registrado: " + command.documento()); });

        AfiliadoModel afiliado = new AfiliadoModel(
                UUID.randomUUID(),
                command.documento(),
                command.nombre(),
                command.salario(),
                LocalDate.now(),
                AfiliadoModel.EstadoAfiliado.ACTIVO
        );

        return afiliadoRepository.save(afiliado);
    }

    public static class DuplicateDocumentoException extends RuntimeException {
        public DuplicateDocumentoException(String message) { super(message); }
    }
}
