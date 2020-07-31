package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {

    private List<Integer> dieValues;
    private Integer numDice;

    public Dice(Integer numDice){
        this.numDice = numDice;
        this.dieValues = new ArrayList<>();
        initializeDice();
    }

    public void rollDice(){
        Random rand = new Random();
        this.dieValues.clear();
        for(int i = 0; i < this.numDice; i++){

            this.dieValues.add(rand.nextInt(6) + 1);
        }
    }

    public List<Integer> getDiceValues(){
        return this.dieValues;
    }

    private void initializeDice() {
        for(int i = 0; i < numDice; i++) {
            this.dieValues.add(i);
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
