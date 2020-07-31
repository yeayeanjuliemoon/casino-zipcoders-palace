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
        "How much would you like to bet? \n[ 6 5 ]\n";
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
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream outboundMessaging = new PrintStream(outputStream);
        System.setOut(outboundMessaging);

        GamblingPlayer player = new GamblingPlayer("WhyNotZoidberg");
        CrapsGame craps = new CrapsGame(player);

        craps.printGameRules();

        String actualOutput = outputStream.toString();
        String expectedOutput = "THA RULEZ";

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