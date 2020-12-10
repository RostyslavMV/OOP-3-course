package com.rmv.oop.lab1.main.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import com.rmv.oop.lab1.main.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class GemEvaluationServiceTest {

    private BigDecimal diamondPrice = new BigDecimal("5.0");

    private BigDecimal sapphirePrice= new BigDecimal("4.0");

    private BigDecimal pearlPrice= new BigDecimal("2.0");

    @InjectMocks
    private GemEvaluationService gemEvaluationService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(gemEvaluationService, "diamondPrice", diamondPrice);
        ReflectionTestUtils.setField(gemEvaluationService, "sapphirePrice", sapphirePrice);
        ReflectionTestUtils.setField(gemEvaluationService, "pearlPrice", pearlPrice);
    }

    @Test
    public void evaluatePrice() {
        BigDecimal clarity = new BigDecimal("50.0");
        BigDecimal weight = new BigDecimal("50.0");

        DiamondColor diamondColor = DiamondColor.BLACK;
        PearlColor pearlColor = PearlColor.BLUE;
        SapphireColor sapphireColor = SapphireColor.GREEN;

        Gem diamond = new Diamond(diamondColor, clarity, weight);
        Gem sapphire = new Sapphire(sapphireColor, clarity, weight);
        Gem pearl = new Pearl(pearlColor, clarity, weight);

        BigDecimal diamondGemPrice = gemEvaluationService.evaluatePrice(diamond);
        BigDecimal sapphireGemPrice = gemEvaluationService.evaluatePrice(sapphire);
        BigDecimal pearlGemPrice = gemEvaluationService.evaluatePrice(pearl);

        BigDecimal diamondExpectedPrice = diamondPrice;
        diamondExpectedPrice = diamondExpectedPrice.multiply(weight).setScale(2, RoundingMode.HALF_UP);
        diamondExpectedPrice = diamondExpectedPrice.multiply(clarity).setScale(2, RoundingMode.HALF_UP);

        BigDecimal sapphireExpectedPrice = sapphirePrice;
        sapphireExpectedPrice = sapphireExpectedPrice.multiply(weight).setScale(2, RoundingMode.HALF_UP);
        sapphireExpectedPrice = sapphireExpectedPrice.multiply(clarity).setScale(2, RoundingMode.HALF_UP);

        BigDecimal pearlExpectedPrice = pearlPrice;
        pearlExpectedPrice = pearlExpectedPrice.multiply(weight).setScale(2, RoundingMode.HALF_UP);
        pearlExpectedPrice = pearlExpectedPrice.multiply(clarity).setScale(2, RoundingMode.HALF_UP);

        assertEquals(diamondGemPrice,diamondExpectedPrice);
        assertEquals(sapphireGemPrice,sapphireExpectedPrice);
        assertEquals(pearlGemPrice,pearlExpectedPrice);
    }

}
