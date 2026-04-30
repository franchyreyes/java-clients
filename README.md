# 🧩 Client Management System

Este proyecto implementa un sistema de **gestión de clientes y direcciones** utilizando **Spring Boot 4.x**, siguiendo principios de **Clean Architecture** y el patrón **CQRS (Command Query Responsibility Segregation)**.

---

## 🚀 Características principales
- **Gestión de clientes**: creación, actualización, eliminación y consulta de clientes.
- **Gestión de direcciones**: cada cliente puede tener múltiples direcciones; se pueden eliminar individualmente sin afectar al cliente.
- **Relaciones JPA/Hibernate**: configuración con `cascade` y `orphanRemoval` para garantizar que al eliminar un cliente se eliminen también sus direcciones asociadas.
- **CQRS**: separación clara entre casos de uso de comandos (crear, actualizar, eliminar) y queries (consultar, listar).
- **Handlers**: capa de orquestación que conecta controladores con casos de uso.
- **DTOs y Responses**: uso de objetos de transferencia (`ClientDTO`, `AddressDTO`) y respuestas estandarizadas (`SuccessResponse`) para mantener consistencia en la API.
- **Paginación y ordenamiento**: soporte para `Pageable` en consultas masivas, evitando problemas de rendimiento con grandes volúmenes de datos.
- **Endpoints RESTful**: siguiendo convenciones HTTP (`200 OK`, `201 Created`, `204 No Content`, `404 Not Found`).

---

## 🛠️ Tecnologías utilizadas
- Java 17+
- Spring Boot 4.x
- Spring Data JPA
- Hibernate
- REST API
- CQRS + Clean Architecture

---

## 📡 Endpoints principales

### 🔹 Clientes
- `GET /clients?page=0&size=10` → Obtener clientes con paginación.
- `POST /clients` → Crear un nuevo cliente.
- `PUT /clients/{id}` → Actualizar un cliente existente.
- `DELETE /clients/{id}` → Eliminar un cliente y todas sus direcciones.

### 🔹 Direcciones
- `POST /clients/{id}/address` → Crear una dirección para un cliente.
- `GET /clients/{id}/address` →  Obtener direcciónes para un cliente.
- `DELETE /clients/{idClient}/address/{idAddress}` → Eliminar una dirección específica de un cliente (el cliente permanece).

---

## 📑 Ejemplos de respuestas

### ✅ GET /clients?page=0&size=5
```json
{
  "timestamp": "2026-04-29T16:50:00",
  "status": 200,
  "message": "Clients retrieved successfully",
  "data": {
    "content": [
      { "id": 1, "name": "Alice", "email": "alice@example.com" },
      { "id": 2, "name": "Bob", "email": "bob@example.com" }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 5
    },
    "totalPages": 200,
    "totalElements": 1000,
    "last": false,
    "first": true
  }
}
