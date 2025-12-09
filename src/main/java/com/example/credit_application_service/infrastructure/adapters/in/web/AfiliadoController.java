package com.example.credit_application_service.infrastructure.adapters.in.web;

import com.example.credit_application_service.application.usecase.CrearAfiliadoUseCaseImpl;
import com.example.credit_application_service.domain.ports.in.CrearAfiliadoUseCase;
import com.example.credit_application_service.infrastructure.adapters.in.web.dto.AfiliadoResponse;
import com.example.credit_application_service.infrastructure.adapters.in.web.dto.CrearAfiliadoRequest;
import com.example.credit_application_service.domain.model.AfiliadoModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/afiliados")
public class AfiliadoController {

    private final CrearAfiliadoUseCase crearAfiliadoUseCase;

    public AfiliadoController(CrearAfiliadoUseCase crearAfiliadoUseCase) {
        this.crearAfiliadoUseCase = crearAfiliadoUseCase;
    }

    @PostMapping
    public ResponseEntity<AfiliadoResponse> crearAfiliado(@Valid @RequestBody CrearAfiliadoRequest request) {
        CrearAfiliadoUseCase.CrearAfiliadoCommand cmd =
                new CrearAfiliadoUseCase.CrearAfiliadoCommand(request.getDocumento(), request.getNombre(), request.getSalario());

        AfiliadoModel saved = crearAfiliadoUseCase.execute(cmd);

        AfiliadoResponse resp = new AfiliadoResponse();
        resp.setId(saved.getId());
        resp.setDocumento(saved.getDocumento());
        resp.setNombre(saved.getNombre());
        resp.setSalario(saved.getSalario());
        resp.setFechaAfiliacion(saved.getFechaAfiliacion());
        resp.setEstado(saved.getEstado().name());

        return ResponseEntity.created(URI.create("/api/afiliados/" + saved.getId())).body(resp);
    }
}
