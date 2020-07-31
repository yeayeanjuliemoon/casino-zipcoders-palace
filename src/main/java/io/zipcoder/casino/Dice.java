package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dice {
    private Integer numDice;
    private List<Integer> dieValues;

    public Dice(Integer numDice){
        this.numDice = numDice;
        this.dieValues = new ArrayList<Integer>();
        initializeDice();
    }

    public void rollDice(){
        Random rand = new Random();
        this.dieValues.clear();
        for(int i = 0; i < this.numDice; i++){
            this.dieValues.add(rand.nextInt(6) + 1);
        }
    }

    public List<Integer> getDieValues(){
        return this.dieValues;
    }

    private void initializeDice(){
        // Initialize the dice to a value of 1 (Might change this later)
        for(int i = 0; i < this.numDice; i++){
            this.dieValues.add(1);
        }
    }

    public Integer sumDice(){
        Integer sum = 0;
        for(Integer i: dieValues){
            sum += i;
        }
        return sum;
    }
}
