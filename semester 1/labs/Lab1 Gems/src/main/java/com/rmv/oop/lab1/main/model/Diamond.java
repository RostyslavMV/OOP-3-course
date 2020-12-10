package com.rmv.oop.lab1.main.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString(callSuper = true)
@Getter
@Setter
public class Diamond extends Gem {
    private DiamondColor color;

    public Diamond(DiamondColor color, BigDecimal clarity, BigDecimal weight) {
        super(clarity, weight);
        this.color = color;
    }
}
