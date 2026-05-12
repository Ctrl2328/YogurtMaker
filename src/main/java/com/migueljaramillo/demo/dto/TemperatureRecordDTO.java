package com.migueljaramillo.demo.dto;

import com.migueljaramillo.demo.domain.model.TemperatureLog;

import lombok.Data;

@Data
public class TemperatureRecordDTO {
    private Double temperature;
    private TemperatureLog.LogType type;
}

