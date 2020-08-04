package io.zipcoder.casino;

import io.zipcoder.casino.card.utilities.CeeLoConstant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.isA;

public class CeeLoGameTest {

    GamblingPlayer gamblingPlayer;
    CeeLoGame ceeLoGame;
    Console console;

    @Before
    public void setup() {
        gamblingPlayer = new GamblingPlayer("Billy");
        ceeLoGame = new CeeLoGame(gamblingPlayer);
        console = Mockito.mock(Console.class);
    }

    @Test
    public void testGetDiceRoll() {
        List<Integer> actual = ceeLoGame.getDiceRoll();
        Assert.assertEquals(3, actual.size());

        Assert.assertNotNull(actual.get(0));
        Assert.assertNotNull(actual.get(1));
        Assert.assertNotNull(actual.get(2));
    }

    @Test
    public void testCheckRoll() {
        List<Integer> values = new ArrayList<>();
        String expected = "Player";

        values.add(4);
        values.add(5);
        values.add(6);

        String actual = ceeLoGame.checkRoll(values, "Player", "House");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCheckForWin() {
        Assert.assertTrue(ceeLoGame.checkForWin("456"));
        Assert.assertTrue(ceeLoGame.checkForWin("611"));
    }

    @Test
    public void testCheckForLoss() {
        Assert.assertTrue(ceeLoGame.checkForLoss("123"));
        Assert.assertTrue(ceeLoGame.checkForLoss("144"));
    }

    @Test
    public void testPlayAgain() {
        ceeLoGame.setConsole(this.console);
        Mockito.doReturn("yes").when(this.console).getStringInput(isA(String.class));
        Assert.assertTrue(ceeLoGame.playAgain(false));
    }

    @Test
    public void testTakeBet() {
        ceeLoGame.setConsole(this.console);

        Integer expected = 200;
        Mockito.doReturn(expected).when(this.console).getIntegerInput(isA(String.class));

        ceeLoGame.takeBet();
        Integer actual = ceeLoGame.getBet();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetPot() {
        Integer bet = 200;
        Integer expected = 400;

        gamblingPlayer.deposit(500);
        ceeLoGame.setBet(bet);
        ceeLoGame.setPot();

        Integer actual = ceeLoGame.getPot();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testPrintScore() {
        String actual = ceeLoGame.printScore();
        String expected = "0";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPayout() {
        gamblingPlayer.deposit(300);
        ceeLoGame.setBet(100);
        ceeLoGame.setPot();

        ceeLoGame.payout();

        Integer expectedPot = 0;
        Assert.assertEquals(expectedPot, ceeLoGame.getPot());

        Integer expectedScore = 1;
        Assert.assertEquals(expectedScore, ceeLoGame.getScore());
    }

    @Test
    public void testPrintGameRules() {
        String actual = ceeLoGame.printGameRules();
        String expected = CeeLoConstant.CEELO_RULES;

        Assert.assertEquals(expected, actual);
    }

}
