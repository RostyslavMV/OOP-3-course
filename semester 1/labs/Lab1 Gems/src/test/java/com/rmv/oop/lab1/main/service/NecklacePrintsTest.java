package com.rmv.oop.lab1.main.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.rmv.oop.lab1.main.model.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class NecklacePrintsTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void print() {

        Gem gem1 = new Diamond(DiamondColor.BLACK, new BigDecimal("50.0"), new BigDecimal("50.0"));
        Gem gem2 = new Pearl(PearlColor.BLUE, new BigDecimal("20.0"), new BigDecimal("60.0"));

        BigDecimal price = new BigDecimal("500.0");
        BigDecimal weight = new BigDecimal("110.0");

        List<Gem> gems = Arrays.asList(gem1, gem2);
        Necklace necklace = new Necklace(gems, price, weight);
        necklace.print();
        String outContentString = outputStreamCaptor.toString();

        assertTrue(outContentString.contains("500.0"));
        assertTrue(outContentString.contains("110.0"));
        assertTrue(outContentString.contains("Diamond"));
        assertTrue(outContentString.contains("Pearl"));
    }

    @Test
    public void printWithClarity() {
        Gem gem1 = new Diamond(DiamondColor.BLACK, new BigDecimal("50.0"), new BigDecimal("50.0"));
        Gem gem2 = new Pearl(PearlColor.BLUE, new BigDecimal("20.0"), new BigDecimal("60.0"));
        Gem gem3 = new Sapphire(SapphireColor.BLUE, new BigDecimal("90.0"), new BigDecimal("60.0"));

        BigDecimal price = new BigDecimal("500.0");
        BigDecimal weight = new BigDecimal("110.0");

        List<Gem> gems = Arrays.asList(gem1, gem2,gem3);
        Necklace necklace = new Necklace(gems, price, weight);
        necklace.printWithClarity(new BigDecimal("30.0"), new BigDecimal("70.0"));
        String outContentString = outputStreamCaptor.toString();

        assertTrue(outContentString.contains("Diamond"));
        assertFalse(outContentString.contains("Pearl"));
        assertFalse(outContentString.contains("Sapphire"));
    }

    @Test
    public void printWithClarityBadArguments() {
        Gem gem1 = new Diamond(DiamondColor.BLACK, new BigDecimal("50.0"), new BigDecimal("50.0"));
        Gem gem2 = new Pearl(PearlColor.BLUE, new BigDecimal("20.0"), new BigDecimal("60.0"));

        BigDecimal price = new BigDecimal("500.0");
        BigDecimal weight = new BigDecimal("110.0");

        List<Gem> gems = Arrays.asList(gem1, gem2);
        Necklace necklace = new Necklace(gems, price, weight);

        assertThrows(UnsupportedOperationException.class, () -> necklace.printWithClarity(new BigDecimal(-5), new BigDecimal("70.0")));
        assertThrows(UnsupportedOperationException.class, () -> necklace.printWithClarity(new BigDecimal("30.0"), new BigDecimal("170.0")));
    }

}
