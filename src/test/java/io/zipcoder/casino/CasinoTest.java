package io.zipcoder.casino;

import static org.junit.Assert.*;

import io.zipcoder.casino.card.games.Blackjack;
import io.zipcoder.casino.card.games.GoFish;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CasinoTest {

    Casino casino;
    String givenName = "aName";

    @Before
    public void init(){
    casino = new Casino();
    }


    @Test
    public void createPlayerOptionsTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("no").getBytes());
        System.setIn(in);
        casino = new Casino();

        Player player = casino.createPlayerOptions(givenName);

        String actualName = player.toString();

        assertEquals(givenName, actualName);
    }

    @Test
    public void createPlayerOptionsGamblingTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("yes\n40\n").getBytes());
        System.setIn(in);
        casino = new Casino();

        Player player = casino.createPlayerOptions(givenName);

        int actualBalance = ((GamblingPlayer)player).getBalance();

        assertEquals(40, actualBalance);
    }

    @Test
    public void createPlayerTest(){
        Player player = casino.createPlayer(givenName);
        String actualName = player.toString();

        assertEquals(givenName, actualName);
    }

    @Test
    public void createGamblingPlayerTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("40\n").getBytes());
        System.setIn(in);
        casino = new Casino();

        GamblingPlayer player = casino.createGamblingPlayer(givenName);

        int actualBalance = player.getBalance();

        assertEquals(40, actualBalance);
    }

    /*@Test TODO - there must be a better way to do this
    public void selectGameTest(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream outboundMessaging = new PrintStream(outputStream);
        System.setOut(outboundMessaging);

        ByteArrayInputStream in = new ByteArrayInputStream(("0"+ System.lineSeparator() + "no").getBytes());
        System.setIn(in);

        Player player = new GamblingPlayer(givenName);
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

    } */

    @Test
    public void printCasinoMenuTest() {
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
        casino.createPlayer(givenName);
        casino.playerLogin(givenName);
        String expectedPlayers = "Current Players:\n    "+givenName+"\n";

        String actualPlayers = casino.printPlayers();

        assertEquals(expectedPlayers, actualPlayers);
    }

    @Test
    public void isAPlayerTest(){
        casino.createPlayer(givenName);
        int expected = 0;

        int actual = casino.isAPlayer(givenName);

        assertEquals(expected, actual);
    }

    @Test
    public void isAPlayerNOTTest(){
        String notAPlayer = "NotAPlayer";
        int expected = -1;

        int actual = casino.isAPlayer(notAPlayer);

        assertEquals(expected, actual);
    }

    @Test
    public void getGamblingStatusTest(){
        Boolean actual = casino.getGamblingStatus("no");

        assertFalse(actual);
    }

    @Test
    public void getGamblingStatusYesTest(){
        Boolean actual = casino.getGamblingStatus("yes");

        assertTrue(actual);
    }

    @Test
    public void parseGamblingStatusNoTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("NO\n").getBytes());
        System.setIn(in);
        casino = new Casino();

        Boolean actual = casino.parseGamblingStatus();

        assertFalse(actual);
    }

    @Test
    public void parseGamblingStatusYesTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("yES\n").getBytes());
        System.setIn(in);
        casino = new Casino();

        Boolean actual = casino.parseGamblingStatus();

        assertTrue(actual);
    }

    @Test
    public void parseGamblingStatusBadInputTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("BadInput\nno\n").getBytes());
        System.setIn(in);
        casino = new Casino();

        Boolean actual = casino.parseGamblingStatus();

        assertFalse(actual);
    }
    @Test
    public void playerLogoutTest(){
        casino.createPlayer(givenName);
        casino.playerLogin(givenName);

        casino.playerLogout();
        Boolean actual = casino.checkIfActivePlayer();

        assertFalse(actual);
    }

    @Test
    public void playerLogoutNoPlayerTest(){
        Boolean actual = casino.playerLogout();

        assertFalse(actual);
    }

    @Test
    public void printPlayersTest(){
        casino.createPlayer(givenName);
        casino.playerLogin(givenName);

        String actualPlayers = casino.printPlayers();
        String expectedPlayers = "Current Players:\n    "+givenName+"\n";

        assertEquals(expectedPlayers, actualPlayers);
    }

    @Test
    public void parseMenuInput1Test(){
        ByteArrayInputStream in = new ByteArrayInputStream(("1\naName\nno\n").getBytes());
        System.setIn(in);
        casino = new Casino();

        casino.parseMenuInput();
        int actual = casino.isAPlayer("aName");

        assertNotEquals(-1, actual);
    }

    @Test
    public void parseMenuInput6Test(){
        ByteArrayInputStream in = new ByteArrayInputStream(("6\n").getBytes());
        System.setIn(in);
        casino = new Casino();
        casino.createPlayer(givenName);
        casino.playerLogin(givenName);

        casino.parseMenuInput();
        boolean actual = casino.checkIfActivePlayer();

        assertFalse(actual);
    }

    @Test
    public void parseMenuInputDDefaultTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("badInput\n1\naName\nno\n").getBytes());
        System.setIn(in);
        casino = new Casino();


        casino.parseMenuInput();
        int actual = casino.isAPlayer("aName");

        assertNotEquals(-1, actual);
    }

    @Test
    public void gameLoginGamblingTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("0\nno\n").getBytes());
        System.setIn(in);
        casino = new Casino();
        GamblingPlayer player = casino.createGamblingPlayer(givenName);
        casino.playerLogin(givenName);
        ByteArrayInputStream in2 = new ByteArrayInputStream(("0\nno\n").getBytes());
        System.setIn(in2);
        Blackjack blackjack = new Blackjack(player);

        casino.gameLogin(blackjack);
        boolean isBlackjack = casino.getActiveGame() instanceof Blackjack;

        assertTrue(isBlackjack);
    }

    @Test
    public void gameLoginNotGamblingTest(){
        casino = new Casino();
        Player player = casino.createPlayer(givenName);
        casino.playerLogin(givenName);
        ByteArrayInputStream in2 = new ByteArrayInputStream(("exit\n").getBytes());
        System.setIn(in2);
        GoFish gofish = new GoFish(5, player);

        casino.gameLogin(gofish);
        boolean isGoFish = casino.getActiveGame() instanceof GoFish;

        assertTrue(isGoFish);
    }

    @Test
    public void gamblingGameLoginTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("0\nno\n").getBytes());
        System.setIn(in);
        casino = new Casino();
        GamblingPlayer player = casino.createGamblingPlayer(givenName);
        casino.playerLogin(givenName);
        ByteArrayInputStream in2 = new ByteArrayInputStream(("0\nno\n").getBytes());
        System.setIn(in2);
        Blackjack blackjack = new Blackjack(player);

        casino.gamblingGameLogin(blackjack);
        boolean isBlackjack = casino.getActiveGame() instanceof Blackjack;

        assertTrue(isBlackjack);
    }

    @Test
    public void checkIfActivePlayersTest(){
        casino.createPlayer(givenName);
        casino.playerLogin(givenName);

        Boolean actual = casino.checkIfActivePlayer();

        assertTrue(actual);
    }

    @Test
    public void parseBalanceAddNoTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("yes\n0\nno\n").getBytes());
        System.setIn(in);
        casino = new Casino();

        GamblingPlayer player = casino.createGamblingPlayer(givenName);
        casino.playerLogin(givenName);

        casino.parseBalanceAdd();

        int actual = player.getBalance();

        assertEquals(0, actual);
    }

    @Test
    public void parseBalanceAddYesTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("yes\n0\nyes\n40\n").getBytes());
        System.setIn(in);
        casino = new Casino();

        GamblingPlayer player = casino.createGamblingPlayer(givenName);
        casino.playerLogin(givenName);

        casino.parseBalanceAdd();

        int actual = player.getBalance();

        assertEquals(40, actual);
    }

    @Test
    public void parseBalanceAddBadInputTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("yes\n0\nbadInput\nno\n").getBytes());
        System.setIn(in);
        casino = new Casino();

        GamblingPlayer player = casino.createGamblingPlayer(givenName);
        casino.playerLogin(givenName);

        casino.parseBalanceAdd();

        int actual = player.getBalance();

        assertEquals(0, actual);
    }

    @Test
    public void addToPlayerBalanceTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("yes\n0\n").getBytes());
        System.setIn(in);
        casino = new Casino();
        GamblingPlayer player = casino.createGamblingPlayer(givenName);
        casino.playerLogin(givenName);
        int givenBalance = 40;

        casino.addToPlayerBalance(givenBalance);
        int actual = player.getBalance();

        assertEquals(givenBalance, actual);
    }

    @Test
    public void checkIfGamblingPlayerNOTTest(){
        casino.createPlayer(givenName);
        casino.playerLogin(givenName);

        Boolean actual = casino.checkIfGamblingPlayer();

        assertFalse(actual);
    }

    @Test
    public void checkIfGamblingPlayerTest(){
        ByteArrayInputStream in = new ByteArrayInputStream(("yes\n40\n").getBytes());
        System.setIn(in);
        casino = new Casino();

        casino.createGamblingPlayer(givenName);
        casino.playerLogin(givenName);

        Boolean actual = casino.checkIfGamblingPlayer();

        assertTrue(actual);
    }

}