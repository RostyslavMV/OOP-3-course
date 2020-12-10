package com.rmv.oop.lab1.main.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.rmv.oop.lab1.main.model.Diamond;
import com.rmv.oop.lab1.main.model.Gem;
import com.rmv.oop.lab1.main.model.Sapphire;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class GemEvaluationService {
    @Value("${diamondPrice}")
    private BigDecimal diamondPrice;

    @Value("${sapphirePrice}")
    private BigDecimal sapphirePrice;

    @Value("${pearlPrice}")
    private BigDecimal pearlPrice;

    public BigDecimal evaluatePrice(Gem gem) {
        BigDecimal resultPrice  = getBasePrice(gem);
        resultPrice = resultPrice.multiply(gem.getWeight()).setScale(2, RoundingMode.HALF_UP);
        resultPrice = resultPrice.multiply(gem.getClarity()).setScale(2, RoundingMode.HALF_UP);
        gem.setPrice(resultPrice);
        return resultPrice;
    }

    private BigDecimal getBasePrice(Gem gem){
        if (gem.getClass() == Diamond.class){
            return  diamondPrice;
        }
        else if (gem.getClass() == Sapphire.class){
            return sapphirePrice;
        }
        else return pearlPrice;
    }
}
