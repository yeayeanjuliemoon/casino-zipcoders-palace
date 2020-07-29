package io.zipcoder.casino;

public abstract class DiceGame implements Game {
    Dice dice;
    Integer numberRoll;
    Integer diceSum;

    public DiceGame(){

    }

    public String printDiceValues(){
        return "";
    }
}
