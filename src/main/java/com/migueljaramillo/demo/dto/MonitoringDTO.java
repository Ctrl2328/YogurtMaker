package com.migueljaramillo.demo.dto;

import java.util.Map;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

public class MonitoringDTO {
    
    @Data
    @Builder
    @Schema(description = "Resumen del control estadístico de temperaturas de un lote específico")
    public static class TemperatureSummary {
        @Schema(description = "Temperatura actual registrada en tiempo real (°C)", example = "42.5")
        private Double currentTemperature;
        
        @Schema(description = "Temperatura máxima alcanzada durante todo el proceso (°C)", example = "85.0")
        private Double maximumTemperature;
        
        @Schema(description = "Temperatura mínima registrada (°C)", example = "4.0")
        private Double minimumTemperature;
        
        @Schema(description = "Promedio aritmético de las temperaturas registradas en la fase actual (°C)", example = "41.2")
        private Double averageTemperature;
    }
    
    @Data
    @Builder
    @Schema(description = "Métricas generales consolidadas para el panel de control (Dashboard)")
    public static class Dashboard {
        @Schema(description = "Mapa con el conteo de lotes agrupados por su estado actual (PREPARING, HEATING, etc.)")
        private Map<String, Long> batchCounts;
        
        @Schema(description = "Cantidad total de lotes que se encuentran en proceso activo de fabricación", example = "5")
        private Long activeBatchesCount;
        
        @Schema(description = "Número de lotes que finalizaron exitosamente el día de hoy", example = "3")
        private Integer completedToday;
    }
}
