package io.zipcoder.casino;

import java.util.Random;

public class Dice {
    private Integer[] diceValues;

    public Dice(Integer numDice){
        this.diceValues = new Integer[numDice];
    }

    public void rollDice(){
        Random rand = new Random();
        for(int i = 0; i < diceValues.length; i++){
            this.diceValues[i] = (rand.nextInt(6) + 1);
        }
    }

    public Integer[] getDiceValues(){
        return this.diceValues;
    }

}
