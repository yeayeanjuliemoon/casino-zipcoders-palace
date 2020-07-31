package io.zipcoder.casino;

public class Player {

    String name;

    public Player(String aName) {
        this.name = aName;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
