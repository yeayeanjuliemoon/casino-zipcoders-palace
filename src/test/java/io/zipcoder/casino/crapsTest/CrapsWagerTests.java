package io.zipcoder.casino.crapsTest;

import io.zipcoder.casino.CrapsGame;
import io.zipcoder.casino.GamblingPlayer;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.card.utilities.CrapsWagerType;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

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

    @Test
    public void testPayout(){
        setUpWithInput("");
        this.game.setDiceSum(7);
        // Sum of 7 on round 1, DONTPASS, SEVENS Win, rest lose
        // -300, + 100 from dontpass, + 300 from sevens
        Integer expectedBalance = 1200;
        this.game.setWager(CrapsWagerType.PASS, 100);
        this.game.setWager(CrapsWagerType.DONTPASS, 100);
        this.game.setWager(CrapsWagerType.SEVENS, 100);
        this.game.setWager(CrapsWagerType.FIELD, 100);
        this.game.setWager(CrapsWagerType.ANYCRAPS, 100);

        this.game.payout();

        Integer actualBalance = this.mainPlayer.getBalance();

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testPassWagerRound1(){
        setUpWithInput("");
        this.game.setDiceSum(7);

        assertTrue(this.game.winWager(CrapsWagerType.PASS));
    }
    @Test
    public void testPassWagerRound2(){
        setUpWithInput("");
        this.game.setDiceSum(7);
        this.game.setPoint(4);
        this.game.setRoundNum(2);

        assertFalse(this.game.winWager(CrapsWagerType.PASS));
    }
    @Test
    public void testPassWagerNull(){
        setUpWithInput("");
        this.game.setDiceSum(5);

        assertNull(this.game.winWager(CrapsWagerType.PASS));
    }

    @Test
    public void testDontPassWagerRound2(){
        setUpWithInput("");
        this.game.setDiceSum(7);
        this.game.setPoint(4);
        this.game.setRoundNum(2);

        assertTrue(this.game.winWager(CrapsWagerType.DONTPASS));
    }



}
