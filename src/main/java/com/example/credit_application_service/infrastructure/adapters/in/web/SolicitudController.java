package com.example.credit_application_service.infrastructure.adapters.in.web;

import com.example.credit_application_service.application.usecase.CrearSolicitudUseCaseImpl;
import com.example.credit_application_service.domain.model.SolicitudCreditoModel;
import com.example.credit_application_service.domain.ports.in.CrearSolicitudUseCase;
import com.example.credit_application_service.infrastructure.adapters.in.web.dto.CrearSolicitudRequest;
import com.example.credit_application_service.infrastructure.adapters.in.web.dto.SolicitudResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final CrearSolicitudUseCase crearSolicitudUseCase;

    public SolicitudController(CrearSolicitudUseCase crearSolicitudUseCase) {
        this.crearSolicitudUseCase = crearSolicitudUseCase;
    }

    @PostMapping
    public ResponseEntity<SolicitudResponse> crearSolicitud(@Valid @RequestBody CrearSolicitudRequest request) {
        CrearSolicitudUseCase.CrearSolicitudCommand cmd =
                new CrearSolicitudUseCase.CrearSolicitudCommand(
                        request.getAfiliadoDocumento(),
                        request.getMonto(),
                        request.getPlazoMeses(),
                        request.getTasaPropuesta()
                );

        SolicitudCreditoModel saved = crearSolicitudUseCase.execute(cmd);

        SolicitudResponse resp = new SolicitudResponse();
        resp.setId(saved.getId());
        resp.setAfiliadoDocumento(saved.getAfiliadoDocumento());
        resp.setMonto(saved.getMonto());
        resp.setPlazoMeses(saved.getPlazoMeses());
        resp.setTasaPropuesta(saved.getTasaPropuesta());
        resp.setFechaSolicitud(saved.getFechaSolicitud());
        resp.setEstado(saved.getEstado().name());

        return ResponseEntity.created(URI.create("/api/solicitudes/" + saved.getId())).body(resp);
    }
}
