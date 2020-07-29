package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

public class GamblingPlayerTest {

    @Test
    public void getBalanceTest(){
        GamblingPlayer gambler = new GamblingPlayer("aName");
        Integer givenBalance = 50;

        gambler.deposit(givenBalance);
        Integer actualBalance = gambler.getBalance();

        Assert.assertEquals(givenBalance, actualBalance);
    }

    @Test
    public void depositTest(){
        GamblingPlayer gambler = new GamblingPlayer("aName");
        Integer givenBalance = 5000;

        gambler.deposit(givenBalance);
        Integer actualBalance = gambler.getBalance();

        Assert.assertEquals(givenBalance, actualBalance);
    }

    @Test
    public void withdrawTest(){
        GamblingPlayer gambler = new GamblingPlayer("aName");
        Integer givenBalance = 50;

        gambler.deposit(givenBalance);
        gambler.withdraw(givenBalance);

        Integer actualBalance = gambler.getBalance();
        Integer expectedBalance = 0;

        Assert.assertEquals(expectedBalance, actualBalance);
    }



}
