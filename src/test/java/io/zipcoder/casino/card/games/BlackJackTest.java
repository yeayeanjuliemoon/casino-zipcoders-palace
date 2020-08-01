package io.zipcoder.casino.card.games;

import io.zipcoder.casino.GamblingPlayer;
import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardRank;
import io.zipcoder.casino.card.utilities.CardSuit;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class BlackJackTest {

    GamblingPlayer player;
    Blackjack blackjack;
    Logger logger = Logger.getLogger(BlackJackTest.class.getName());

    @Before
    public void init() {
        player = new GamblingPlayer("aName");
        blackjack = new Blackjack(player);
    }

    @Test
    public void dealCardTest() {
        String originalHand = blackjack.showHand(player);
        Card card = new Card(CardSuit.SPADE, CardRank.ACE);
        String givenCard = card.toString();

        blackjack.dealCard(player, card);
        String dealtHand = blackjack.showHand(player);
        String handDifference = dealtHand.substring(originalHand.length());

        assertEquals(givenCard, handDifference);
    }

    @Test
    public void countHandTest() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(CardSuit.SPADE, CardRank.ACE));
        cards.add(new Card(CardSuit.CLUB, CardRank.JACK));


        int actualResult = blackjack.countHand(cards);
        int expectedResult =21;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void aceTestTest(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(CardSuit.SPADE, CardRank.ACE)); // Value 11
        cards.add(new Card(CardSuit.CLUB, CardRank.JACK)); // Value 10
        cards.add(new Card(CardSuit.HEART, CardRank.NINE)); // Value 9

        int actualResult = blackjack.aceTest(30, cards);
        int expectedResult =20;

        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void determinePlayerMoveTest() {
        Boolean givenPlayerChoice = true;
        String originalHand = blackjack.showHand(player);
        blackjack.determinePlayerMove(givenPlayerChoice);

        String newHand = blackjack.showHand(player);
        String newCard = newHand.substring(originalHand.length());
        logger.log(Level.INFO, newCard);
        assertTrue(newCard.length() > 1);
    }

    @Test
    public void translatePlayerChoiceTest(){
        Boolean actualResult = blackjack.translatePlayerChoice("yes");
        assertTrue(actualResult);
    }

    @Test
    public void translatePlayerChoiceNullTest(){
        Boolean actualResult = blackjack.translatePlayerChoice("notCorrectInput");
        assertNull(actualResult);
    }

    @Test
    public void playerBustTest1() {
        Boolean actualResult = blackjack.playerBust(22);
        assertTrue(actualResult);
    }

    @Test
    public void playerBustTest2() {
        Boolean actualResult = blackjack.playerBust(21);
        assertFalse(actualResult);
    }


    @Test
    public void takeBetTest() {
        player.deposit(20);

        blackjack.takeBet(player, 20);
        blackjack.payout(); // Returns 2X bet
        int postPayoutBalance = player.getBalance();

        assertEquals(40, postPayoutBalance);
    }

    @Test
    public void sufficientFundsCheckTest1(){
        player.deposit(50);
        int oversizedBet = 500;
        Boolean actualResult = blackjack.sufficientFundsCheck(player, oversizedBet);

        assertFalse(actualResult);
    }

    @Test
    public void sufficientFundsCheckTest2(){
        player.deposit(50000);
        int oversizedBet = 500;
        Boolean actualResult = blackjack.sufficientFundsCheck(player, oversizedBet);

        assertTrue(actualResult);
    }


    @Test
    public void payoutTest() {
        player.deposit(20);

        blackjack.takeBet(player, 20);
        blackjack.payout(); //Returns 2X bet
        int postPayoutBalance = player.getBalance();

        assertEquals(40, postPayoutBalance);
    }

    @Test
    public void payBackTest() {
        player.deposit(20);

        blackjack.takeBet(player, 20);
        blackjack.payBack(); // Returns bet
        int postPayoutBalance = player.getBalance();

        assertEquals(20, postPayoutBalance);
    }

        /*//TODO - REFACTOR TESTS AS CODE NOTED FOR REFACTOR
    @Test
    public void playTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(("20"+ System.lineSeparator() + "no").getBytes());
        System.setIn(in);

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


        blackjack.nextTurn();
        boolean dontContinueTurn = blackjack.checkGameState();

        assertFalse(dontContinueTurn);
    }

    @Test
    public void playerTwentyOneTest() {

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

        Card card = new Card(CardSuit.SPADE, CardRank.ACE);

        int expectedValue = 11;
        int actualValue = blackjack.cardValueCalculator(card);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void printGameRulesTest() {


        String expectedRules ="* The goal of the game is to beat the dealer's hand without going over 21\n" +
                "* You and the dealer start with two cards. One of the dealer's cards is hidden until their turn.\n"+
                "* You can ask for additional cards until you want to stop or you go over 21.\n"+
                "* Cards Two through Ten are face value. Face cards are worth 10. Aces are worth 1 or 11.\n\n";
        String actualRules = blackjack.printGameRules();


        assertEquals(expectedRules, actualRules);
    }*/
}