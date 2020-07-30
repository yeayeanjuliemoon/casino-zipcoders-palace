package io.zipcoder.casino;

import io.zipcoder.casino.Player;
import io.zipcoder.casino.CrapsWager;
import io.zipcoder.casino.DiceGame;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CrapsGame extends DiceGame implements GamblingGame {

    private Map<Player, CrapsWager> playerWagerMap;
    private Integer roundNum;
    private Integer point;
    private Console console = new Console(System.in, System.out);
    private Boolean gameState;


    public CrapsGame(){
        super();
        gameState = true;
    }

    private void getRoundOneWager(){
        //TODO
    }

    private void getAllWagers(){
        //TODO
    }

    private void resetWagers(){
        //TODO
    }

    private void resetRoundNumber(){
        //TODO
    }

    @Override
    public void takeBet() {
        // Capture what type of bet
        // invoke method for that kind of bet
    }

    @Override
    public void payout() {
        // TODO
    }

    private void setPoint(Integer point){
        this.point = point;
    }

    public void play(){
        console.println(printGameRules());
        takeBet();
        console.println("You have wagered "); //TODO GET WAGER VALUE
        pauseForReadability();
        while(gameState){
            nextTurn();
        }

        exit();
    }

    @Override
    public void nextTurn() {
        // prompt for bet
        // roll dice
        // Print returned value
        // check bets
        // Payout where applicable
        // if game > 1 and returned 7, change gameState
    }

    @Override
    public Boolean checkGameState() {
        return null;
    }

    //    @Override
    public String printGameStatus() {
        return null;
    }

    @Override
    public String printGameRules() {
        return "Rules to be printed here";
    }

    private void pauseForReadability(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            Logger logger = Logger.getLogger(Casino.class.getName());
            logger.log(Level.INFO, e.toString());
        }
    }

    @Override
    public void exit() {
        console.println("Thank you for playing craps");
        pauseForReadability();
    }

}