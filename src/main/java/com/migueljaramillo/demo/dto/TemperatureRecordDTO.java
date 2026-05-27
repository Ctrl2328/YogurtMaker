package com.migueljaramillo.demo.dto;

import com.migueljaramillo.demo.domain.model.TemperatureLog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Objeto de transferencia de datos para el reporte manual o automático de lecturas térmicas")
public class TemperatureRecordDTO {
    
    @Schema(description = "Valor numérico de la temperatura medida en grados Celsius", example = "43.2")
    private Double temperature;
    
    @Schema(description = "Fase del proceso a la que corresponde la lectura (ej. HEATING, INCUBATION)", example = "INCUBATION")
    private TemperatureLog.LogType type;
}

