package com.grade.manage.controller;

import com.grade.manage.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Studios TKOH!
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<ApiResponse<Map<String, Object>>> home() {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("mensaje", "Bienvenido a la API de grading de Studios TKOH");

        // Resumen de la API
        Map<String, String> resumen = new LinkedHashMap<>();
        resumen.put("descripcion", "REST API para la gestión de plantillas de actividades, asignaciones y puntajes de desempeño.");
        resumen.put("tecnologias", "Spring Boot 3.5.6, Java 21, JPA/Hibernate, MySQL y JWT.");
        resumen.put("seguridad", "Autenticación basada en tokens JWT.");
        info.put("resumen", resumen);

        // Endpoints Básicos
        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("Autenticación (POST)", "/api/auth/login");
        endpoints.put("Registro (POST)", "/api/auth/register");
        endpoints.put("Lista de Usuarios (GET)", "/api/users");
        endpoints.put("Lista de Asignaciones (GET)", "/api/assignments");
        endpoints.put("Historial Reciente (GET)", "/api/score-history/users/{userId}/recent");
        info.put("endpoints_principales", endpoints);

        return ResponseEntity.ok(ApiResponse.ok("Información de la API obtenida correctamente", info));
    }
}
