package io.zipcoder.casino;

import io.zipcoder.casino.card.utilities.CrapsWagerType;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class CrapsGameTest {

    @Test
    public void payoutTest() {
        String winningOutput = "Enter the type of wager you would like to make: \n"+
        "How much would you like to bet? \nBetting $50 on PASS\nRolling the dice....\n[ 6 ][ 5 ]\nYou now have $100 in your account\n";
        boolean nonwinningState = true;

        GamblingPlayer player = new GamblingPlayer("Bender");
        while(nonwinningState) {
            player.withdraw(player.getBalance());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream outboundMessaging = new PrintStream(outputStream);
            System.setOut(outboundMessaging);

            ByteArrayInputStream in = new ByteArrayInputStream(("PASS"+ System.lineSeparator() + "50").getBytes());
            System.setIn(in);

            player.deposit(50);
            CrapsGame craps = new CrapsGame(player);

            craps.nextTurn();

            if (outputStream.toString().equals(winningOutput)) {
                nonwinningState = false;
            }
            System.out.flush();
        }

        int actualReturn = player.getBalance();
        int expectedReturn = 100;

        assertEquals(expectedReturn, actualReturn);
    }

    @Test
    public void playTest() {
        //TODO
    }

    @Test
    public void nextTurnTest() {
        //TODO
    }

    @Test
    public void checkGameStateTest() {
        //TODO
    }

    @Test
    public void printGameStatusTest() {
        //TODO
    }

    @Test
    public void printGameRulesTest() {
        GamblingPlayer player = new GamblingPlayer("WhyNotZoidberg");
        CrapsGame craps = new CrapsGame(player);

        String actualOutput = craps.printGameRules();
        String expectedOutput = "==========Craps===========\n" + "Craps is a game where you roll a pair of dice multiple times\n" +
                "and bet on the outcome. The first round you can only make pass or don't pass\n" +
                "bets. The outcome of the first round becomes the \"point\" value\n" + "There are 5 possible wagers:\n"
                + "\tpass - On the first round, you are betting that the dice will equal 7 or 11\n" +
                "\tpast the first round, you are betting that the dice will hit the point value (2x Odds)\n\n" +
                "\tdontpass - On the first round, you are  betting that the dice will hit \"craps\"\n" +
                "\t(2, 3, or 12), past the first round, you are betting that the dice will hit a\n" +
                "\t7 before the point value (2x Odds)\n\n" + "\tfield - Past the first round, you are betting that the dice will hit a 2, 3, 4\n" +
                "\t9, 10, 11, 12 (3x Odds)\n\n" + "\tsevens - Past the first round, you are betting that the dice will roll a 7 (5x Odds)\n\n" +
                "\tanycraps - Past the first round, you are betting that the dice will hit craps (7x Odds)\n\n" +
                "Each round, enter the type bet you would like to make and the amount you want to bet. Enter\n" +
                "'none' when you are done betting for each round. All bets that are not Pass or Don't Pass\n" +
                "are cleared between rounds.\n\n Good Luck!\n";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void exitTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream outboundMessaging = new PrintStream(outputStream);
        System.setOut(outboundMessaging);

        GamblingPlayer player = new GamblingPlayer("Fry");
        CrapsGame craps = new CrapsGame(player);

        craps.exit();

        String actualResult = outputStream.toString();
        String expectedResult = "Thank you for playing craps\n";

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getWagerTypeTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("PASS").getBytes());
        System.setIn(in);

        GamblingPlayer player = new GamblingPlayer("Bender");
        CrapsGame craps = new CrapsGame(player);

        CrapsWagerType actualWager = craps.getWagerType();
        CrapsWagerType expectedWager = CrapsWagerType.PASS;
        assertEquals(expectedWager, actualWager);
    }

    @Test
    public void winWagerTest() {
        /* TODO - to be revisted. may not be possible as currently formatted.
        String winningOutput = "Enter the type of wager you would like to make: \n"+
                "How much would you like to bet? \n[ 6 5 ]\n";
        boolean nonwinningState = true;
        boolean returnValue = false;

        GamblingPlayer player = new GamblingPlayer("Bender");
        while(nonwinningState) {
            player.withdraw(player.getBalance());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream outboundMessaging = new PrintStream(outputStream);
            System.setOut(outboundMessaging);

            ByteArrayInputStream in = new ByteArrayInputStream(("PASS"+ System.lineSeparator() + "50").getBytes());
            System.setIn(in);

            player.deposit(50);
            CrapsGame craps = new CrapsGame(player);

            craps.nextTurn();

            if (outputStream.toString().equals(winningOutput)) {
                nonwinningState = false;
            }
            returnValue = craps.winWager(CrapsWagerType.PASS);
            System.out.flush();
        }

        assertTrue(returnValue);*/
    }
}