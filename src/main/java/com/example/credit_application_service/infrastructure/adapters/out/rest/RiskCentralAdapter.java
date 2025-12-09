package com.example.credit_application_service.infrastructure.adapters.out.rest;

import com.example.credit_application_service.domain.model.RiskEvaluationModel;
import com.example.credit_application_service.domain.ports.out.RiskCentralPort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Component
public class RiskCentralAdapter implements RiskCentralPort {

    private final RestTemplate restTemplate;

    public RiskCentralAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public RiskEvaluationModel evaluarRiesgo(String documento, double monto, int plazoMeses) {

        // Construir body del request
        Map<String, Object> body = new HashMap<>();
        body.put("documento", documento);
        body.put("monto", monto);
        body.put("plazo", plazoMeses);

        // Llamada POST
        ResponseEntity<RiskEvaluationModel> response = restTemplate.postForEntity(
                "http://localhost:8081/risk-evaluation",
                body,
                RiskEvaluationModel.class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            return new RiskEvaluationModel(0, "DESCONOCIDO", "Error al consultar riesgo");
        }
    }
}
