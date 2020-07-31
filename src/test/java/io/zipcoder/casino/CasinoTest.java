package io.zipcoder.casino;

import static org.junit.Assert.*;

import io.zipcoder.casino.card.games.Blackjack;
import org.junit.Rule;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CasinoTest {


    @Test
    public void createPlayerTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("no").getBytes());
        System.setIn(in);

        Casino casino = new Casino();
        String givenName = "ted";
        Player player = casino.createPlayer(givenName);

        String actualName = player.toString();

        assertEquals(givenName, actualName);
    }

    @Test
    public void selectGameTest(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream outboundMessaging = new PrintStream(outputStream);
        System.setOut(outboundMessaging);

        ByteArrayInputStream in = new ByteArrayInputStream(("0"+ System.lineSeparator() + "no").getBytes());
        System.setIn(in);

        Casino casino = new Casino();
        Player player = new GamblingPlayer("Ned");
        Blackjack blackjack = new Blackjack(player);
        casino.selectGame(blackjack);

        String expectedOutput = "* The goal of the game is to beat the dealer's hand without going over 21\n" +
                "* You and the dealer start with two cards. One of the dealer's cards is hidden until their turn.\n" +
                "* You can ask for additional cards until you want to stop or you go over 21.\n" +
                "* Cards Two through Ten are face value. Face cards are worth 10. Aces are worth 1 or 11.";
        System.out.flush();
        String actualOutput = outputStream.toString();
        actualOutput = actualOutput.substring(0,336);

        assertEquals(expectedOutput, actualOutput);



    }

    @Test
    public void printCasinoMenuTest() {
        Casino casino = new Casino();

        String actualResult = casino.printCasinoMenu();
        String expectedResult = "******* ♖ ZipCoder's Palace ♖  *******\n" +
                "******* Please Enter A Number: *******\n"+
                "* 1: Login ~~~~~~~~~~~~~~~~~~~~~~~ *\n"+
                "* 2: Play GoFish ~~~~~~~~~~~~~~~~~ *\n"+
                "* 3: Play BlackJack ~~~~~~~~~~~~~~ *\n"+
                "* 4: Play Craps ~~~~~~~~~~~~~~~~~~ *\n"+
                "* 5: Play CeeLo ~~~~~~~~~~~~~~~~~~ *\n"+
                "* 6: Logout ~~~~~~~~~~~~~~~~~~~~~~ *\n"+
                "* 7: Leave ZipCoder's Palace ~~~~~ *\n"+
                "**** You must be logged in to play a game! ****\n";

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void playerLoginTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("no").getBytes());
        System.setIn(in);

        Casino casino = new Casino();
        String givenName = "GamblingHomer";

        casino.playerLogin(givenName);
        String actualPlayers = casino.printPlayers();
        String expectedPlayers = "Current Players:\n    "+givenName+"\n";

        assertEquals(expectedPlayers, actualPlayers);
    }

    @Test
    public void playerLogoutTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("no").getBytes());
        System.setIn(in);

        Casino casino = new Casino();
        String givenName = "GamblingMoe";

        casino.playerLogin(givenName);

        casino.playerLogout();
        Boolean actual = casino.checkIfActivePlayer();

        assertFalse(actual);
    }

    @Test
    public void printPlayersTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("no").getBytes());
        System.setIn(in);

        Casino casino = new Casino();
        String givenName = "NonGamblingNed";

        casino.playerLogin(givenName);
        String actualPlayers = casino.printPlayers();
        String expectedPlayers = "Current Players:\n    "+givenName+"\n";

        assertEquals(expectedPlayers, actualPlayers);
    }

    @Test
    public void parseMenuInputTest(){
        Casino casino = new Casino();

        //casino.parseMenuInput();

    }

    @Test
    public void checkIfActivePlayersTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("no").getBytes());
        System.setIn(in);

        Casino casino = new Casino();
        String givenName = "Bart";
        casino.playerLogin(givenName);

        Boolean actual = casino.checkIfActivePlayer();

        assertTrue(actual);
    }


}