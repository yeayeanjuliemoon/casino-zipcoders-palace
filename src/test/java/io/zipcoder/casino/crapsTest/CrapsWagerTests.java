package io.zipcoder.casino.crapsTest;

import io.zipcoder.casino.CrapsGame;
import io.zipcoder.casino.GamblingPlayer;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.card.utilities.CrapsWagerType;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void testPayout2(){
        setUpWithInput("");
        this.game.setDiceSum(2);
        // Sum of 2 on round 1, dontpass, field, anycraps win
        // -200, +600 from any, +200 from field, +100 from dontpass
        Integer expectedBalance = 1700;
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

    @Test
    public void testGameStateTrue(){
        setUpWithInput("");
        this.game.setDiceSum(8);
        this.game.setPoint(1);

        assertTrue(this.game.checkGameState());
    }

    @Test
    public void testGameStateFalse(){
        setUpWithInput("");
        this.game.setDiceSum(4);
        this.game.setPoint(4);
        this.game.setRoundNum(2);

        assertFalse(this.game.checkGameState());
    }

    @Test
    public void testGetAllWagers(){
        setUpWithInput("pass\n100\nfield\n300\nnone");
        Integer expectedPass = 100;
        Integer expectedField = 300;

        this.game.getAllWagers();

        Integer actualPass = this.game.getPlayerWager().getPass();
        Integer actualField = this.game.getPlayerWager().getFieldWager();

        assertEquals(expectedPass, actualPass);
        assertEquals(expectedField, actualField);
    }

    @Test
    public void printGameRulesTest() {
        GamblingPlayer player = new GamblingPlayer("WhyNotZoidberg");
        CrapsGame craps = new CrapsGame(player);

        String actualOutput = craps.printGameRules();
        String expectedOutput = "==========Craps===========\n" + "Craps is a game where you roll a pair of dice multiple times\n" +
                "and bet on the outcome. The first round you can only make pass or don't pass\n" +
                "bets. The outcome of the first round becomes the \"point\" value\n" + "There are 5 possible wagers:\n"
                + "\tpass - On the first round, you are betting that the dice will equal 7 or 11\n" +
                "\tpast the first round, you are betting that the dice will hit the point value (2x Odds)\n\n" +
                "\tdontpass - On the first round, you are  betting that the dice will hit \"craps\"\n" +
                "\t(2, 3, or 12), past the first round, you are betting that the dice will hit a\n" +
                "\t7 before the point value (2x Odds)\n\n" + "\tfield - Past the first round, you are betting that the dice will hit a 2, 3, 4\n" +
                "\t9, 10, 11, 12 (3x Odds)\n\n" + "\tsevens - Past the first round, you are betting that the dice will roll a 7 (5x Odds)\n\n" +
                "\tanycraps - Past the first round, you are betting that the dice will hit craps (7x Odds)\n\n" +
                "Each round, enter the type bet you would like to make and the amount you want to bet. Enter\n" +
                "'none' when you are done betting for each round. All bets that are not Pass or Don't Pass\n" +
                "are cleared between rounds.\n\n Good Luck!\n";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testNextTurnFirst(){
        List<Integer> endingValues = new ArrayList<>(Arrays.asList(2, 3, 7, 11, 12));
        setUpWithInput("pass\n100");
        Integer expectedPassWin = 100;
        Integer expectedPassLoss = 0;

        this.game.nextTurn();

        Integer actualPass = this.game.getPlayerWager().getPass();
        if(endingValues.contains(this.game.getDiceSum())){
            assertFalse(this.game.checkGameState());
            assertEquals(expectedPassLoss, actualPass);
        }
        else{
            assertTrue(this.game.checkGameState());
            assertEquals(expectedPassWin, actualPass);
        }
    }

}
