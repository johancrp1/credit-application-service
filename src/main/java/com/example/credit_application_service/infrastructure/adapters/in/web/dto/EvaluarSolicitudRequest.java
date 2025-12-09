package com.example.credit_application_service.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class EvaluarSolicitudRequest {

    @NotNull
    private UUID solicitudId;

    public UUID getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(UUID solicitudId) {
        this.solicitudId = solicitudId;
    }
}
