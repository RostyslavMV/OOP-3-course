package com.rmv.oop.lab1.main.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
public class Gem {
    private BigDecimal clarity;
    private BigDecimal weight; //In carats
    private BigDecimal price;

    public Gem(BigDecimal clarity, BigDecimal weight) {
        this.clarity = clarity;
        this.weight = weight;
    }
}
