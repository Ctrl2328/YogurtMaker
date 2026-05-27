package com.migueljaramillo.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class BatchDTO {
    
    @Data
    @Schema(description = "Objeto de petición para iniciar la producción de un nuevo lote de yogurt")
    public static class StartBatchRequest {
        
        @Schema(description = "Identificador único de la receta base a utilizar", example = "1")
        private Long recipeId;
        
        @Schema(description = "Volumen personalizado de leche en mililitros (opcional, si no se envía usa el de la receta)", example = "2000.0")
        private Double customMilkVolume;
        
        @Schema(description = "Cantidad personalizada de cultivo láctico (opcional, si no se envía usa el de la receta)", example = "100.0")
        private Double customStarterAmount;
    }
    
    @Data
    @Schema(description = "Objeto de petición para registrar y justificar el fallo en un lote de producción")
    public static class FailRequest {
        
        @Schema(description = "Motivo detallado por el cual el lote de yogurt fue cancelado o marcado como fallido", example = "Corte de energía durante la fase de incubación")
        private String reason;
    }
}