CoopCredit – Sistema Integral de Solicitudes de Crédito
=======================================================

Descripción del Proyecto
------------------------
CoopCredit es un sistema modular para la gestión de solicitudes de crédito de una cooperativa. El sistema reemplaza los procesos manuales con un flujo seguro, trazable y escalable. Implementa:

- Arquitectura Hexagonal: separación clara entre dominio, casos de uso y adaptadores.
- Microservicio de solicitudes de crédito (credit-application-service).
- Microservicio simulado de riesgo (risk-central-mock-service).
- Seguridad JWT con roles.
- Persistencia H2 (proyecto inicial; se puede migrar a MySQL/Postgres).
- Flujo de evaluación de solicitudes con políticas internas.

Tecnologías Utilizadas
----------------------
- Java 17 / Spring Boot 3
- Spring Security + JWT
- H2 Database
- Spring Web (REST)
- Maven
- Postman para pruebas

Arquitectura Hexagonal
----------------------
com.example.credit_application_service
├── domain
│   ├── model               # Modelos de dominio puros 
│   ├── service             # Servicios de dominio
│   └── ports
│       ├── in              # Interfaces de casos de uso
│       └── out             # Interfaces de repositorios o servicios externos
│
├── application
│   ├── usecase             # Implementación de casos de uso
│   └── mapper              # MapStruct u otros mapeos DTO <-> Domain
│
├── infrastructure
│   ├── adapters
│   │   ├── in/web          # Controladores REST
│   │   └── out/jpa         # Persistencia JPA / H2
│   ├── config              # Seguridad, JWT, Spring Config
│   └── exception           # Manejo global de errores
│
└── CreditApplicationServiceApplication.java

Endpoints Principales
--------------------

1️⃣ Autenticación

Método | Endpoint         | Rol requerido | Descripción
-------|----------------|---------------|-------------
POST   | /auth/register  | Ninguno       | Registrar usuario y generar JWT
POST   | /auth/login     | Ninguno       | Login y obtener JWT

2️⃣ Gestión de Afiliados (solo AFILIADO)

Método | Endpoint                | Descripción
-------|-----------------------|--------------------------------
POST   | /api/afiliados         | Crear afiliado
GET    | /api/afiliados/{id}    | Obtener datos de afiliado

3️⃣ Gestión de Solicitudes

Método | Endpoint                     | Rol requerido | Descripción
-------|-----------------------------|----------------|----------------------------------
POST   | /api/solicitudes             | AFILIADO       | Crear solicitud de crédito
POST   | /api/solicitudes/evaluar     | ANALISTA       | Evaluar solicitud (con RiskCentral)
GET    | /api/solicitudes/{id}        | Cualquiera     | Consultar solicitud y evaluación

Flujo de Solicitud de Crédito
-----------------------------
1. Crear usuario y obtener JWT.
2. Crear afiliado (AFILIADO) → asignar documento único, salario y fecha de afiliación.
3. Crear solicitud de crédito → estado inicial PENDIENTE.
4. Evaluar solicitud (ANALISTA):
   - Llama al microservicio risk-central-mock-service para obtener score y nivel de riesgo.
   - Aplica políticas internas:
     - Cuota / ingreso < 50%
     - Monto máximo según salario
     - Antigüedad mínima del afiliado (6 meses)
   - Actualiza estado de la solicitud (APROBADO / RECHAZADO) y crea EvaluacionRiesgo.
5. Consultar solicitud → ver estado final y evaluación asociada.

Microservicio de Riesgo (Mock)
------------------------------
- Puerto: 8081
- Endpoint: POST /risk-evaluation
- Recibe: { "documento": "...", "monto": 5000000, "plazo": 36 }
- Responde con score consistente por documento:
{
  "documento": "1017654311",
  "score": 642,
  "nivelRiesgo": "MEDIO",
  "detalle": "Historial crediticio moderado."
}

Pruebas con Postman
-------------------

Variables de colección:
- {{baseUrl}} = http://localhost:8080
- {{token}} → JWT generado en login
- {{afiliadoDocumento}} = 1017654311
- {{solicitudId}} → id generado al crear solicitud

1️⃣ Registrar usuario
POST {{baseUrl}}/auth/register
Body JSON:
{
  "username": "juan",
  "password": "123456",
  "roles": ["ROLE_AFILIADO"]
}
- Guardar token JWT en {{token}}.

2️⃣ Login
POST {{baseUrl}}/auth/login
Body JSON:
{
  "username": "juan",
  "password": "123456"
}
- Guardar token JWT en {{token}}.

3️⃣ Crear Afiliado
POST {{baseUrl}}/api/afiliados
Headers:
Authorization: Bearer {{token}}
Content-Type: application/json
Body JSON:
{
  "documento": "{{afiliadoDocumento}}",
  "nombre": "Juan Pérez",
  "salario": 5000000,
  "fechaAfiliacion": "2023-01-01",
  "estado": "ACTIVO"
}

4️⃣ Crear Solicitud
POST {{baseUrl}}/api/solicitudes
Headers:
Authorization: Bearer {{token}}
Content-Type: application/json
Body JSON:
{
  "afiliadoDocumento": "{{afiliadoDocumento}}",
  "monto": 5000000,
  "plazoMeses": 24,
  "tasaPropuesta": 12.5
}
- Guardar id en {{solicitudId}}.

5️⃣ Evaluar Solicitud
POST {{baseUrl}}/api/solicitudes/evaluar
Headers:
Authorization: Bearer {{token_analista}}
Content-Type: application/json
Body JSON:
{
  "solicitudId": "{{solicitudId}}"
}
- Devuelve estado final (APROBADO / RECHAZADO), nivel de riesgo y detalle.

6️⃣ Consultar Solicitud
GET {{baseUrl}}/api/solicitudes/{{solicitudId}}
Headers:
Authorization: Bearer {{token}}
- Devuelve solicitud con evaluación asociada.

Notas Importantes
-----------------
- Base de datos: H2 (pruebas rápidas; se puede migrar a MySQL/Postgres).
- JWT: stateless, no hay sesiones.
- Roles controlan acceso:
  - AFILIADO → sus solicitudes
  - ANALISTA → evaluaciones
  - ADMIN → acceso completo
- RiskCentralMockService devuelve scores consistentes por documento usando hash interno.

