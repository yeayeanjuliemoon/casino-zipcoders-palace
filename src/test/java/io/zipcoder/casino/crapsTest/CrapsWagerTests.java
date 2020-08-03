package io.zipcoder.casino.crapsTest;

import io.zipcoder.casino.CrapsGame;
import io.zipcoder.casino.GamblingPlayer;
import io.zipcoder.casino.Player;
import org.junit.Before;

public class CrapsWagerTests {

    private CrapsGame game;
    private GamblingPlayer mainPlayer;

    @Before
    public void setUp(){
        this.mainPlayer = new GamblingPlayer("Bob");
        this.mainPlayer.deposit(1000);
        this.game = new CrapsGame(this.mainPlayer);
    }

    

}
