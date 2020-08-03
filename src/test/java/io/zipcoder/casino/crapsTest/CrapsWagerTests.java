package io.zipcoder.casino.crapsTest;

import io.zipcoder.casino.CrapsGame;
import io.zipcoder.casino.GamblingPlayer;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.card.utilities.CrapsWagerType;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CrapsWagerTests {

    private CrapsGame game;
    private GamblingPlayer mainPlayer;
    private ByteArrayInputStream playerInput;

    public void setUpWithInput(String input){
        this.playerInput = new ByteArrayInputStream((input).getBytes());
        System.setIn(this.playerInput);
        this.mainPlayer = new GamblingPlayer("Bob");
        this.mainPlayer.deposit(1000);
        this.game = new CrapsGame(this.mainPlayer);
    }

    @Test
    public void testGetRoundOneWagerPass(){
        setUpWithInput("pass\n100");
        this.game.getRoundOneWager();
        Integer expectedWager = 100;

        Integer actualWager = this.game.getPlayerWager().getPass();

        assertEquals(expectedWager, actualWager);
    }

    @Test
    public void testGetRoundOneWagerPassWrongWager(){
        setUpWithInput("field\njsdhfki\npass\n100");
        this.game.getRoundOneWager();
        Integer expectedWager = 100;

        Integer actualWager = this.game.getPlayerWager().getPass();

        assertEquals(expectedWager, actualWager);
    }

    @Test
    public void testGetRoundOneWagerNotEnoughMoney(){
        setUpWithInput("pass\n10000\n100");
        this.game.getRoundOneWager();
        Integer expectedWager = 100;

        Integer actualWager = this.game.getPlayerWager().getPass();

        assertEquals(expectedWager, actualWager);
    }


    @Test
    public void testGetRoundOneWagerDontPass(){
        setUpWithInput("dontpass\n100");
        Integer expectedWager = 100;

        this.game.getRoundOneWager();

        Integer actualWager = this.game.getPlayerWager().getDontPass();

        assertEquals(expectedWager, actualWager);
    }

    @Test
    public void testSetField(){
        setUpWithInput("");
        Integer expectedField = 100;
        this.game.setWager(CrapsWagerType.FIELD, expectedField);

        Integer actualField = this.game.getPlayerWager().getFieldWager();

        assertEquals(actualField, expectedField);
    }

    @Test
    public void testSetSevens(){
        setUpWithInput("");
        Integer expectedSeven = 100;
        this.game.setWager(CrapsWagerType.SEVENS, expectedSeven);

        Integer actualSeven = this.game.getPlayerWager().getSeven();

        assertEquals(actualSeven, expectedSeven);
    }

    @Test
    public void testSetAnyCraps(){
        setUpWithInput("");
        Integer expectedAny = 100;
        this.game.setWager(CrapsWagerType.ANYCRAPS, expectedAny);

        Integer actualAny = this.game.getPlayerWager().getAnyCraps();

        assertEquals(expectedAny, actualAny);
    }

    @Test
    public void testNoWager(){
        setUpWithInput("");

        Boolean output = this.game.setWager(CrapsWagerType.NONE, 0);

        assertTrue(output);
    }

    @Test
    public void testResetWagers(){
        setUpWithInput("");
        Integer expected = 0;
        this.game.setWager(CrapsWagerType.SEVENS, 100);
        this.game.setWager(CrapsWagerType.FIELD, 100);
        this.game.setWager(CrapsWagerType.ANYCRAPS, 100);

        this.game.resetWagers();

        Integer sevensWager = this.game.getPlayerWager().getSeven();
        Integer fieldWager = this.game.getPlayerWager().getFieldWager();
        Integer anyCrapsWager = this.game.getPlayerWager().getAnyCraps();

        assertEquals(sevensWager, expected);
        assertEquals(fieldWager, expected);
        assertEquals(anyCrapsWager, expected);

    }

}
