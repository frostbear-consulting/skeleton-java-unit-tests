package com.frostbear.java.service;

import com.frostbear.java.dto.CalculationStateDTO;
import com.frostbear.java.repo.ExampleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExampleServiceImplTest {

    @DisplayName("Throws if `by` is null")
    @Test
    void testThrowsIfNullGiven() {
        var sut = new ExampleServiceImpl(null);

        assertThatThrownBy(() -> sut.multiply(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Correctly calculates without state")
    @Test
    void testCalculatesCorrectlyWithNoState() {
        // GIVEN
        var mockRepo = Mockito.mock(ExampleRepository.class);

        Mockito.when(mockRepo.getCalculationState()).thenReturn(Optional.empty());

        // WHEN
        var sut = new ExampleServiceImpl(mockRepo);
        sut.multiply(BigDecimal.TEN);

        // THEN
        var captor = ArgumentCaptor.forClass(BigDecimal.class);
        Mockito.verify(mockRepo).saveCalculation(captor.capture());

        assertThat(captor.getValue()).isEqualTo(BigDecimal.ZERO);
    }

    @DisplayName("Correctly calculates with state")
    @Test
    void testCalculatesCorrectWithState() {
        // GIVEN
        var base = BigDecimal.valueOf(8741.44);
        var multiplier = BigDecimal.valueOf(981.34);
        var mockRepo = Mockito.mock(ExampleRepository.class);

        Mockito.when(mockRepo.getCalculationState())
                .thenReturn(Optional.of(new CalculationStateDTO(base, Instant.now())));

        // WHEN
        var sut = new ExampleServiceImpl(mockRepo);
        sut.multiply(multiplier);

        // THEN
        var captor = ArgumentCaptor.forClass(BigDecimal.class);
        Mockito.verify(mockRepo).saveCalculation(captor.capture());

        assertThat(captor.getValue()).isEqualTo(base.multiply(multiplier));
    }
}