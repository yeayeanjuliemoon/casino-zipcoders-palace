package io.zipcoder.casino;

import io.zipcoder.casino.card.games.GoFish;
import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardRank;
import io.zipcoder.casino.card.utilities.CardSuit;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GoFishTest {
    private Player mainPlayer;
    private Player dealer;
    private GoFish game;
    private Map<Player, List<Card>> playerHands;

    @Before
    public void setUp(){
        this.mainPlayer = new Player("Bob");
        this.game = new GoFish(5, mainPlayer);
        this.playerHands = game.getPlayerHands();
        this.dealer = game.getDealer();

        // Making the player hand [Jack Spade] [Jack Diamond] [Two Spade] [Two Diamond] [Eight Diamond]
        List<Card> playerTestHand = new ArrayList<>(Arrays.asList
                (new Card(CardSuit.SPADE, CardRank.JACK), new Card(CardSuit.SPADE, CardRank.TWO),
                        new Card(CardSuit.DIAMOND, CardRank.TWO), new Card(CardSuit.DIAMOND, CardRank.JACK),
                        new Card(CardSuit.DIAMOND, CardRank.EIGHT)));
        List<Card> dealerTestHand = new ArrayList<>(Arrays.asList
                (new Card(CardSuit.CLUB, CardRank.JACK), new Card(CardSuit.CLUB, CardRank.TWO),
                        new Card(CardSuit.HEART, CardRank.TWO), new Card(CardSuit.HEART, CardRank.JACK),
                        new Card(CardSuit.HEART, CardRank.SEVEN)));

        this.game.setPlayerHand(playerTestHand);
        this.game.setDealerHand(dealerTestHand);
    }

    @Test
    public void testConstructor(){
        assertEquals(this.playerHands.get(mainPlayer).size(), 5);
    }

    @Test
    public void testCheckPlayerHand(){
        CardRank rankToBeChecked = this.playerHands.get(this.mainPlayer).get(0).getRank();

        assertTrue(this.game.checkPlayerHand(rankToBeChecked, this.mainPlayer));
    }

    @Test
    public void testParseRankSuccess1(){
        CardRank expected = CardRank.JACK;
        try {
            CardRank actual = game.parseCardRank("jack");
            assertEquals(expected, actual);
        } catch (IllegalArgumentException e){

        }

    }

    @Test
    public void testParseRankSuccess2(){
        CardRank expected = CardRank.TWO;
        try {
            CardRank actual = game.parseCardRank("two");

            assertEquals(expected, actual);
        } catch (IllegalArgumentException e){

        }
    }

    @Test
    public void testDetermineWinner(){
        Player winner = game.determineWinner();

        assertEquals(winner, null);
    }

    @Test
    public void testCheckGameState(){
        boolean gameState = this.game.checkGameState();

        assertTrue(gameState);
    }

    @Test
    public void testHandleUserInput(){
        boolean successStatus = this.game.handleUserInput(CardRank.JACK);
        if(this.game.checkPlayerHand(CardRank.JACK, this.mainPlayer)){
            assertFalse(successStatus);
        }
        else{
            assertTrue(successStatus);
        }

        boolean exitStatus = this.game.handleUserInput(null);

        assertTrue(exitStatus);
    }

    @Test
    public void testIncrementPlayerScore(){
        this.game.incrementScore(this.mainPlayer);
        Player winner = this.game.determineWinner();

        assertEquals(winner, this.mainPlayer);

        this.game.incrementScore(this.dealer);
        this.game.incrementScore(this.dealer);

        winner = this.game.determineWinner();

        assertEquals(winner, this.dealer);
    }

    @Test
    public void testTransferCards(){
        CardRank toBeTransferred = this.playerHands.get(this.mainPlayer).get(0).getRank();
        Integer dealerBeforeTransferHandSize = this.playerHands.get(this.dealer).size();

        this.game.transferCards(this.mainPlayer, this.dealer, toBeTransferred);

        Integer dealerAfterTransferHandSize = this.playerHands.get(this.dealer).size();

        assertTrue(dealerAfterTransferHandSize > dealerBeforeTransferHandSize);
    }

    @Test
    public void testRankMapCreation(){
        List<Card> playerHand = this.playerHands.get(this.mainPlayer);
        Map<CardRank, Integer> rankMap = this.game.createRankMap(this.mainPlayer);
        List<Card> mainPlayerHand = this.playerHands.get(this.mainPlayer);
        CardRank firstRank = playerHand.get(0).getRank();
        playerHand.add(new Card(CardSuit.SPADE, firstRank));
        playerHand.add(new Card(CardSuit.SPADE, firstRank));

        for(Card c : mainPlayerHand){
            assertTrue(rankMap.get(c.getRank()) > 0);
        }
    }

    @Test
    public void testGoFish(){
        Integer initialHandSize = this.playerHands.get(this.mainPlayer).size();

        this.game.goFish(this.mainPlayer);

        Integer finalHandSize = this.playerHands.get(this.mainPlayer).size();

        assertTrue(finalHandSize == (initialHandSize + 1));
    }

    @Test
    public void testCheckForCardSets(){
        List<Card> playerHand = this.playerHands.get(this.mainPlayer);
        CardRank firstRank = playerHand.get(0).getRank();
        playerHand.add(new Card(CardSuit.SPADE, firstRank));
        playerHand.add(new Card(CardSuit.SPADE, firstRank));
        playerHand.add(new Card(CardSuit.SPADE, firstRank));

        this.game.checkForCardSets(this.mainPlayer);

        assertFalse(this.game.checkPlayerHand(firstRank, this.mainPlayer));
    }

    @Test
    public void testPrintRules(){
        String expectedRules = "=======Welcome to Go Fish=======\n" +
                "How to Play: \n" +
                "- Ask the other player if they have any cards that match the rank\n of a card in your hand\n" +
                "- If they do, you take all cards of that rank from their hand, and\n can ask again"+
                "- If they don't, you Go Fish! draw a card, and your turn is over\n" +
                "- Each time you get a set of four cards from the same rank, you \n remove those cards from your hand and gain a point\n" +
                "- First player to 5 points wins! \n\n" + "Enter 'exit' at any time to end the game. Have Fun!";

        assertEquals(expectedRules, this.game.printGameRules());
    }

    @Test
    public void testGetPlayerInput(){
        CardRank expected = CardRank.JACK;
        ByteArrayInputStream in = new ByteArrayInputStream("JACK".getBytes());
        System.setIn(in);
        CardRank actual = this.game.getPlayerInput();

        assertEquals(expected, actual);
    }






}
