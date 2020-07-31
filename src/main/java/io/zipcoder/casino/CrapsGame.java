package io.zipcoder.casino;

import io.zipcoder.casino.card.utilities.CrapsWagerType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CrapsGame extends DiceGame implements GamblingGame {

    private CrapsWager playerWager = new CrapsWager();
    private Integer roundNum = 1;
    private Integer point;
    private final Console console = new Console(System.in, System.out);
    private Boolean gameState;
    private GamblingPlayer activePlayer;


    public CrapsGame(GamblingPlayer player){
        super(2);
        this.gameState = true;
        this.activePlayer = player;
    }

    private void getRoundOneWager(){
        //TODO
        boolean readyToRoll = false;
        while(!readyToRoll){
            CrapsWagerType wagerType = getWagerType();
            if(wagerType != CrapsWagerType.PASS && wagerType != CrapsWagerType.DONTPASS){
                console.println("You can only bet PASS/DONTPASS on round 1!");
            }
            else{
                Integer amountWagered = parseBet();
                switch(wagerType){
                    case PASS:
                        this.playerWager.setPass(amountWagered);
                        break;
                    case DONTPASS:
                        this.playerWager.setDontPass(amountWagered);
                }
                readyToRoll = true;
            }
        }
    }

    private void getAllWagers(){
        //TODO
    }

    private void resetWagers(){
        //TODO
        this.playerWager.setAnyCraps(0);
        this.playerWager.setFieldWager(0);
        this.playerWager.setSeven(0);
    }

    private void resetRoundNumber(){
        //TODO
        this.roundNum = 0;
    }

    private Integer parseBet(){
        Integer amountWagered = console.getIntegerInput("How much would you like to bet? ");
        if(amountWagered <= this.activePlayer.getBalance()){
            return amountWagered;
        }
        else{
            console.println("Insufficient Funds");
            return parseBet();
        }
    }

    @Override
    public void takeBet() {

    }

    @Override
    public void payout() {
        // I hate this
        try{
            if(winWager(CrapsWagerType.PASS)){
                this.activePlayer.deposit(2 * this.playerWager.getPass());
            }
            else{
                this.activePlayer.withdraw(this.playerWager.getPass());
            }
            this.playerWager.setPass(0);
        } catch (NullPointerException e){};
        try{
            if(winWager(CrapsWagerType.DONTPASS)){
                this.activePlayer.deposit(2 * this.playerWager.getDontPass());
            }
            else{
                this.activePlayer.withdraw(this.playerWager.getDontPass());
            }
            this.playerWager.setDontPass(0);
        } catch (NullPointerException e){};
        if(winWager(CrapsWagerType.FIELD)){
            this.activePlayer.deposit(3 * this.playerWager.getFieldWager());
        }
        else{
            this.activePlayer.withdraw(this.playerWager.getFieldWager());
        }
        if(winWager(CrapsWagerType.SEVENS)){
            this.activePlayer.deposit(5 * this.playerWager.getSeven());
        }
        else{
            this.activePlayer.withdraw(this.playerWager.getSeven());
        }
        if(winWager(CrapsWagerType.ANYCRAPS)){
            this.activePlayer.deposit(7 * this.playerWager.getAnyCraps());
        }
        else{
            this.activePlayer.withdraw((this.playerWager.getAnyCraps()));
        }
        resetWagers();
    }

    private void setPoint(Integer point){
        this.point = point;
    }

    public void play(){
        console.println(printGameRules());
        pauseForReadability();
        while(gameState){
            nextTurn();
            this.roundNum++;
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
        getRoundOneWager();
        this.dice.rollDice();
        console.println(printDiceValues());
        payout();
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

    public CrapsWagerType getWagerType(){
        String playerInput = console.getStringInput("Enter the type of wager you would like to make: ");
        try{
            return CrapsWagerType.valueOf(playerInput.toUpperCase());
        }catch (IllegalArgumentException e){
            console.println("Not a valid wager type");
            return getWagerType();
        }
    }

    public Boolean winWager(CrapsWagerType wagerType){
        this.diceSum = this.dice.sumDice();
        switch (wagerType){
            case PASS:
                return passWager();
            case DONTPASS:
                Boolean pass = passWager();
                if(pass != null){
                    pass = !pass;
                }
                return pass;
            case FIELD:
                List<Integer> winningValues = new ArrayList<>(Arrays.asList(2, 3, 4, 9, 10, 11, 12));
                if(winningValues.contains(this.diceSum)){
                    return true;
                }
                else{
                    return false;
                }
            case SEVENS:
                if(this.diceSum == 7){
                    return true;
                }
                else{
                    return false;
                }
            case ANYCRAPS:
                if(this.diceSum == 2 || this.diceSum == 3 || this.diceSum == 12){
                    return true;
                }
                else{
                    return false;
                }
        }
        return null;
    }

    private Boolean passWager(){
        if (roundNum == 1){
            if(this.diceSum == 7 || this.diceSum == 11){
                return true;
            }
            else if(this.diceSum == 2 || this.diceSum == 3 || this.diceSum == 12){
                return false;
            }
            else{
                this.point = this.diceSum;
                return null;
            }
        }
        else{
            if(this.diceSum == this.point){
                return true;
            }
            else if(this.diceSum == 7){
                return false;
            }
            else{
                return null;
            }
        }
    }

}