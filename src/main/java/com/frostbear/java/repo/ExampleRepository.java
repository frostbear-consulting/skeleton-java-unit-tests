package com.frostbear.java.repo;

import com.frostbear.java.dto.CalculationStateDTO;

import java.math.BigDecimal;
import java.util.Optional;

public interface ExampleRepository {

    Optional<CalculationStateDTO> getCalculationState();

    void saveCalculation(BigDecimal newResult);

}