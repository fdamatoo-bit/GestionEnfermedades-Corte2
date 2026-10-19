package com.fabrizio.gestionenfermedades.infrastructure.adapter.in.rest;

import com.fabrizio.gestionenfermedades.domain.model.Enfermedad;
import com.fabrizio.gestionenfermedades.domain.port.in.EnfermedadUseCase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enfermedades")
public class EnfermedadController {

    private final EnfermedadUseCase enfermedadUseCase;

    public EnfermedadController(EnfermedadUseCase enfermedadUseCase) {
        this.enfermedadUseCase = enfermedadUseCase;
    }

    @PostMapping
    public ResponseEntity<Enfermedad> crear(
            @RequestBody Enfermedad enfermedad) {

        Enfermedad creada = enfermedadUseCase.crear(enfermedad);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creada);
    }

    @GetMapping
    public ResponseEntity<List<Enfermedad>> listarTodas() {

        return ResponseEntity.ok(
                enfermedadUseCase.listarTodas()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enfermedad> buscarPorId(
            @PathVariable Long id) {

        return enfermedadUseCase.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build()
                );
    }

    @GetMapping("/gravedad/{nivelGravedad}")
    public ResponseEntity<List<Enfermedad>> buscarPorNivelGravedad(
            @PathVariable String nivelGravedad) {

        return ResponseEntity.ok(
                enfermedadUseCase
                        .buscarPorNivelGravedad(nivelGravedad)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enfermedad> actualizar(
            @PathVariable Long id,
            @RequestBody Enfermedad enfermedad) {

        try {
            return ResponseEntity.ok(
                    enfermedadUseCase.actualizar(
                            id,
                            enfermedad
                    )
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        if (enfermedadUseCase
                .buscarPorId(id)
                .isEmpty()) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        enfermedadUseCase.eliminar(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    // REPORTE PARAMETRIZADO 1:
    // Filtra enfermedades por nivel de gravedad
    @GetMapping("/reportes/gravedad")
    public ResponseEntity<List<Enfermedad>> reportePorGravedad(
            @RequestParam String nivelGravedad) {

        List<Enfermedad> resultado =
                enfermedadUseCase.listarTodas()
                        .stream()
                        .filter(enfermedad ->
                                enfermedad.getNivelGravedad() != null &&
                                enfermedad.getNivelGravedad()
                                        .equalsIgnoreCase(
                                                nivelGravedad
                                        )
                        )
                        .toList();

        return ResponseEntity.ok(resultado);
    }

    // REPORTE PARAMETRIZADO 2:
    // Filtra enfermedades según sean contagiosas o no
    @GetMapping("/reportes/contagiosa")
    public ResponseEntity<List<Enfermedad>> reportePorContagiosa(
            @RequestParam boolean valor) {

        List<Enfermedad> resultado =
                enfermedadUseCase.listarTodas()
                        .stream()
                        .filter(enfermedad ->
                                enfermedad.isEsContagiosa()
                                        == valor
                        )
                        .toList();

        return ResponseEntity.ok(resultado);
    }
}