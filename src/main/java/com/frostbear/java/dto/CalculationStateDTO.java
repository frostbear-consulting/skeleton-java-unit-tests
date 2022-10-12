package com.frostbear.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculationStateDTO {

    protected BigDecimal result;
    protected Instant calculatedAt;

}