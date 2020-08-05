package io.zipcoder.casino.card.games;

import io.zipcoder.casino.GamblingPlayer;
import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardRank;
import io.zipcoder.casino.card.utilities.CardSuit;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class BlackJackTest {

    GamblingPlayer player;
    Blackjack blackjack;
    Logger logger = Logger.getLogger(BlackJackTest.class.getName());
    ArrayList<Card> losingHand;
    ArrayList<Card> twentyOneHand;


    @Before
    public void init() {
        player = new GamblingPlayer("aName");
        blackjack = new Blackjack(player);
        losingHand =  new ArrayList<>();
        losingHand.add(new Card(CardSuit.SPADE, CardRank.JACK));
        losingHand.add(new Card(CardSuit.SPADE, CardRank.QUEEN));
        losingHand.add(new Card(CardSuit.SPADE, CardRank.KING));
        twentyOneHand =  new ArrayList<>();
        twentyOneHand.add(new Card(CardSuit.SPADE, CardRank.JACK));
        twentyOneHand.add(new Card(CardSuit.SPADE, CardRank.ACE));
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


        int actualResult = blackjack.getHandValue(cards);
        int expectedResult =21;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void aceTestTest(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(CardSuit.SPADE, CardRank.ACE)); // Value 11
        cards.add(new Card(CardSuit.CLUB, CardRank.JACK)); // Value 10
        cards.add(new Card(CardSuit.HEART, CardRank.NINE)); // Value 9

        int actualResult = blackjack.adjustForAces(30, cards);
        int expectedResult =20;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void aceTestMultipleAcesTest(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(CardSuit.SPADE, CardRank.ACE)); // Value 11
        cards.add(new Card(CardSuit.CLUB, CardRank.ACE)); // Value 11 -> 1
        cards.add(new Card(CardSuit.HEART, CardRank.ACE)); // Value 11 -> 1

        int actualResult = blackjack.adjustForAces(33, cards);
        int expectedResult =13;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void determinePlayerMoveTest1() {
        Boolean givenPlayerChoice = true;
        String originalHand = blackjack.showHand(player);
        blackjack.determinePlayerMove(givenPlayerChoice);

        String newHand = blackjack.showHand(player);
        String newCard = newHand.substring(originalHand.length());
        logger.log(Level.INFO, newCard);
        assertTrue(newCard.length() > 1);
    }

    @Test
    public void determinePlayerMove2Test() {
        Boolean givenPlayerChoice = false;
        String originalHand = blackjack.showHand(player);
        blackjack.determinePlayerMove(givenPlayerChoice);

        String newHand = blackjack.showHand(player);

        assertTrue(originalHand.equals(newHand));
    }

    @Test
    public void translatePlayerChoiceTest(){
        Boolean actualResult = blackjack.translatePlayerChoiceToBool("yes");
        assertTrue(actualResult);
    }

    @Test
    public void translatePlayerChoiceNoTest(){
        Boolean actualResult = blackjack.translatePlayerChoiceToBool("no");
        assertFalse(actualResult);
    }

    @Test
    public void translatePlayerChoiceNullTest(){
        Boolean actualResult = blackjack.translatePlayerChoiceToBool("notCorrectInput");
        assertNull(actualResult);
    }

    @Test
    public void parsePlayerMoveYesTest() {
        ByteArrayInputStream givenPlayerChoice = new ByteArrayInputStream(("yes\n").getBytes());
        System.setIn(givenPlayerChoice);
        this.blackjack = new Blackjack(player);

        String originalHand = blackjack.showHand(player);
        blackjack.parsePlayerMoveChoice();

        String newHand = blackjack.showHand(player);
        String newCard = newHand.substring(originalHand.length());
        logger.log(Level.INFO, newCard);
        assertTrue(newCard.length() > 1);
    }

    @Test
    public void parsePlayerMoveNoTest() {
        ByteArrayInputStream givenPlayerChoice = new ByteArrayInputStream(("no\n").getBytes());
        System.setIn(givenPlayerChoice);
        this.blackjack = new Blackjack(player);

        String originalHand = blackjack.showHand(player);
        blackjack.parsePlayerMoveChoice();

        String newHand = blackjack.showHand(player);
        String newCard = newHand.substring(originalHand.length());
        logger.log(Level.INFO, newCard);
        assertTrue(newCard.length() == 0);
    }

    @Test
    public void parsePlayerMoveNullTest() {
        ByteArrayInputStream givenPlayerChoice = new ByteArrayInputStream(("badinput\nno\n").getBytes());
        System.setIn(givenPlayerChoice);
        this.blackjack = new Blackjack(player);

        String originalHand = blackjack.showHand(player);
        blackjack.parsePlayerMoveChoice();

        String newHand = blackjack.showHand(player);
        String newCard = newHand.substring(originalHand.length());
        logger.log(Level.INFO, newCard);
        assertTrue(newCard.length() == 0);
    }

    @Test
    public void playerBustTest1() {
        Boolean actualResult = blackjack.playerBustTest(22);
        assertTrue(actualResult);
    }

    @Test
    public void playerBustTest2() {
        Boolean actualResult = blackjack.playerBustTest(21);
        assertFalse(actualResult);
    }

    @Test
    public void dealerTurnSimTest(){
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(CardSuit.SPADE, CardRank.KING));
        hand.add(new Card(CardSuit.SPADE, CardRank.FIVE));
        blackjack.setDealerHand(hand);
        String startingHand = blackjack.getDealerHand().toString();

        blackjack.dealerTurnSim();
        String endingHand = blackjack.getDealerHand().toString();

        assertNotEquals(startingHand, endingHand);
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
    public void takeBetInsufficientTest() {
        ByteArrayInputStream givenPlayerChoice = new ByteArrayInputStream(("20\n").getBytes());
        System.setIn(givenPlayerChoice);
        this.blackjack = new Blackjack(player);
        player.deposit(20);

        blackjack.takeBet(player, 40);
        blackjack.payBack(); // Returns bet
        int postPayoutBalance = player.getBalance();

        assertEquals(20, postPayoutBalance);
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

    @Test
    public void playTest(){
        ByteArrayInputStream givenPlayerChoice = new ByteArrayInputStream(("20\nno\n").getBytes());
        System.setIn(givenPlayerChoice);
        this.blackjack = new Blackjack(player);
        player.deposit(20);

        blackjack.play();
        int actual = player.getBalance();

        assertNotEquals(20, actual);
    }

    @Test
    public void gameSetupTest(){
        ByteArrayInputStream givenPlayerChoice = new ByteArrayInputStream(("20\n").getBytes());
        System.setIn(givenPlayerChoice);
        this.blackjack = new Blackjack(player);
        player.deposit(20);

        blackjack.gameSetup();
        blackjack.payout();
        int actualBalance = player.getBalance();

        assertEquals(40, actualBalance);
    }
    @Test
    public void playerPlaysTurnTest(){
        ByteArrayInputStream givenPlayerChoice = new ByteArrayInputStream(("no\n").getBytes());
        System.setIn(givenPlayerChoice);
        this.blackjack = new Blackjack(player);
        player.deposit(20);
        blackjack.takeBet(player, 20);

        blackjack.setPlayerHand(twentyOneHand);
        blackjack.playerPlaysTurn();
        Boolean actualState = blackjack.checkGameState();

        assertFalse(actualState);
    }
    @Test
    public void nextTurnTest(){
        ByteArrayInputStream givenPlayerChoice = new ByteArrayInputStream(("no\n").getBytes());
        System.setIn(givenPlayerChoice);
        this.blackjack = new Blackjack(player);

        blackjack.setPlayerHand(losingHand);
        blackjack.nextTurn();
        Boolean actualState = blackjack.checkGameState();

        assertFalse(actualState);
    }

    @Test
    public void endCasePlayerGotTwentyOneTest(){
        player.deposit(20);

        blackjack.takeBet(player, 20);
        blackjack.endCasePlayerGotTwentyOne(); // Returns 2X bet
        int postPayoutBalance = player.getBalance();

        assertEquals(40, postPayoutBalance);
    }

    @Test
    public void playerGotTwentyOneDidTest(){
        int givenHandValue = 21;

        boolean actual = blackjack.playerGotTwentyOne(givenHandValue);

        assertTrue(actual);
    }

    @Test
    public void playerGotTwentyOneDidntTest(){
        int givenHandValue = 18;

        boolean actual = blackjack.playerGotTwentyOne(givenHandValue);

        assertFalse(actual);
    }

    @Test
    public void afterPlayerInputLosingTest(){
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(CardSuit.SPADE, CardRank.KING));
        blackjack.setDealerHand(hand);
        String expected = blackjack.getDealerHand().toString();

        blackjack.setPlayerHand(losingHand);
        blackjack.afterPlayerInput();

        String actual = blackjack.getDealerHand().toString();

        assertEquals(expected, actual);
    }

    @Test
    public void afterPlayerInputWinningTest(){
        player.deposit(10);
        blackjack.takeBet(player, 10);
        blackjack.setDealerHand(losingHand);
        String expected = blackjack.getDealerHand().toString();

        blackjack.setPlayerHand(twentyOneHand);
        blackjack.afterPlayerInput();

        int actual = player.getBalance();

        assertEquals(20, actual);
    }

    @Test
    public void tallyWinnersDealerBustTest(){
        player.deposit(10);
        blackjack.takeBet(player, 10);
        blackjack.setDealerHand(losingHand);

        blackjack.tallyWinners();
        int actual = player.getBalance();

        assertEquals(20, actual);
    }

    @Test
    public void tallyWinnersWinTest(){
        player.deposit(10);
        blackjack.takeBet(player, 10);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(CardSuit.SPADE, CardRank.KING));
        blackjack.setDealerHand(hand);
        blackjack.setPlayerHand(twentyOneHand);

        blackjack.tallyWinners();
        int actual = player.getBalance();

        assertEquals(20, actual);
    }

    @Test
    public void tallyWinnersDrawTest(){
        player.deposit(10);
        blackjack.takeBet(player, 10);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(CardSuit.SPADE, CardRank.KING));
        blackjack.setDealerHand(hand);
        blackjack.setPlayerHand(hand);

        blackjack.tallyWinners();
        int actual = player.getBalance();

        assertEquals(10, actual);
    }

    @Test
    public void tallyWinnersLostTest(){
        player.deposit(10);
        blackjack.takeBet(player, 10);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(CardSuit.SPADE, CardRank.KING));
        blackjack.setDealerHand(twentyOneHand);
        blackjack.setPlayerHand(hand);

        blackjack.tallyWinners();
        int actual = player.getBalance();

        assertEquals(0, actual);
    }


    @Test
    public void endCaseWinningHandWonTest(){
        int givenPlayerHandValue = 20;
        int givenDealerHandValue = 18;

        Boolean actual = blackjack.endCaseWinningHand(givenPlayerHandValue, givenDealerHandValue);

        assertTrue(actual);
    }

    @Test
    public void endCaseWinningHandLostTest(){
        int givenPlayerHandValue = 18;
        int givenDealerHandValue = 20;

        Boolean actual = blackjack.endCaseWinningHand(givenPlayerHandValue, givenDealerHandValue);

        assertFalse(actual);
    }

    @Test
    public void endCaseWinningHandDrawTest(){
        int givenPlayerHandValue = 18;
        int givenDealerHandValue = 18;

        Boolean actual = blackjack.endCaseWinningHand(givenPlayerHandValue, givenDealerHandValue);

        assertFalse(actual);
    }

    @Test
    public void endCaseDrawTest(){
        int givenPlayerHandValue = 18;
        int givenDealerHandValue = 18;

        Boolean actual = blackjack.endCaseDraw(givenPlayerHandValue, givenDealerHandValue);

        assertTrue(actual);
    }

    @Test
    public void endCaseDrawNoDrawTest(){
        int givenPlayerHandValue = 18;
        int givenDealerHandValue = 20;

        Boolean actual = blackjack.endCaseDraw(givenPlayerHandValue, givenDealerHandValue);

        assertFalse(actual);
    }

    @Test
    public void cardValueCalculatorTest() {

        Card card = new Card(CardSuit.SPADE, CardRank.ACE);

        Integer expectedValue = 11;
        Integer actualValue = blackjack.cardValueCalculator(card);

        assertEquals(expectedValue.longValue(), actualValue.longValue());
    }

    @Test
    public void checkGameStateTest(){
        player.deposit(10);
        blackjack.takeBet(player, 10);
        blackjack.endCasePlayerGotTwentyOne();

        Boolean actualState = blackjack.checkGameState();

        assertFalse(actualState);
    }
    @Test
    public void printGameRulesTest() {
        String expectedRules ="* The goal of the game is to beat the dealer's hand without going over 21\n" +
                "* You and the dealer start with two cards. One of the dealer's cards is hidden until their turn.\n"+
                "* You can ask for additional cards until you want to stop or you go over 21.\n"+
                "* Cards Two through Ten are face value. Face cards are worth 10. Aces are worth 1 or 11.\n\n";
        String actualRules = blackjack.printGameRules();


        assertEquals(expectedRules, actualRules);
    }
}