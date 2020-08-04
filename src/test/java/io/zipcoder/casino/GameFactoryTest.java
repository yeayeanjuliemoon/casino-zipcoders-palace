package io.zipcoder.casino;

import io.zipcoder.casino.card.games.Blackjack;
import io.zipcoder.casino.card.games.GoFish;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameFactoryTest {

    Player aPlayer;
    Player aGamblingPLayer;

    @Before
    public void init(){
        aPlayer = new Player("aName");
        aGamblingPLayer = new GamblingPlayer("aName");
    }

    @Test
    public void getGameGoFish1Test() {
        Game actual = GameFactory.getGame("GOFISH", aPlayer);

        assertTrue(actual instanceof GoFish);
    }

    @Test
    public void getGameGoFish2Test() {
        Game actual = GameFactory.getGame("GOFISH", aGamblingPLayer);

        assertTrue(actual instanceof GoFish);
    }

    @Test
    public void getGameBlackJack1Test() {
        Game actual = GameFactory.getGame("BLACKJACK", aPlayer);

        assertNull(actual);
    }

    @Test
    public void getGameBlackJack2Test() {
        Game actual = GameFactory.getGame("BLACKJACK", aGamblingPLayer);

        assertTrue(actual instanceof Blackjack);
    }

    @Test
    public void getGameCraps1Test() {
        Game actual = GameFactory.getGame("CRAPS", aPlayer);

        assertNull(actual);
    }

    @Test
    public void getGameCraps2Test() {
        Game actual = GameFactory.getGame("CRAPS", aGamblingPLayer);

        assertTrue(actual instanceof CrapsGame);
    }

    @Test
    public void getGameCeelo1Test() {
        Game actual = GameFactory.getGame("CEELO", aPlayer);

        assertNull(actual);
    }

    @Test
    public void getGameCeelo2Test() {
        Game actual = GameFactory.getGame("CEELO", aGamblingPLayer);

        assertTrue(actual instanceof CeeLoGame);
    }

    @Test
    public void getGameBadInputTest() {
        Game actual = GameFactory.getGame("BADINPUT", aGamblingPLayer);

        assertNull(actual);
    }

    @Test
    public void getGameNullPlayerTest() {
        aGamblingPLayer = null;
        Game actual = GameFactory.getGame("CRAPS", aGamblingPLayer);

        assertNull(actual);
    }
}