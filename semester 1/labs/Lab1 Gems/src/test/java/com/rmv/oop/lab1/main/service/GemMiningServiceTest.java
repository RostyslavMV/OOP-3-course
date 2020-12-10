package com.rmv.oop.lab1.main.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import com.rmv.oop.lab1.main.model.Diamond;
import com.rmv.oop.lab1.main.model.Gem;
import com.rmv.oop.lab1.main.model.Pearl;
import com.rmv.oop.lab1.main.model.Sapphire;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class GemMiningServiceTest {

    @InjectMocks
    private GemMiningService gemMiningService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(gemMiningService, "maxWeight", new BigDecimal("120.0"));
        ReflectionTestUtils.setField(gemMiningService, "maxClarity", new BigDecimal("100.0"));
    }


    @Test
    void mine100Gems() {
        for (int i =0; i<100; i++){
            Gem gem = gemMiningService.mineGem();
            assertNotNull(gem.getClarity());
            assertNotNull(gem.getWeight());
            if (gem.getClass() == Diamond.class) {
                assertNotNull(((Diamond) gem).getColor());
            } else if (gem.getClass() == Pearl.class) {
                assertNotNull(((Pearl) gem).getColor());
            } else if (gem.getClass() == Sapphire.class) {
                assertNotNull(((Sapphire) gem).getColor());
            }
        }
    }
}
