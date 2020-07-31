package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void toStringTest(){
        String givenName = "DegenerateGambler";
        Player player = new Player(givenName);

        String actualResult = player.toString();
        String expectedResult = "DegenerateGambler";

        Assert.assertEquals(actualResult, expectedResult);
    }

}
