package io.zipcoder.casino;

import io.zipcoder.casino.card.games.GoFish;
import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardRank;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GoFishTest {
    private Player mainPlayer;
    private GoFish game;
    private Map<Player, List<Card>> playerHands;

    @Before
    public void setUp(){
        this.mainPlayer = new Player("Bob");
        this.game = new GoFish(5, mainPlayer);
        this.playerHands = game.getPlayerHands();
    }

    @Test
    public void testConstructor(){
        assertEquals(this.playerHands.get(mainPlayer).size(), 5);
    }

    @Test
    public void testParseRankSuccess1(){
        CardRank expected = CardRank.JACK;
        CardRank actual = game.parseCardRank("jack");

        assertEquals(expected, actual);

    }

    @Test
    public void testParseRankSuccess2(){
        CardRank expected = CardRank.TWO;
        CardRank actual = game.parseCardRank("two");

        assertEquals(expected, actual);
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






}
