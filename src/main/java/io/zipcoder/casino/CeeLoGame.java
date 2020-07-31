package io.zipcoder.casino;

import io.zipcoder.casino.Player;
import io.zipcoder.casino.DiceGame;

import java.util.Map;

public class CeeLoGame extends DiceGame {
    //public Map<Player, Integer> roundScore;
    public Integer pot;

    public CeeLoGame(){
        super(2);
    }

    private void decideRoundBet(){

    }

    private Boolean agreeToBet(){
        return null;
    }

    private Integer checkCombinations(){
        return null;
    }

    @Override
    public void play() {

    }

    @Override
    public void nextTurn() {

    }

    @Override
    public Boolean checkGameState() {
        return null;
    }


    @Override
    public String printGameRules() {
        return null;
    }

    @Override
    public void exit() {

    }
    /*
    private Player findRoundWinner(){
        return null;
    }

     */
}
