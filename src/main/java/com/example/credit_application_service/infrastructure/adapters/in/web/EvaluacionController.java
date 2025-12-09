package com.example.credit_application_service.infrastructure.adapters.in.web;

import com.example.credit_application_service.domain.model.SolicitudCreditoModel;
import com.example.credit_application_service.domain.ports.in.EvaluarSolicitudUseCase;
import com.example.credit_application_service.infrastructure.adapters.in.web.dto.EvaluarSolicitudRequest;
import com.example.credit_application_service.infrastructure.adapters.in.web.dto.EvaluarSolicitudResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solicitudes")
public class EvaluacionController {

    private final EvaluarSolicitudUseCase evaluarSolicitudUseCase;

    public EvaluacionController(EvaluarSolicitudUseCase evaluarSolicitudUseCase) {
        this.evaluarSolicitudUseCase = evaluarSolicitudUseCase;
    }

    @PostMapping("/evaluar")
    public ResponseEntity<EvaluarSolicitudResponse> evaluarSolicitud(@Valid @RequestBody EvaluarSolicitudRequest request) {
        // Convertir UUID a command
        EvaluarSolicitudUseCase.EvaluarSolicitudCommand command =
                new EvaluarSolicitudUseCase.EvaluarSolicitudCommand(request.getSolicitudId());

        SolicitudCreditoModel updated = evaluarSolicitudUseCase.execute(command);

        EvaluarSolicitudResponse resp = new EvaluarSolicitudResponse();
        resp.setSolicitudId(updated.getId());
        resp.setEstado(updated.getEstado().name());
        resp.setNivelRiesgo(updated.getEvaluacion().getNivelRiesgo());
        resp.setDetalle(updated.getEvaluacion().getDetalle());

        return ResponseEntity.ok(resp);
    }
}
