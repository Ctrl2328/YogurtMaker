package com.migueljaramillo.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto que representa un ingrediente para la preparación del yogurt")
public class IngredientDTO {
    
    @Schema(description = "Nombre del ingrediente", example = "Leche entera")
    private String name;
    
    @Schema(description = "Cantidad requerida del ingrediente", example = "1000.0")
    private Double quantity;
    
    @Schema(description = "Unidad de medida (ej. ml, gr, tazas)", example = "ml")
    private String unit;
    
    @Schema(description = "Indica si el ingrediente es opcional o estricto", example = "false")
    private Boolean optional;
}
