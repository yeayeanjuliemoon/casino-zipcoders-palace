//package io.zipcoder.casino;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//public class CasinoTest {
//
//    @Test
//    public void constructorTest(){
//        Casino casino = new Casino();
//
//    }
//
//    @Test
//    public void createPlayerTest(){
//        Casino casino = new Casino();
//
//
//    }
//
//    @Test
//    public void resetBalanceTest(){
//        Casino casino = new Casino();
//        casino.resetBalance();
//
//    }
//
//    @Test
//    public void selectGameTest(){
//        Casino casino = new Casino();
//        casino.selectGame();
//
//    }
//
//    @Test
//    public void playGameTest(){
//        Casino casino = new Casino();
//        casino.playGame();
//    }
//
//    @Test
//    public void quitTest(){
//        Casino casino = new Casino();
//        casino.quit();
//
//        Assert.assertNull(casino);
//    }
//
//    @Test
//    public void printCasinoMenuTest() {
//        Casino casino = new Casino();
//
//        String actualResult = casino.printCasinoMenu();
//        String expectedResult = "Welcome to ZipCoder's Palace!\nPlease input a selection from below\nSomething}" +
//                                ": a something game\n Something: a something game\n";
//
//        Assert.assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    public void playerLoginTest(){
//        Casino casino = new Casino();
//        String givenName = "GamblingDegenerate";
//
//        casino.playerLogin(givenName);
//        String actualPlayer = casino.printActivePlayers();
//    }
//
//    @Test
//    public void playerLogoutTest(){
//        Casino casino = new Casino();
//        String givenName = "GamblingDegenerate";
//
//        casino.playerLogin(givenName);
//        casino.playerLogout(givenName);
//
//        String actualPlayer = casino.printActivePlayers();
//
//    }
//
//    @Test
//    public void printActivePlayerTest(){
//        Casino casino = new Casino();
//        String givenName = "GamblingDegenerate";
//
//        casino.playerLogin(givenName);
//        String actualPlayer = casino.printActivePlayers();
//
//    }
//
//    @Test
//    public void parseMenuInputTest(){
//        Casino casino = new Casino();
//
//        casino.parseMenuInput();
//
//    }
//
//    @Test
//    public void checkIfActivePlayersTest(){
//        Casino casino = new Casino();
//
//        Boolean actualActivePlayers = casino.checkIfActivePlayers();
//
//        Assert.assertFalse(actualActivePlayers);
//    }
//
//
//}
