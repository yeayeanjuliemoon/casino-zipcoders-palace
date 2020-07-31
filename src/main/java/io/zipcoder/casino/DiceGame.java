package io.zipcoder.casino;


import java.util.List;
public abstract class DiceGame implements Game, GamblingGame {
    Dice dice;
    Integer numberRoll;
    Integer diceSum;

    public DiceGame(Integer numDice){
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
