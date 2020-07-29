package io.zipcoder.casino;

public class GamblingPlayer extends Player implements Gambler {

    Integer balance;

    public GamblingPlayer(String aName) {
        super(aName);
        this.balance = 0;
    }

    public Integer getBalance(){
        return this.balance;
    }

    public void withdraw(Integer amount){
        this.balance -= amount;

    }

    public void deposit(Integer amount){
        this.balance += amount;
    }
}
