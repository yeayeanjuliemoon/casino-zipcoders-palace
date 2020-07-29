package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.List;

public class Dice {
    private Integer numDice;
    private List<Integer> dieValues;

    public Dice(Integer numDice){
        this.numDice = numDice;
    }

    public void rollDice(){

    }

    List<Integer> getDieValues(){
        return this.dieValues;
    }
}
