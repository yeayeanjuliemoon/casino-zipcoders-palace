package io.zipcoder.casino;

import io.zipcoder.casino.card.utilities.CrapsWagerType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
        super(2, player);
        this.gameState = true;
        this.activePlayer = player;
    }

    public void getRoundOneWager(){
        boolean readyToRoll = false;
        while(!readyToRoll){
            CrapsWagerType wagerType = getWagerType();
            if(wagerType != CrapsWagerType.PASS && wagerType != CrapsWagerType.DONTPASS){
                console.println("You can only bet PASS/DONTPASS on round 1!");
            }
            else{
                takeRoundOneBet(wagerType);
                readyToRoll = true;
            }
        }
    }

    private void takeRoundOneBet(CrapsWagerType wagerType) {
        Integer amountWagered = parseBet();
        console.println("Betting $" + amountWagered + " on " + wagerType.toString());
        setWager(wagerType, amountWagered);
    }

    public void getAllWagers(){
        boolean readyToRoll = false;
        while(!readyToRoll){
            readyToRoll = getIndividualWager();
        }
        console.println("You are betting: \n" + this.playerWager.toString());
    }

    private boolean getIndividualWager(){
        CrapsWagerType wagerType = getWagerType();
        Integer amountWagered = 0;
        if(!(wagerType == CrapsWagerType.NONE)) {
            amountWagered = parseBet();
            console.println("Betting $" + amountWagered + " on " + wagerType.toString());
        }
        else{
            console.println("Betting is over, let the dice roll!");
        }
         return setWager(wagerType, amountWagered);
    }

    public boolean setWager(CrapsWagerType wagerType, Integer amountWagered) {
        switch(wagerType){
            case PASS:
                this.playerWager.setPass(amountWagered);
                break;
            case DONTPASS:
                this.playerWager.setDontPass(amountWagered);
                break;
            case ANYCRAPS:
                this.playerWager.setAnyCraps(amountWagered);
                break;
            case SEVENS:
                this.playerWager.setSeven(amountWagered);
                break;
            case FIELD:
                this.playerWager.setFieldWager(amountWagered);
                break;
            case NONE:
                return true;
        }
        return false;
    }

    public void resetWagers(){
        //TODO
        this.playerWager.setAnyCraps(0);
        this.playerWager.setFieldWager(0);
        this.playerWager.setSeven(0);
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
        payoutPass();
        payoutDontPass();
        payoutField();
        payoutSevens();
        payoutAnyCraps();
        resetWagers();
    }

    public void payoutPass() {
        try{
            if(winWager(CrapsWagerType.PASS)){
                this.activePlayer.deposit(this.playerWager.getPass());
            }
            else{
                this.activePlayer.withdraw(this.playerWager.getPass());
            }
            this.playerWager.setPass(0);
        } catch (NullPointerException e){}
        ;
    }

    public void payoutDontPass() {
        try{
            if(winWager(CrapsWagerType.DONTPASS)){
                this.activePlayer.deposit(this.playerWager.getDontPass());
            }
            else{
                this.activePlayer.withdraw(this.playerWager.getDontPass());
            }
            this.playerWager.setDontPass(0);
        } catch (NullPointerException e){}
        ;
    }

    public void payoutField() {
        if(winWager(CrapsWagerType.FIELD)){
            this.activePlayer.deposit(2 * this.playerWager.getFieldWager());
        }
        else{
            this.activePlayer.withdraw(this.playerWager.getFieldWager());
        }
    }

    public void payoutSevens() {
        if(winWager(CrapsWagerType.SEVENS)){
            this.activePlayer.deposit(4 * this.playerWager.getSeven());
        }
        else{
            this.activePlayer.withdraw(this.playerWager.getSeven());
        }
    }

    public void payoutAnyCraps() {
        if(winWager(CrapsWagerType.ANYCRAPS)){
            this.activePlayer.deposit(6 * this.playerWager.getAnyCraps());
        }
        else{
            this.activePlayer.withdraw((this.playerWager.getAnyCraps()));
        }
    }

    public void setPoint(Integer point){
        this.point = point;
    }

    public void play(){
        console.println(printGameRules());
        while(this.gameState){
            nextTurn();
            this.roundNum++;
        }
        exit();
    }

    @Override
    public void nextTurn() {
        pickWager();
        console.println("Rolling the dice....");
        this.dice.rollDice();
        console.println(printDiceValues());
        this.diceSum = this.dice.sumDice();
        payout();
        console.println("You now have $" + this.activePlayer.getBalance() + " in your account");
        this.gameState = checkGameState();
    }

    public void pickWager() {
        if(roundNum == 1) {
            getRoundOneWager();
        } else{
            getAllWagers();
        }
    }

    @Override
    public Boolean checkGameState() {
        if(this.roundNum == 1) {
            return roundOneGameState();
        }
        else{
            return laterRoundGameState();
        }
    }

    public Boolean laterRoundGameState() {
        return !(this.diceSum == this.point || this.diceSum == 7);
    }

    public Boolean roundOneGameState() {
        List<Integer> endingValues = new ArrayList<>(Arrays.asList(7, 11, 2, 3, 12));
        return !endingValues.contains(this.diceSum);
    }


    @Override
    public void exit() {
        console.println("Thank you for playing craps");
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
        switch (wagerType){
            case PASS:
                return passWager();
            case DONTPASS:
                return dontPassWager();
            case FIELD:
                return fieldWager();
            case SEVENS:
                return sevensWager();
            case ANYCRAPS:
                return anycrapsWager();
        }
        return null;
    }

    public Boolean anycrapsWager() {
        return (this.diceSum == 2 || this.diceSum == 3 || this.diceSum == 12);
    }

    public Boolean sevensWager() {
        return this.diceSum == 7;
    }

    public Boolean fieldWager() {
        List<Integer> winningValues = new ArrayList<>(Arrays.asList(2, 3, 4, 9, 10, 11, 12));
        if(winningValues.contains(this.diceSum)){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean dontPassWager() {
        Boolean pass = passWager();
        if(pass != null){
            pass = !pass;
        }
        return pass;
    }

    private Boolean passWager(){
        if (roundNum == 1){
            return roundOnePassWager();
        }
        else{
            return laterRoundPassWager();
        }
    }

    private Boolean laterRoundPassWager() {
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

    private Boolean roundOnePassWager() {
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

    @Override
    public String printGameRules() {
        return "==========Craps===========\n" + "Craps is a game where you roll a pair of dice multiple times\n" +
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
    }

    public CrapsWager getPlayerWager(){
        return this.playerWager;
    }

    public void setDiceSum(Integer sum){
        this.diceSum = sum;
    }

    public Integer getDiceSum(){
        return this.diceSum;
    }

    public void setRoundNum(Integer roundNum){
        this.roundNum = roundNum;
    }

}
