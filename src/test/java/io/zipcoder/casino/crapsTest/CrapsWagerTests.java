package io.zipcoder.casino.crapsTest;

import io.zipcoder.casino.CrapsGame;
import io.zipcoder.casino.GamblingPlayer;
import io.zipcoder.casino.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

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
    public void testGetRoundOneWagerDontPass(){
        setUpWithInput("dontpass\n100");
        Integer expectedWager = 100;

        this.game.getRoundOneWager();

        Integer actualWager = this.game.getPlayerWager().getDontPass();

        assertEquals(expectedWager, actualWager);
    }

}
