package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CeeLoGameTest {

    @Test
    public void testTakeBet() {
        GamblingPlayer gamblingPlayer = new GamblingPlayer("Billy");
        CeeLoGame ceeLoGame = new CeeLoGame(gamblingPlayer);

        gamblingPlayer.deposit(100);

        ceeLoGame.takeBet(100);

        Integer expected = 200;
        Integer actual = ceeLoGame.getPot();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetDiceRoll() {
        GamblingPlayer gamblingPlayer = new GamblingPlayer("Billy");
        CeeLoGame ceeLoGame = new CeeLoGame(gamblingPlayer);

        List<Integer> actual = ceeLoGame.getDiceRoll();
        Assert.assertEquals(3, actual.size());

        Assert.assertNotNull(actual.get(0));
        Assert.assertNotNull(actual.get(1));
        Assert.assertNotNull(actual.get(2));
    }

    @Test
    public void testCheckPlayer() {
        GamblingPlayer gamblingPlayer = new GamblingPlayer("Billy");
        CeeLoGame ceeLoGame = new CeeLoGame(gamblingPlayer);

        List<Integer> testArray1 = new ArrayList<>();
        testArray1.add(4);
        testArray1.add(5);
        testArray1.add(6);

        String expected = "Player";
        String actual = ceeLoGame.checkPlayer(testArray1);

        Assert.assertEquals(expected, actual);

        List<Integer> testArray2 = new ArrayList<>();
        testArray2.add(1);
        testArray2.add(1);
        testArray2.add(6);

        String expected1 = "Player";
        String actual1 = ceeLoGame.checkPlayer(testArray2);

        Assert.assertEquals(expected1, actual1);

    }

    @Test
    public void testCheckHouse() {
        GamblingPlayer gamblingPlayer = new GamblingPlayer("Billy");
        CeeLoGame ceeLoGame = new CeeLoGame(gamblingPlayer);

        List<Integer> testArray1 = new ArrayList<>();
        testArray1.add(4);
        testArray1.add(5);
        testArray1.add(6);

        String expected = "House";
        String actual = ceeLoGame.checkHouse(testArray1);

        Assert.assertEquals(expected, actual);

        List<Integer> testArray2 = new ArrayList<>();
        testArray2.add(1);
        testArray2.add(1);
        testArray2.add(6);

        String expected1 = "House";
        String actual1 = ceeLoGame.checkHouse(testArray2);

        Assert.assertEquals(expected1, actual1);
    }

    @Test
    public void testPrintScore() {
        GamblingPlayer gamblingPlayer = new GamblingPlayer("Billy");
        CeeLoGame ceeLoGame = new CeeLoGame(gamblingPlayer);

        String actual = ceeLoGame.printScore();
        String expected = "The player's score is " + 0;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPrintGameRules() {
        GamblingPlayer gamblingPlayer = new GamblingPlayer("Billy");
        CeeLoGame ceeLoGame = new CeeLoGame(gamblingPlayer);

        String actual = ceeLoGame.printGameRules();
        String expected = "======================================================================================= \n" +
                "Automatic Win: Player wins automatically if one of the following combinations are rolled: \n" +
                " 4-5-6, triples of same number, doubles with same number with a third showing a 6\n" +
                "Automatic Loss: Player loses automatically if one of the following combinations are rolled: \n" +
                "1-2-3, doubles with same number with third showing a 1\n" +
                "Re-roll: When none of the above combinations are rolled, player needs to re-roll until one of the above combinations are rolled.\n" +
                "=======================================================================================";

        Assert.assertEquals(expected, actual);
    }


}
