package com.rmv.oop.lab1.main.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.rmv.oop.lab1.main.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Service
public class GemMiningService {
    @Value("${maxWeight}")
    private BigDecimal maxWeight;
    @Value("${maxClarity}")
    private BigDecimal maxClarity;

    private final Random random = new Random();

    public Gem mineGem() {
        int gemType = random.nextInt(3);
        BigDecimal clarity = getClarity();
        BigDecimal weight = getWeight();
        switch (gemType) {
            case 0:
                DiamondColor diamondColor = DiamondColor.values()
                        [random.nextInt(DiamondColor.values().length)];
                return new Diamond(diamondColor, clarity, weight);
            case 1:
                PearlColor pearlColor = PearlColor.values()
                        [random.nextInt(PearlColor.values().length)];
                return new Pearl(pearlColor, clarity, weight);
            default:
                SapphireColor sapphireColor = SapphireColor.values()
                        [random.nextInt(SapphireColor.values().length)];
                return new Sapphire(sapphireColor, clarity, weight);
        }
    }

    private BigDecimal getWeight() {
        return BigDecimal.valueOf(Math.random()).multiply(maxWeight).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getClarity() {
        return BigDecimal.valueOf(Math.random()).multiply(maxClarity).setScale(2, RoundingMode.HALF_UP);
    }
}
