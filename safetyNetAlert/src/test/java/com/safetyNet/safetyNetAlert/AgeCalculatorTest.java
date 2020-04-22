package com.safetyNet.safetyNetAlert;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AgeCalculatorTest {

    @Test
    void calculateAge() {
        LocalDate birthDate = LocalDate.of(2012, 02, 18);
        int actual = AgeCalculator.calculateAge(birthDate, LocalDate.of(2020, 04, 16));
        Assert.assertEquals(8, actual);
    }
}