package io.zipcoder.casino;

import io.zipcoder.casino.Player;
import io.zipcoder.casino.CrapsWager;
import io.zipcoder.casino.DiceGame;

import java.util.Map;

public class CrapsGame extends DiceGame {

    private Map<Player, CrapsWager> playerWagerMap;
    private Integer roundNum;
    private Integer point;

    public CrapsGame(){
        super();
    }

    private void getRoundOneWager(){

    }

    private void getAllWagers(){

    }

    private void resetWagers(){

    }

    private void resetRoundNumber(){

    }

    private void setPoint(Integer point){
        this.point = point;
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
    public String printGameStatus() {
        return null;
    }

    @Override
    public String printGameRules() {
        return null;
    }

    @Override
    public void exit() {

    }
}
