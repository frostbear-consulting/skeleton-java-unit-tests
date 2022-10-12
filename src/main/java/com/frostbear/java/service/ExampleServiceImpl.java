package com.frostbear.java.service;

import com.frostbear.java.dto.CalculationStateDTO;
import com.frostbear.java.repo.ExampleRepository;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class ExampleServiceImpl implements ExampleService {

    protected ExampleRepository repo;

    @Override
    public void multiply(BigDecimal by) {
        if (null == by) {
            throw new IllegalArgumentException("by most not be null");
        }

        var current = this.repo.getCalculationState()
                .orElseGet(() -> new CalculationStateDTO(BigDecimal.ZERO, null));

        var result = current.getResult().multiply(by);

        this.repo.saveCalculation(result);
    }
}