package com.migueljaramillo.demo.dto;

import java.util.List;
import com.migueljaramillo.demo.domain.model.Recipe;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos para la creación y actualización de recetas")
public class RecipeDTO {
    
    @Schema(description = "Nombre de la receta de yogurt", example = "Yogurt Griego Tradicional")
    private String name;
    
    @Schema(description = "Descripción breve del resultado de la receta", example = "Yogurt espeso y alto en proteínas")
    private String description;
    
    @Schema(description = "Volumen de leche por defecto en mililitros", example = "1000.0")
    private Double defaultMilkVolume;
    
    @Schema(description = "Cantidad de cultivo láctico o iniciador", example = "50.0")
    private Double defaultStarterAmount;
    
    @Schema(description = "Temperatura ideal para calentar la leche (°C)", example = "85.0")
    private Double heatingTemperature;
    
    @Schema(description = "Duración del calentamiento en minutos", example = "30")
    private Integer heatingDuration;
    
    @Schema(description = "Temperatura para añadir el cultivo (°C)", example = "43.0")
    private Double inoculationTemperature;
    
    @Schema(description = "Temperatura a mantener durante la incubación (°C)", example = "40.0")
    private Double incubationTemperature;
    
    @Schema(description = "Tiempo mínimo de incubación en minutos", example = "480")
    private Integer minIncubationTime;
    
    @Schema(description = "Tiempo máximo de incubación en minutos", example = "720")
    private Integer maxIncubationTime;
    
    @Schema(description = "Tiempo recomendado de refrigeración final en minutos", example = "240")
    private Integer refrigerationTime;
    
    @Schema(description = "Nivel de dificultad de la preparación", example = "MEDIUM")
    private Recipe.DifficultyLevel difficulty;
    
    @Schema(description = "Consejos adicionales para el éxito de la receta", example = "No revolver bruscamente después de inocular.")
    private String tips;
    
    @Schema(description = "Lista de ingredientes necesarios")
    private List<IngredientDTO> ingredients;
}