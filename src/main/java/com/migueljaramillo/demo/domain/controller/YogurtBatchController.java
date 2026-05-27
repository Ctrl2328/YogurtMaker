package com.migueljaramillo.demo.domain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.migueljaramillo.demo.domain.model.YogurtBatch;
import com.migueljaramillo.demo.domain.service.YogurtMakingService;
import com.migueljaramillo.demo.dto.BatchDTO;
import com.migueljaramillo.demo.dto.TemperatureRecordDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
@Tag(name = "2. Producción y Lotes", description = "Control del proceso de elaboración de yogurt y registro de temperaturas")
public class YogurtBatchController {
    
    private final YogurtMakingService yogurtMakingService;
    
    @Operation(summary = "Iniciar un nuevo lote", description = "Toma una receta base y crea un nuevo lote de producción en estado inicial.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Lote de yogurt iniciado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o receta inexistente", content = @Content)
    })
    @PostMapping
    public ResponseEntity<YogurtBatch> startNewBatch(@RequestBody BatchDTO.StartBatchRequest request) {
        YogurtBatch batch = yogurtMakingService.startNewBatch(
            request.getRecipeId(), 
            request.getCustomMilkVolume(), 
            request.getCustomStarterAmount()
        );
        return new ResponseEntity<>(batch, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Iniciar fase de calentamiento", description = "Cambia el estado del lote a HEATING.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado", content = @Content)
    })
    @PostMapping("/{batchId}/heating")
    public ResponseEntity<YogurtBatch> startHeating(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startHeating(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Iniciar fase de inoculación", description = "Cambia el estado del lote a INOCULATING.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado", content = @Content)
    })
    @PostMapping("/{batchId}/inoculating")
    public ResponseEntity<YogurtBatch> startInoculating(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startInoculating(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Iniciar fase de incubación", description = "Cambia el estado del lote a INCUBATING.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado", content = @Content)
    })
    @PostMapping("/{batchId}/incubation")
    public ResponseEntity<YogurtBatch> startIncubation(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startIncubation(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Iniciar fase de refrigeración", description = "Cambia el estado del lote a REFRIGERATING.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado", content = @Content)
    })
    @PostMapping("/{batchId}/refrigeration")
    public ResponseEntity<YogurtBatch> startRefrigeration(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.startRefrigeration(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Completar lote", description = "Marca el lote de yogurt como COMPLETED.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lote completado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado", content = @Content)
    })
    @PostMapping("/{batchId}/complete")
    public ResponseEntity<YogurtBatch> completeBatch(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.completeBatch(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Marcar lote como fallido", description = "Cambia el estado del lote a FAILED y registra el motivo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lote marcado como fallido"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado", content = @Content)
    })
    @PostMapping("/{batchId}/fail")
    public ResponseEntity<YogurtBatch> markAsFailed(
            @PathVariable Long batchId, 
            @RequestBody BatchDTO.FailRequest request) {
        YogurtBatch batch = yogurtMakingService.markAsFailed(batchId, request.getReason());
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Listar todos los lotes", description = "Retorna una lista de todos los lotes, opcionalmente filtrados por estado.")
    @ApiResponse(responseCode = "200", description = "Lista de lotes obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<YogurtBatch>> getAllBatches(
            @RequestParam(required = false) YogurtBatch.BatchStatus status) {
        if (status != null) {
            return ResponseEntity.ok(yogurtMakingService.getBatchesByStatus(status));
        }
        return ResponseEntity.ok(yogurtMakingService.getAllBatches());
    }
    
    @Operation(summary = "Obtener lote por ID", description = "Busca y retorna los detalles de un lote específico de producción.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lote encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado", content = @Content)
    })
    @GetMapping("/{batchId}")
    public ResponseEntity<YogurtBatch> getBatch(@PathVariable Long batchId) {
        YogurtBatch batch = yogurtMakingService.getBatch(batchId);
        return ResponseEntity.ok(batch);
    }
    
    @Operation(summary = "Registrar temperatura", description = "Añade un registro de temperatura a un lote específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Temperatura registrada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de temperatura inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Lote no encontrado", content = @Content)
    })
    @PostMapping("/{batchId}/temperature")
    public ResponseEntity<Void> recordTemperature(
            @PathVariable Long batchId, 
            @RequestBody TemperatureRecordDTO request) {
        yogurtMakingService.recordTemperature(batchId, request.getTemperature(), request.getType());
        return ResponseEntity.ok().build();
    }
}