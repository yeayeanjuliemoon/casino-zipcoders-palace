package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiceTest {

    @Test
    public void testDiceConstructor(){
        Dice dice = new Dice(3);

        List<Integer> actual = dice.getDiceValues();

        Assert.assertEquals(3, actual.size());
    }

    @Test
    public void testRollDice() {
        Dice dice = new Dice(3);
        dice.rollDice();

        List<Integer> actual = dice.getDiceValues();

        Assert.assertNotNull(actual.get(0));
        Assert.assertNotNull(actual.get(1));
        Assert.assertNotNull(actual.get(2));
    }

    }

