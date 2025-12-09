package com.example.credit_application_service.domain.ports.out;

import com.example.credit_application_service.domain.model.RiskEvaluationModel;

public interface RiskCentralPort {
    RiskEvaluationModel evaluarRiesgo(String documento, double monto, int plazoMeses);
}
