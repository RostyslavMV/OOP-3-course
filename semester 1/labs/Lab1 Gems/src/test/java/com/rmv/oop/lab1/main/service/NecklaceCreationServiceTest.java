package com.rmv.oop.lab1.main.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.rmv.oop.lab1.main.model.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class NecklaceCreationServiceTest {
    @Mock
    private GemMiningService gemMiningService;

    @Mock
    private GemEvaluationService gemEvaluationService;

    @InjectMocks
    private NecklaceCreationService necklaceCreationService;

    @Test
    void getNecklacePrice() {
        Gem gem1 = new Diamond(DiamondColor.BLACK, new BigDecimal("50.0"), new BigDecimal("50.0"));
        Gem gem2 = new Pearl(PearlColor.BLUE, new BigDecimal("20.0"), new BigDecimal("60.0"));
        final BigDecimal price1 = new BigDecimal("10.0");
        final BigDecimal price2 = new BigDecimal("20.0");

        when(gemEvaluationService.evaluatePrice(gem1)).thenReturn(price1);
        when(gemEvaluationService.evaluatePrice(gem2)).thenReturn(price2);
        List<Gem> gems = Arrays.asList(gem1, gem2);
        BigDecimal price = necklaceCreationService.getNecklacePrice(gems);
        assertEquals(price1.add(price2), price);
    }

    @Test
    void getNecklaceWeight() {
        BigDecimal weight1 = new BigDecimal("20.0");
        BigDecimal weight2 = new BigDecimal("70.0");
        Gem gem1 = new Diamond(DiamondColor.BLACK, new BigDecimal("50.0"), weight1);
        Gem gem2 = new Pearl(PearlColor.BLUE, new BigDecimal("20.0"), weight2);

        List<Gem> gems = Arrays.asList(gem1, gem2);
        BigDecimal weight = necklaceCreationService.getNecklaceWeight(gems);
        assertEquals(weight1.add(weight2), weight);
    }

    @Test
    void createNecklace() {
        Gem gem = new Diamond(DiamondColor.BLACK, new BigDecimal("50.0"), new BigDecimal("50.0"));
        final BigDecimal price = new BigDecimal("10.0");
        gem.setPrice(price);
        when(gemMiningService.mineGem()).thenReturn(gem);
        when(gemEvaluationService.evaluatePrice(gem)).thenReturn(price);
        Necklace necklace = necklaceCreationService.createNecklace(10);

        assertEquals(10, necklace.getGems().size());
        necklace.getGems().forEach(
                minedGem -> assertEquals(gem, minedGem)
        );
    }

}
