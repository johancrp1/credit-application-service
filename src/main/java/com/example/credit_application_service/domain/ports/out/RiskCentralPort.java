package com.example.credit_application_service.domain.ports.out;

public interface RiskCentralPort {

    RiskResponse evaluate(String documento, double monto, int plazoMeses);

    class RiskResponse {
        public final String documento;
        public final int score;
        public final String nivel;
        public final String detalle;

        public RiskResponse(String documento, int score, String nivel, String detalle) {
            this.documento = documento;
            this.score = score;
            this.nivel = nivel;
            this.detalle = detalle;
        }
    }
}
