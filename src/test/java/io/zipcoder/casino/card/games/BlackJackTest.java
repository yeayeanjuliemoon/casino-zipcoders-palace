package io.zipcoder.casino.card.games;

import io.zipcoder.casino.GamblingPlayer;
import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardRank;
import io.zipcoder.casino.card.utilities.CardSuit;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class BlackJackTest {

    @Test
    public void dealCardTest() {
        GamblingPlayer player = new GamblingPlayer("Zed");
        Blackjack blackjack = new Blackjack(player);

        String originalHand = blackjack.showHand(player);
        blackjack.dealCard(player);
        String dealtHand = blackjack.showHand(player);

        Logger logger = Logger.getLogger(BlackJackTest.class.getName());
        logger.log(Level.INFO, dealtHand);

        assertNotEquals(originalHand, dealtHand);
    }

    @Test
    public void countHandTest() {
        GamblingPlayer player = new GamblingPlayer("Ted");
        int actualResult = 0;
        int expectedResult = 15;

        boolean matchesTest = false;
        while(!matchesTest){
            Blackjack blackjack = new Blackjack(player);
            if(blackjack.showHand(player).equals("[FOUR CLUB][ACE SPADE]")){
                actualResult = blackjack.countHand(player);
                matchesTest = true;
            }
        }

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getPlayerChoiceTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("no").getBytes());
        System.setIn(in);
        GamblingPlayer player = new GamblingPlayer("Red");
        Blackjack blackjack = new Blackjack(player);

        Boolean actualResult = blackjack.getPlayerChoice();

        assertFalse(actualResult);
    }

    @Test
    public void playerBustTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("no").getBytes());
        System.setIn(in);

        GamblingPlayer player = new GamblingPlayer("Sed");
        Blackjack blackjack = new Blackjack(player);
        while(blackjack.countHand(player) < 22){
            blackjack.dealCard(player);
        }

        Boolean actualResult = blackjack.playerBust(player);

        assertTrue(actualResult);
    }

    @Test
    public void takeBetTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("20").getBytes());
        System.setIn(in);
        GamblingPlayer player = new GamblingPlayer("Red");
        Blackjack blackjack = new Blackjack(player);
        player.deposit(20);

        blackjack.takeBet();
        blackjack.payout();
        int postPayoutBalance = player.getBalance();

        assertEquals(40, postPayoutBalance);
    }

    @Test
    public void payoutTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("20").getBytes());
        System.setIn(in);
        GamblingPlayer player = new GamblingPlayer(null);
        Blackjack blackjack = new Blackjack(player);
        player.deposit(20);

        blackjack.takeBet();
        blackjack.payout();
        int postPayoutBalance = player.getBalance();

        assertEquals(40, postPayoutBalance);
    }

    @Test
    public void payBackTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("20").getBytes());
        System.setIn(in);
        GamblingPlayer player = new GamblingPlayer("ted");
        Blackjack blackjack = new Blackjack(player);
        player.deposit(20);

        blackjack.takeBet();
        blackjack.payBack();
        int postPayoutBalance = player.getBalance();

        assertEquals(20, postPayoutBalance);
    }

    @Test
    public void playTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("20"+ System.lineSeparator() + "no").getBytes());
        System.setIn(in);
        GamblingPlayer player = new GamblingPlayer("Ned");
        Blackjack blackjack = new Blackjack(player);
        int startBalance = 40;
        player.deposit(startBalance);

        blackjack.play();
        int endBalance = player.getBalance();

        // In the occurrence of a draw, this may equal.
        assertNotEquals(startBalance, endBalance);
    }

    @Test
    public void nextTurnTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("no").getBytes());
        System.setIn(in);
        GamblingPlayer player = new GamblingPlayer("Jed");
        Blackjack blackjack = new Blackjack(player);

        blackjack.nextTurn();
        boolean dontContinueTurn = blackjack.checkGameState();

        assertFalse(dontContinueTurn);
    }

    @Test
    public void playerTwentyOneTest() {
        GamblingPlayer player = new GamblingPlayer("BlackJackFred");
        boolean actualResult = false;

        boolean matchesTest = false;
        while(!matchesTest){
            ByteArrayInputStream in = new ByteArrayInputStream(("20").getBytes());
            System.setIn(in);
            player.deposit(20);
            Blackjack blackjack = new Blackjack(player);
            blackjack.takeBet();
            if(blackjack.showHand(player).equals("[JACK CLUB][ACE SPADE]")){
                actualResult = blackjack.playerTwentyOne();
                matchesTest = true;
            }
        }

        assertTrue(actualResult);
    }

    @Test
    public void cardValueCalculatorTest() {
        GamblingPlayer player = new GamblingPlayer("ted");
        Blackjack blackjack = new Blackjack(player);
        Card card = new Card(CardSuit.SPADE, CardRank.ACE);

        int expectedValue = 11;
        int actualValue = blackjack.cardValueCalculator(card);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void printGameRulesTest() {
        GamblingPlayer player = new GamblingPlayer("ted");
        Blackjack blackjack = new Blackjack(player);

        String expectedRules ="* The goal of the game is to beat the dealer's hand without going over 21\n" +
                "* You and the dealer start with two cards. One of the dealer's cards is hidden until their turn.\n"+
                "* You can ask for additional cards until you want to stop or you go over 21.\n"+
                "* Cards Two through Ten are face value. Face cards are worth 10. Aces are worth 1 or 11.\n\n";
        String actualRules = blackjack.printGameRules();


        assertEquals(expectedRules, actualRules);
    }
}