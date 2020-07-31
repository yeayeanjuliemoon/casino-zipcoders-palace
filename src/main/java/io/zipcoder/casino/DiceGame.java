package io.zipcoder.casino;


import java.util.List;
public abstract class DiceGame implements Game, GamblingGame {
    protected Dice dice;
    protected Integer numberRoll;
    protected Integer diceSum;

    public DiceGame(Integer numDice, GamblingPlayer player){
        this.dice = new Dice(numDice);
    }

    public String printDiceValues(){
        List<Integer> diceList = dice.getDiceValues();
        StringBuilder sb = new StringBuilder();
        for(Integer i : diceList){
            sb.append("[ " + i.toString() + " ]");
        }
        return sb.toString();
    }
}
