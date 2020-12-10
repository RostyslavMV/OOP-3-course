package com.rmv.oop.lab1.main.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Necklace {
    private List<Gem> gems;
    private BigDecimal price;
    private BigDecimal weight;

    public void print() {
        System.out.println("Necklace price = " + price);
        System.out.println("Necklace weight = " + weight);
        System.out.println("Necklace contains these gems:");
        for (Gem gem : gems) {
            System.out.println(gem);
        }
    }

    public void printWithClarity(BigDecimal minClarity, BigDecimal maxClarity) {
        if (minClarity.compareTo(BigDecimal.ZERO) < 0) {
            throw new UnsupportedOperationException("minClarity less than zero");
        }
        if (maxClarity.compareTo(new BigDecimal("100")) > 0) {
            throw new UnsupportedOperationException("maxClarity is more than zero");
        }
        System.out.println("Gems with clarity between " + minClarity + " and " + maxClarity);
        for (Gem gem : gems) {
            if (gem.getClarity().compareTo(minClarity) > 0 && gem.getClarity().compareTo(maxClarity) < 0) {
                System.out.println(gem);
            }
        }
    }
}
