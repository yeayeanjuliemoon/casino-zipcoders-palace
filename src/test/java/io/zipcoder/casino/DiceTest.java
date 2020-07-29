package io.zipcoder.casino;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiceTest {

    @Test
    public void testDiceConstructor(){
        Integer expected = 3;
        Dice dice = new Dice(expected);

        Integer actual = dice.getDieValues().size();

        assertEquals(expected, actual);
    }

    @Test
    public void testDiceGetValues(){
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 1, 1));
        Dice dice = new Dice(3);

        List<Integer> actual = dice.getDieValues();

        assertEquals(expected, actual);
    }

    @Test
    public void testDiceRoll(){
        int numDice = 3;
        Dice dice = new Dice(numDice);
        dice.rollDice();

        List<Integer> result = dice.getDieValues();

        for(int i = 0; i < numDice; i++){
            assertTrue((result.get(i) >= 1) || (result.get(i) <= 6));
        }
    }




}
