package com.example.credit_application_service.domain.model;

import java.util.UUID;

public final class EvaluacionRiesgoModel {

    private final UUID id;
    private final int score;
    private final String nivelRiesgo;
    private final String detalle;

    public EvaluacionRiesgoModel(UUID id, int score, String nivelRiesgo, String detalle) {
        this.id = id;
        this.score = score;
        this.nivelRiesgo = nivelRiesgo;
        this.detalle = detalle;
    }

    // getters
    public UUID getId() { return id; }
    public int getScore() { return score; }
    public String getNivelRiesgo() { return nivelRiesgo; }
    public String getDetalle() { return detalle; }
}
