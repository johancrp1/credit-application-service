package com.example.credit_application_service.domain.model;

public final class RiskEvaluationModel {
    private final int score;
    private final String nivelRiesgo;
    private final String detalle;

    public RiskEvaluationModel(int score, String nivelRiesgo, String detalle) {
        this.score = score;
        this.nivelRiesgo = nivelRiesgo;
        this.detalle = detalle;
    }

    public int getScore() { return score; }
    public String getNivelRiesgo() { return nivelRiesgo; }
    public String getDetalle() { return detalle; }
}
